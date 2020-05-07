package com.simakov.diploma.Controllers;

import java.io.IOException;
import java.util.List;

import com.simakov.diploma.Model.Cart;
import com.simakov.diploma.Model.Promo;
import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.CartRepository;
import com.simakov.diploma.Repository.PromoRepository;
import com.simakov.diploma.Repository.UserRepository;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.jwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class PromoController {

    @Autowired
    private PromoRepository promoRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private jwtUtil jwtutil;

    @PostMapping("/getpromo")
    public ResponseEntity<Response> getPromo(@RequestBody String title) throws IOException {
        Response resp = new Response();
        try {
            Promo promo = promoRepo.findByPromoCode(title);
            resp.setStatus("200");
            resp.setMessage("LIST ITEMS");
            // resp.setObject(promoRepo.findAll());
            resp.setObject(promo);
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/getpromoid")
    public ResponseEntity<Response> getPromoId(@RequestBody Integer promoId) throws IOException {
        Response resp = new Response();
        try {
            Promo promo = promoRepo.findByPromoId(promoId);
            resp.setStatus("200");
            resp.setMessage("LIST ITEMS");
            resp.setObject(promo);
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addpromo")
    public ResponseEntity<Response> addPromo(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody Integer promoId) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            List<Cart> cartlist = cartRepo.findAllByUuid(loggedUser.getUuid());
            for (Cart cart : cartlist) {
                cart.setPromoId(promoId);
                cartRepo.save(cart);
            }
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
        } else if (AUTH_TOKEN != null) {
            List<Cart> cartlist = cartRepo.findAllByUuid(AUTH_TOKEN);
            for (Cart cart : cartlist) {
                cart.setPromoId(promoId);
                cartRepo.save(cart);
            }
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/delpromo")
    public ResponseEntity<Response> delPromo(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
        Response resp = new Response();
        if (jwtutil.checkToken(AUTH_TOKEN) != null) {
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            List<Cart> cartlist = cartRepo.findAllByUuid(loggedUser.getUuid());
            for (Cart cart : cartlist) {
                cart.setPromoId(0);
                cartRepo.save(cart);
            }
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
        } else if (AUTH_TOKEN != null) {
            List<Cart> cartlist = cartRepo.findAllByUuid(AUTH_TOKEN);
            for (Cart cart : cartlist) {
                cart.setPromoId(0);
                cartRepo.save(cart);
            }
            resp.setStatus("200");
            resp.setMessage("GET_ORDERS");
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }
}