package com.simakov.diploma.Controllers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simakov.diploma.Constants.Constants;
import com.simakov.diploma.Model.Blind;
import com.simakov.diploma.Model.Cart;
import com.simakov.diploma.Model.Payment;
import com.simakov.diploma.Model.PersInfo;
import com.simakov.diploma.Model.Product;
import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.BlindRepository;
import com.simakov.diploma.Repository.CartRepository;
import com.simakov.diploma.Repository.PersInfoRepository;
import com.simakov.diploma.Repository.ProductRepository;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.Validator;
import com.simakov.diploma.Utilities.jwtUtil;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    List<Integer> list;
    List<Object> lister;

    String uuid;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private BlindRepository blindRepo;

    @Autowired
    private PersInfoRepository persRepo;

    @Autowired
    private jwtUtil jwtutil;

    // @GetMapping("/cart")
    // public ResponseEntity<Response> getCart(@RequestHeader(name = "AUTH_TOKEN")
    // String AUTH_TOKEN) throws IOException {
    // Response resp = new Response();
    // // if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN)
    // !=
    // // null) {
    // try {
    // // User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
    // resp.setStatus("200");
    // resp.setMessage("LIST_CART");
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // // resp.setObject(cartRepo.findByUserEmail(loggedUser.getEmail()));
    // } catch (Exception e) {
    // resp.setStatus("500");
    // resp.setMessage(e.getMessage());
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // }
    // // } else {
    // // resp.setStatus("500");
    // // resp.setMessage("ERROR");
    // // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }

    // @PostMapping("/addcart")
    // public ResponseEntity<Response> addCart(@RequestHeader(name = "AUTH_TOKEN")
    // String AUTH_TOKEN,
    // @RequestBody int productId) throws IOException {
    // Response resp = new Response();
    // if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) !=
    // null) {
    // try {
    // User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
    // Product cartItem = productRepo.findByProductId(productId);
    // // cartRepo.dropId();
    // // cartRepo.autoIncrement();
    // // cartRepo.addId();
    // Cart cart = new Cart();
    // Date date = new Date();
    // cart.setCartAdded(date);
    // cart.setProductArticle(cartItem.getProductArticle());
    // cart.setProductId(productId);
    // cart.setProductImg(cartItem.getProductImg());
    // cart.setProductPrice(cartItem.getProductPrice());
    // cart.setProductQuantity(1);
    // cart.setProductTitle(cartItem.getProductTitle());
    // // cart.setUserEmail(loggedUser.getEmail());
    // cartRepo.save(cart);
    // resp.setStatus("200");
    // resp.setMessage("Wishlist updated");
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // } catch (Exception e) {
    // resp.setStatus("500");
    // resp.setMessage(e.getMessage());
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // }
    // } else {
    // resp.setStatus("500");
    // resp.setMessage("ERROR");
    // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }

    // @GetMapping("/updcart")
    // public ResponseEntity<Response> updateCart(@RequestHeader(name =
    // "AUTH_TOKEN") String AUTH_TOKEN,
    // @RequestParam(name = "cartid") String cartid, @RequestParam(name =
    // "quantity") String quantity)
    // throws IOException {
    // Response resp = new Response();
    // if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) !=
    // null) {
    // try {
    // User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
    // Cart updCart = cartRepo.findByCartIdAndUserEmail(Integer.parseInt(cartid),
    // loggedUser.getEmail());
    // updCart.setProductQuantity(Integer.parseInt(quantity));
    // cartRepo.save(updCart);
    // List<Cart> cartlist = cartRepo.findByUserEmail(loggedUser.getEmail());
    // resp.setStatus("200");
    // resp.setMessage("Cart updated");
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // resp.setObject(cartlist);
    // } catch (Exception e) {
    // resp.setStatus("500");
    // resp.setMessage(e.getMessage());
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // }
    // } else {
    // resp.setStatus("500");
    // resp.setMessage("ERROR");
    // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }

    // @PostMapping("/delcart")
    // public ResponseEntity<Response> deleteCart(@RequestHeader(name =
    // "AUTH_TOKEN") String AUTH_TOKEN,
    // @RequestBody int cartid) throws IOException {
    // Response resp = new Response();
    // if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) !=
    // null) {
    // try {
    // User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
    // cartRepo.deleteByCartIdAndUserEmail(cartid, loggedUser.getEmail());
    // List<Cart> cartlist = cartRepo.findByUserEmail(loggedUser.getEmail());
    // resp.setStatus("200");
    // resp.setMessage("Cart deleted");
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // resp.setObject(cartlist);
    // } catch (Exception e) {
    // resp.setStatus("500");
    // resp.setMessage(e.getMessage());
    // resp.setAUTH_TOKEN(AUTH_TOKEN);
    // }
    // } else {
    // resp.setStatus("500");
    // resp.setMessage("ERROR");
    // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }

    // @PostMapping("/payment")
    // public ResponseEntity<Response> paymentInfo(@RequestBody Payment chargeForm)
    // {
    // Token lover = chargeForm.getToken();
    // Response resp = new Response();
    // try {
    // Stripe.apiKey = "sk_test_GKfX0jhismUAg2kdSNzIQaKX00IVS2UVEC";
    // Map<String, Object> params = new HashMap<>();
    // params.put("amount", chargeForm.getUserAmount());
    // params.put("currency", "RUB");
    // params.put("source", lover.getId());
    // Charge.create(params);
    // resp.setStatus("200");
    // resp.setMessage("Order successed");
    // } catch (Exception e) {
    // resp.setStatus("500");
    // resp.setMessage(e.getMessage());
    // }
    // return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    // }

    @GetMapping("/cart")
    public ResponseEntity<Response> getCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) throws IOException {
        Response resp = new Response();

        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("LIST_CART");
            resp.setObject(cartRepo.findByUuid(loggedUser.getUuid()));
        } else if (AUTH_TOKEN != null) {
            resp.setStatus("200");
            resp.setMessage("LIST_CART");
            resp.setObject(cartRepo.findByUuid(AUTH_TOKEN));
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addcart")
    public ResponseEntity<Response> addCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody int productId) throws IOException {
        Response resp = new Response();

        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            Product cartItem = productRepo.findByProductId(productId);
            Cart cart = new Cart();
            Date date = new Date();
            // cart.setCartAdded(date);
            // cart.setProductArticle(cartItem.getProductArticle());
            // cart.setProductId(productId);
            // cart.setProductImg(cartItem.getProductImg());
            // cart.setProductPrice(cartItem.getProductPrice());
            // cart.setProductQuantity(1);
            // cart.setProductTitle(cartItem.getProductTitle());
            // cart.setUuid(loggedUser.getUuid());
            cart.setCartAdded(date);
            cart.setProductId(productId);
            cart.setProductQuantity(1);
            cart.setUuid(loggedUser.getUuid());
            cartRepo.save(cart);
            resp.setStatus("200");
            resp.setMessage("ADD_CART");
        } else if (AUTH_TOKEN != null) {
            Product cartItem = productRepo.findByProductId(productId);
            Cart cart = new Cart();
            Date date = new Date();
            cart.setCartAdded(date);
            cart.setProductId(productId);
            cart.setProductQuantity(1);
            cart.setUuid(AUTH_TOKEN);
            cartRepo.save(cart);
            resp.setStatus("200");
            resp.setMessage("ADD_CART");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/updcart")
    public ResponseEntity<Response> updateCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestParam(name = "cartid") String cartid, @RequestParam(name = "quantity") String quantity)
            throws IOException {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            Cart updCart = cartRepo.findByCartIdAndUuid(Integer.parseInt(cartid), loggedUser.getUuid());
            updCart.setProductQuantity(Integer.parseInt(quantity));
            cartRepo.save(updCart);
            List<Cart> cartlist = cartRepo.findByUuid(loggedUser.getUuid());
            resp.setStatus("200");
            resp.setMessage("UPD_CART");
            resp.setObject(cartlist);
        } else if (AUTH_TOKEN != null) {
            Cart updCart = cartRepo.findByCartIdAndUuid(Integer.parseInt(cartid), AUTH_TOKEN);
            updCart.setProductQuantity(Integer.parseInt(quantity));
            cartRepo.save(updCart);
            List<Cart> cartlist = cartRepo.findByUuid(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("UPD_CART");
            resp.setObject(cartlist);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/delcart")
    public ResponseEntity<Response> deleteCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody int cartid) throws IOException {
        Response resp = new Response();

        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            cartRepo.deleteByCartIdAndUuid(cartid, loggedUser.getUuid());
            List<Cart> cartlist = cartRepo.findByUuid(loggedUser.getUuid());
            resp.setStatus("200");
            resp.setMessage("DEL_CART");
            resp.setObject(cartlist);
        } else if (AUTH_TOKEN != null) {
            cartRepo.deleteByCartIdAndUuid(cartid, AUTH_TOKEN);
            List<Cart> cartlist = cartRepo.findByUuid(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("DEL_CART");
            resp.setObject(cartlist);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/cartid")
    public ResponseEntity<Response> getCartId(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody ArrayList<Integer> cartid) throws IOException {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            ArrayList<Integer> num = new ArrayList<Integer>();
            ArrayList<Object> prod = new ArrayList<Object>();
            for (Integer cart : cartid) {
                num.add(cartRepo.findByCartIdAndUuid(cart, loggedUser.getUuid()).getProductId());
            }
            for (Integer one : num) {
                prod.add(productRepo.findByProductId(one));
            }
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(prod);
        } else if (AUTH_TOKEN != null) {
            ArrayList<Integer> num = new ArrayList<Integer>();
            ArrayList<Object> prod = new ArrayList<Object>();
            for (Integer cart : cartid) {
                num.add(cartRepo.findByCartIdAndUuid(cart, AUTH_TOKEN).getProductId());
            }
            for (Integer one : num) {
                prod.add(productRepo.findByProductId(one));
            }
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(prod);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/payment")
    public ResponseEntity<Response> paymentInfo(@RequestBody Payment chargeForm) {
        Token lover = chargeForm.getToken();
        Response resp = new Response();
        try {
            if (jwtutil.checkToken(chargeForm.getUuid()) != null) {
                User loggedUser = jwtutil.checkToken(chargeForm.getUuid());
                uuid = loggedUser.getUuid();
            } else {
                uuid = chargeForm.getUuid();
            }
            Stripe.apiKey = Constants.STRIPE_APIKEY;
            Map<String, Object> params = new HashMap<>();
            params.put("amount", chargeForm.getUserAmount());
            params.put("currency", "RUB");
            params.put("source", lover.getId());
            params.put("description",
                    "UUID пользователя: " + uuid + ", телефон пользователя: " + chargeForm.getPhone());
            Charge charge = Charge.create(params);
            resp.setStatus("200");
            resp.setMessage("Order successed");
            resp.setObject(charge.getId());
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/blindfont")
    public ResponseEntity<Response> blind(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody Blind blind) {
        Response resp = new Response();

        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            Blind blinde;
            if (blindRepo.findByUuid(loggedUser.getUuid()) != null) {
                blinde = blindRepo.findByUuid(loggedUser.getUuid());
            } else {
                blinde = new Blind();
            }
            blinde.setUuid(loggedUser.getUuid());
            blinde.setFontsize(blind.getFontsize());
            blinde.setLineheight(blind.getLineheight());
            blinde.setColorsite(blind.getColorsite());
            blinde.setBacksite(blind.getBacksite());
            blindRepo.save(blinde);

            // ArrayList<Integer> num = new ArrayList<Integer>();
            // ArrayList<Object> prod = new ArrayList<Object>();
            // for (Integer cart : cartid) {
            // num.add(cartRepo.findByCartIdAndUuid(cart,
            // loggedUser.getUuid()).getProductId());
            // }
            // for (Integer one : num) {
            // prod.add(productRepo.findByProductId(one));
            // }
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            // resp.setObject(prod);
        } else if (AUTH_TOKEN != null) {
            Blind blinde;
            if (blindRepo.findByUuid(AUTH_TOKEN) != null) {
                blinde = blindRepo.findByUuid(AUTH_TOKEN);
            } else {
                blinde = new Blind();
            }
            blinde.setUuid(AUTH_TOKEN);
            blinde.setFontsize(blind.getFontsize());
            blinde.setLineheight(blind.getLineheight());
            blinde.setColorsite(blind.getColorsite());
            blinde.setBacksite(blind.getBacksite());
            blindRepo.save(blinde);
            // Blind blind = blindRepo.findByUuid(AUTH_TOKEN);
            // blind.setFontsize(blindfont);
            // blindRepo.save(blind);
            // ArrayList<Integer> num = new ArrayList<Integer>();
            // ArrayList<Object> prod = new ArrayList<Object>();
            // for (Integer cart : cartid) {
            // num.add(cartRepo.findByCartIdAndUuid(cart, AUTH_TOKEN).getProductId());
            // }
            // for (Integer one : num) {
            // prod.add(productRepo.findByProductId(one));
            // }
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            // resp.setObject(prod);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }

        // List<Blind> blind = blindRepo.findAll();
        // for (Blind blin : blind) {
        // Blind bolder = blin;
        // System.out.println(bolder.getFontsize());
        // bolder.setFontsize(blindfont);
        // blindRepo.save(bolder);
        // }
        // resp.setObject(blind);
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getblind")
    public ResponseEntity<Response> getBlind(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        // List<Blind> blind = blindRepo.findAll();
        // resp.setObject(blind);

        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            Blind blind = blindRepo.findByUuid(loggedUser.getUuid());
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(blind);
        } else if (AUTH_TOKEN != null) {
            Blind blind = blindRepo.findByUuid(AUTH_TOKEN);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(blind);
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getbio")
    public ResponseEntity<Response> getBio() {
        Response resp = new Response();
        try {
            List<PersInfo> pers = persRepo.findAll();
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(pers);
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

}