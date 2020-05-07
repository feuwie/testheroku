package com.simakov.diploma.Controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.simakov.diploma.Constants.Constants;
import com.simakov.diploma.Model.AdminOrder;
import com.simakov.diploma.Model.Cart;
import com.simakov.diploma.Model.PersInfo;
import com.simakov.diploma.Model.PlaceOrder;
import com.simakov.diploma.Model.Product;
import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.AdminOrderRepository;
import com.simakov.diploma.Repository.CartRepository;
import com.simakov.diploma.Repository.OrderRepository;
import com.simakov.diploma.Repository.PersInfoRepository;
import com.simakov.diploma.Repository.ProductRepository;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.Validator;
import com.simakov.diploma.Utilities.jwtUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private AdminOrderRepository adminOrderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private PersInfoRepository persRepo;

    @Autowired
    private jwtUtil jwtutil;

    @PostMapping("/makeorder")
    public ResponseEntity<Response> makeOrder(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody PlaceOrder address, @RequestParam(name = "phone") String phone) throws IOException {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            PlaceOrder order = new PlaceOrder();
            order.setUuid(loggedUser.getUuid());
            Date date = new Date();
            order.setOrderDate(date);
            order.setOrderStatus("На складе");
            order.setComment(address.getComment());
            // order.setPhone(address.getPhone());
            // order.setEmail(address.getEmail());
            order.setPaymentId(address.getPaymentId());
            int total = 0;
            List<Cart> cartlist = cartRepo.findAllByUuid(loggedUser.getUuid());
            for (Cart cart : cartlist) {
                Product prod = productRepo.findByProductId(cart.getProductId());
                total = total + (cart.getProductQuantity() * prod.getProductPrice());
                prod.setProductQuantity(prod.getProductQuantity() - cart.getProductQuantity());
            }
            order.setOrderCost(total);
            PlaceOrder res = orderRepo.save(order);
            cartlist.forEach((cart) -> {
                AdminOrder ord = new AdminOrder();
                ord.setOrderId(res.getOrderId());
                ord.setProductId(cart.getProductId());
                ord.setProductQuantity(cart.getProductQuantity());
                ord.setUuid(cart.getUuid());
                adminOrderRepo.save(ord);
            });
            cartRepo.deleteAllByUuid(loggedUser.getUuid());

            Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            System.out.println(phone);
            String string = String.format("Поздравляем с успешной покупкой! Ваш номер заказа %2d", res.getOrderId());
            Message message = Message.creator(new PhoneNumber(phone), new PhoneNumber("+13343445022"), string)
                    .create();
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(res.getOrderId());
        } else if (AUTH_TOKEN != null) {
            PlaceOrder order = new PlaceOrder();
            order.setUuid(AUTH_TOKEN);
            Date date = new Date();
            order.setOrderDate(date);
            order.setOrderStatus("На складе");
            order.setComment(address.getComment());
            // order.setPhone(address.getPhone());
            order.setPaymentId(address.getPaymentId());
            int total = 0;
            List<Cart> cartlist = cartRepo.findAllByUuid(AUTH_TOKEN);
            for (Cart cart : cartlist) {
                Product prod = productRepo.findByProductId(cart.getProductId());
                total = total + (cart.getProductQuantity() * prod.getProductPrice());
                prod.setProductQuantity(prod.getProductQuantity() - cart.getProductQuantity());
            }
            order.setOrderCost(total);
            PlaceOrder res = orderRepo.save(order);
            cartlist.forEach((cart) -> {
                AdminOrder ord = new AdminOrder();
                ord.setOrderId(res.getOrderId());
                ord.setProductId(cart.getProductId());
                ord.setProductQuantity(cart.getProductQuantity());
                ord.setUuid(cart.getUuid());
                adminOrderRepo.save(ord);
            });
            cartRepo.deleteAllByUuid(AUTH_TOKEN);
            Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            String string = String.format("Поздравляем с успешной покупкой! Ваш номер заказа %2d", res.getOrderId());
            Message message = Message.creator(new PhoneNumber(phone), new PhoneNumber("+13343445022"), string)
                    .create();
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(res.getOrderId());
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/orderstatus")
    public ResponseEntity<Response> orderStatus(@RequestBody int order) throws IOException {
        Response resp = new Response();
        try {
            PlaceOrder ord = orderRepo.findByOrderId(order);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setObject(ord);
        } catch (Exception err) {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/biochange")
    public ResponseEntity<Response> bioChange(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody PersInfo pers) throws IOException {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            PersInfo perse = new PersInfo();
            perse.setUuid(loggedUser.getUuid());
            perse.setAddress(pers.getAddress());
            perse.setFullname(pers.getFullname());
            // perse.setDob(pers.getDob());
            perse.setDoor(pers.getDoor());
            perse.setDoorphone(pers.getDoorphone());
            perse.setFlat(pers.getFlat());
            perse.setFloor(pers.getFloor());
            perse.setEmail(pers.getEmail());
            perse.setPhone(pers.getPhone());
            // perse.setGender(pers.getGender());
            perse.setInd(pers.getInd());
            perse.setOrderId(pers.getOrderId());
            persRepo.save(perse);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } else if (AUTH_TOKEN != null) {
            PersInfo perse = new PersInfo();
            perse.setUuid(AUTH_TOKEN);
            perse.setAddress(pers.getAddress());
            perse.setEmail(pers.getEmail());
            perse.setDoor(pers.getDoor());
            perse.setDoorphone(pers.getDoorphone());
            perse.setFlat(pers.getFlat());
            perse.setFloor(pers.getFloor());
            perse.setFullname(pers.getFullname());
            perse.setPhone(pers.getPhone());
            perse.setInd(pers.getInd());
            perse.setOrderId(pers.getOrderId());
            persRepo.save(perse);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

}