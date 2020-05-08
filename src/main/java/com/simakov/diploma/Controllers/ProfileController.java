package com.simakov.diploma.Controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.simakov.diploma.Constants.Constants;
import com.simakov.diploma.Model.AdminOrder;
import com.simakov.diploma.Model.Cart;
import com.simakov.diploma.Model.Category;
import com.simakov.diploma.Model.PersInfo;
import com.simakov.diploma.Model.PlaceOrder;
import com.simakov.diploma.Model.Product;
import com.simakov.diploma.Model.Review;
import com.simakov.diploma.Model.User;
import com.simakov.diploma.Model.Wishlist;
import com.simakov.diploma.Repository.AdminOrderRepository;
import com.simakov.diploma.Repository.CartRepository;
import com.simakov.diploma.Repository.CategoryRepository;
import com.simakov.diploma.Repository.OrderRepository;
import com.simakov.diploma.Repository.PersInfoRepository;
import com.simakov.diploma.Repository.ProductRepository;
import com.simakov.diploma.Repository.ReviewRepository;
import com.simakov.diploma.Repository.UserRepository;
import com.simakov.diploma.Repository.WishlistRepository;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.jwtUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Dispute;
import com.stripe.model.DisputeCollection;
import com.stripe.model.Refund;
import com.stripe.model.RefundCollection;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

@CrossOrigin(origins = "*")
@RestController
public class ProfileController {

    private final List<SseEmitter> emitters = new ArrayList<>();

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AdminOrderRepository aOrderRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private WishlistRepository wishRepo;

    @Autowired
    private PersInfoRepository persRepo;

    @Autowired
    private CategoryRepository catRepo;

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private ReviewRepository reviewsRepo;

    @Autowired
    private jwtUtil jwtutil;

    @PostMapping("/profile")
    public ResponseEntity<Response> getProfile(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("LIST_PROFILE");
            resp.setObject(loggedUser);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    // @PostMapping("/updprofilepinfo")
    // public ResponseEntity<Response> updProfilePinfo(@RequestHeader(name =
    // "AUTH_TOKEN") String AUTH_TOKEN,
    // @RequestBody User user) {
    // Response resp = new Response();
    // if (jwtutil.checkToken(AUTH_TOKEN) != null) {
    // User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
    // User usere = userRepo.findByPhoneAndUsertype(loggedUser.getPhone(),
    // loggedUser.getUsertype());
    // usere.setFullname(user.getFullname());
    // usere.setDob(user.getDob());
    // usere.setGender(user.getGender());
    // userRepo.save(usere);
    // resp.setStatus("200");
    // resp.setMessage("UPD_PROFILE");
    // resp.setObject(usere);
    // } else {
    // resp.setStatus("500");
    // resp.setMessage("ERROR");
    // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }
    @PostMapping("/updprofilepinfo")
    public ResponseEntity<Response> updProfilePinfo(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody User user) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            User usere = userRepo.findByPhoneAndUsertype(loggedUser.getPhone(), loggedUser.getUsertype());
            usere.setDob(user.getDob());
            usere.setFullname(user.getFullname());
            usere.setGender(user.getGender());
            // perse.setFullname(pers.getFullname());
            // perse.setDob(pers.getDob());
            // perse.setGender(pers.getGender());
            // perse.setAddress(pers.getAddress());
            // perse.setFlat(pers.getFlat());
            // perse.setInd(pers.getInd());
            // perse.setDoor(pers.getDoor());
            // perse.setFloor(pers.getFloor());
            // perse.setDoorphone(pers.getDoorphone());
            userRepo.save(usere);
            resp.setStatus("200");
            resp.setMessage("UPD_PROFILE");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/delprofile")
    public ResponseEntity<Response> delProfile(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            userRepo.deleteAllByUuid(loggedUser.getUuid());
            cartRepo.deleteAllByUuid(loggedUser.getUuid());
            wishRepo.deleteAllByUuid(loggedUser.getUuid());
            persRepo.deleteAllByUuid(loggedUser.getUuid());
            aOrderRepo.deleteAllByUuid(loggedUser.getUuid());
            orderRepo.deleteAllByUuid(loggedUser.getUuid());
            reviewsRepo.deleteAllByUserId(loggedUser.getUserid());
            resp.setStatus("200");
            resp.setMessage("UPD_PROFILE");
        } else if (AUTH_TOKEN != null) {
            userRepo.deleteAllByUuid(AUTH_TOKEN);
            cartRepo.deleteAllByUuid(AUTH_TOKEN);
            wishRepo.deleteAllByUuid(AUTH_TOKEN);
            persRepo.deleteAllByUuid(AUTH_TOKEN);
            aOrderRepo.deleteAllByUuid(AUTH_TOKEN);
            orderRepo.deleteAllByUuid(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("UPD_PROFILE");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/updphone")
    public ResponseEntity<Response> updPhone(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody User user) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            User usere = userRepo.findByPhoneAndUsertype(loggedUser.getPhone(), loggedUser.getUsertype());
            usere.setPhone(user.getPhone());
            userRepo.save(usere);
            String jwtToken = jwtutil.createToken(usere.getPhone(), usere.getPassword(), "customer", 0);
            resp.setStatus("200");
            resp.setMessage("UPD_PROFILE");
            resp.setAUTH_TOKEN(jwtToken);
            resp.setObject(usere);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/updpass")
    public ResponseEntity<Response> updPass(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody User user) throws IOException {
        Response resp = new Response();

        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            User usere = userRepo.findByPhoneAndUsertype(loggedUser.getPhone(), loggedUser.getUsertype());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            usere.setPassword(hashedPassword);
            userRepo.save(usere);
            String jwtToken = jwtutil.createToken(usere.getPhone(), usere.getPassword(), "customer", 0);
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send('a');
                } catch (Throwable e) {
                    emitter.complete();
                }
            }
            resp.setStatus("200");
            resp.setMessage("UPD_PROFILE");
            resp.setAUTH_TOKEN(jwtToken);
            resp.setObject(usere);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getorders")
    public ResponseEntity<Response> getOrders(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            List<PlaceOrder> orders = orderRepo.findAllByUuid(loggedUser.getUuid());
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
            resp.setObject(orders);
        } else if (AUTH_TOKEN != null) {
            List<PlaceOrder> orders = orderRepo.findAllByUuid(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
            resp.setObject(orders);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getaorders")
    public ResponseEntity<Response> getAOrders(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            List<AdminOrder> orders = aOrderRepo.findByUuid(loggedUser.getUuid());
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
            resp.setObject(orders);
        } else if (AUTH_TOKEN != null) {
            List<AdminOrder> orders = aOrderRepo.findByUuid(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
            resp.setObject(orders);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/checktoken")
    public ResponseEntity<Response> checkToken(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkchange")
    public SseEmitter stream() throws IOException {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;
    }

    @GetMapping("/getallorders")
    public ResponseEntity<Response> allOrders() {
        Response resp = new Response();
        List<PlaceOrder> order = orderRepo.findAll();
        // List<AdminOrder> aorder = aOrderRepo.findAll();
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        resp.setObject(order);
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getallaorders")
    public ResponseEntity<Response> allAOrders() {
        Response resp = new Response();
        List<AdminOrder> aorder = aOrderRepo.findAll();
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        resp.setObject(aorder);
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/deleteorder")
    public ResponseEntity<Response> deleteOrder(@RequestBody int orderId) {
        Response resp = new Response();
        orderRepo.deleteByOrderId(orderId);
        aOrderRepo.deleteByOrderId(orderId);
        resp.setStatus("200");
        resp.setMessage("SUCCESS");

        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/deletecarts")
    public ResponseEntity<Response> deleteCarts() {
        Response resp = new Response();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        List<Cart> cartlist = cartRepo.findAll();
        for (Cart cart : cartlist) {
            LocalDate date = LocalDate.parse(dateFormat.format(cart.getCartAdded()));
            if (ChronoUnit.DAYS.between(date, now) > 30) {
                // cartRepo.deleteAllByUuid(cart.getUuid());
                cartRepo.deleteByCartIdAndUuid(cart.getCartId(), cart.getUuid());
            }
        }
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/deletewish")
    public ResponseEntity<Response> deleteWish() {
        Response resp = new Response();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        List<Wishlist> wishlist = wishRepo.findAll();
        for (Wishlist wish : wishlist) {
            LocalDate date = LocalDate.parse(dateFormat.format(wish.getWishAdded()));
            if (ChronoUnit.DAYS.between(date, now) > 30) {
                wishRepo.deleteByWishlistIdAndUuid(wish.getWishlistId(), wish.getUuid());
            }
        }
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/updstatus")
    public ResponseEntity<Response> updStatus(@RequestBody PlaceOrder order,
            @RequestParam(name = "phone") String phone) {
        Response resp = new Response();
        PlaceOrder ord = orderRepo.findByOrderId(order.getOrderId());
        ord.setOrderStatus(order.getOrderStatus());
        orderRepo.save(ord);
        Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
        String string = String.format("Статус вашего заказа с номером %2d сменился на '%s'!", order.getOrderId(),
                order.getOrderStatus());
        Message message = Message.creator(new PhoneNumber(phone), new PhoneNumber("+13343445022"), string).create();
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/cancelord")
    public ResponseEntity<Response> cancelOrder(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody PlaceOrder order) throws StripeException {
        Response resp = new Response();
        try {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            PlaceOrder ord = orderRepo.findByUuidAndOrderId(loggedUser.getUuid(), order.getOrderId());
            if (order.getOrderStatus().equals(order.getOrderStatus()) && order.getOrderStatus().equals("На складе")) {
                Stripe.apiKey = Constants.STRIPE_APIKEY;
                Map<String, Object> params = new HashMap<>();
                params.put("charge", order.getPaymentId());
                Refund refund = Refund.create(params);
                aOrderRepo.deleteByOrderId(order.getOrderId());
                orderRepo.deleteByOrderId(order.getOrderId());
                persRepo.deleteByOrderId(order.getOrderId());
                resp.setStatus("200");
                resp.setMessage("SUCCESS");
            } else {
                resp.setStatus("500");
                resp.setMessage("ERROR_STATUS");
            }
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/reqorder")
    public ResponseEntity<Response> requestOrder(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody int orderId) {
        Response resp = new Response();
        try {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            // CancelOrder canc = new CancelOrder();
            // canc.setOrderId(orderId);
            // canc.setUuid(loggedUser.getUuid());
            // cancRepo.save(canc);
            PlaceOrder ord = orderRepo.findByUuidAndOrderId(loggedUser.getUuid(), orderId);
            ord.setCancOrder(1);
            orderRepo.save(ord);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/confdecide")
    public ResponseEntity<Response> confDecide(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody int orderId) {
        Response resp = new Response();
        try {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            PlaceOrder ord = orderRepo.findByUuidAndOrderId(loggedUser.getUuid(), orderId);
            ord.setCancOrder(3);
            orderRepo.save(ord);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/rejectionreq")
    public ResponseEntity<Response> rejectionReq(@RequestBody int orderId) {
        Response resp = new Response();
        try {
            PlaceOrder ord = orderRepo.findByOrderId(orderId);
            ord.setCancOrder(2);
            orderRepo.save(ord);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/confirmreq")
    public ResponseEntity<Response> confirmReq(@RequestBody PlaceOrder order) {
        Response resp = new Response();
        try {
            PlaceOrder ord = orderRepo.findByOrderId(order.getOrderId());
            Stripe.apiKey = Constants.STRIPE_APIKEY;
            Map<String, Object> params = new HashMap<>();
            params.put("charge", order.getPaymentId());
            Refund refund = Refund.create(params);
            aOrderRepo.deleteByOrderId(order.getOrderId());
            orderRepo.deleteByOrderId(order.getOrderId());
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    // @GetMapping("/getcancorders")
    // public ResponseEntity<Response> getCancOrders() {
    // Response resp = new Response();
    // try {
    // List<CancelOrder> ord = cancRepo.findAll();
    // resp.setStatus("200");
    // resp.setMessage("SUCCESS");
    // resp.setObject(ord);
    // } catch (Exception e) {
    // resp.setStatus("500");
    // resp.setMessage(e.getMessage());
    // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }

    @GetMapping("/arrusers")
    public ResponseEntity<Response> arrUsers() {
        Response resp = new Response();
        try {
            List<User> users = userRepo.findAll();
            resp.setStatus("200");
            resp.setMessage("LIST_USERS");
            resp.setObject(users);
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/finduser")
    public ResponseEntity<Response> findUser(@RequestBody int user) {
        Response resp = new Response();
        try {
            User usere = userRepo.findByUserid(user);
            resp.setStatus("200");
            resp.setMessage("LIST_USER");
            resp.setObject(usere);
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/blockuser")
    public ResponseEntity<Response> blockUser(@RequestBody int userid) {
        Response resp = new Response();
        try {
            User usere = userRepo.findByUserid(userid);
            usere.setBan(1);
            userRepo.save(usere);
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send('a');
                } catch (Throwable e) {
                    emitter.complete();
                }
            }
            reviewsRepo.deleteAllByUserId(userid);
            resp.setStatus("200");
            resp.setMessage("USER_BLOCKED");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/unblockuser")
    public ResponseEntity<Response> unBlockUser(@RequestBody int userid) {
        Response resp = new Response();
        try {
            User usere = userRepo.findByUserid(userid);
            usere.setBan(0);
            userRepo.save(usere);
            resp.setStatus("200");
            resp.setMessage("USER_UNBLOCKED");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/editcat")
    public ResponseEntity<Response> editCategory(@RequestBody Category category) {
        Response resp = new Response();
        try {
            Category cat = catRepo.findByCategoryId(category.getCategoryId());
            if (category.getCategoryTitle() != null) {
                cat.setCategoryTitle(category.getCategoryTitle());
            }
            if (category.getCategoryImg() != null) {
                cat.setCategoryImg(category.getCategoryImg());
            }
            catRepo.save(cat);
            resp.setStatus("200");
            resp.setMessage("CAT_UPD");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/editprod")
    public ResponseEntity<Response> editProduct(@RequestBody Product product) {
        Response resp = new Response();
        try {
            Product prod = prodRepo.findByProductId(product.getProductId());
            if (product.getProductArticle() != 0) {
                prod.setProductArticle(product.getProductArticle());
            }
            if (product.getProductImg() != null) {
                prod.setProductImg(product.getProductImg());
            }
            if (product.getProductPrice() != 0) {
                prod.setProductPrice(product.getProductPrice());
            }
            if (product.getProductQuantity() != 0) {
                prod.setProductQuantity(product.getProductQuantity());
            }
            if (product.getProductTitle() != null) {
                prod.setProductTitle(product.getProductTitle());
            }
            prodRepo.save(prod);
            resp.setStatus("200");
            resp.setMessage("PROD_UPD");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/delprod")
    public ResponseEntity<Response> deleteProduct(@RequestBody int prodId) {
        Response resp = new Response();
        try {
            prodRepo.deleteByProductId(prodId);
            reviewsRepo.deleteAllByProductId(prodId);
            resp.setStatus("200");
            resp.setMessage("PROD_DEL");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/delcat")
    public ResponseEntity<Response> deleteCategory(@RequestBody int catId) {
        Response resp = new Response();
        try {
            catRepo.deleteByCategoryId(catId);
            resp.setStatus("200");
            resp.setMessage("CAT_DEL");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/addcat")
    public ResponseEntity<Response> addCategory(@RequestBody Category category) {
        Response resp = new Response();
        try {
            Category categorys = new Category();
            categorys.setCategoryImg(category.getCategoryImg());
            categorys.setCategoryTitle(category.getCategoryTitle());
            catRepo.save(categorys);
            resp.setStatus("200");
            resp.setMessage("CAT_ADD");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/addprod")
    public ResponseEntity<Response> addProduct(@RequestBody Product prod) {
        Response resp = new Response();
        try {
            Product product = new Product();
            product.setParentId(prod.getParentId());
            product.setProductArticle(prod.getProductArticle());
            product.setProductImg(prod.getProductImg());
            product.setProductPrice(prod.getProductPrice());
            product.setProductQuantity(prod.getProductQuantity());
            product.setProductTitle(prod.getProductTitle());
            prodRepo.save(product);
            resp.setStatus("200");
            resp.setMessage("PROD_ADD");
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<Response> test() {
        Response resp = new Response();
        resp.setMessage("HERE");
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/getreviews")
    public ResponseEntity<Response> getReviews(@RequestBody int prodId) {
        Response resp = new Response();
        try {
            List<Review> reviews = reviewsRepo.findAllByProductId(prodId);
            resp.setStatus("200");
            resp.setMessage("LIST_REVIEWS");
            resp.setObject(reviews);
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("Error");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/sendreview")
    public ResponseEntity<Response> getReviews(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestParam(name = "orderid") int orderid, @RequestBody Review review) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            Review rev = new Review();
            rev.setProductId(review.getProductId());
            rev.setUserId(loggedUser.getUserid());
            rev.setReviewText(review.getReviewText());
            rev.setRating(review.getRating());
            Date date = new Date();
            rev.setReviewAdded(date);
            reviewsRepo.save(rev);
            AdminOrder aord = aOrderRepo.findByOrderIdAndProductId(orderid, review.getProductId());
            aord.setReviewed(1);
            aOrderRepo.save(aord);
            int total = 0;
            List<Review> reviews = reviewsRepo.findAllByProductId(review.getProductId());
            for (Review revs : reviews) {
                total += (revs.getRating());
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            String result = df.format(((double) total / (double) reviews.size()));
            result = result.replaceAll(",",".");
            Product prod = prodRepo.findByProductId(review.getProductId());
            prod.setRating(result);
            prodRepo.save(prod);
            resp.setStatus("200");
            resp.setMessage("REVIEW_ADDED");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }
}