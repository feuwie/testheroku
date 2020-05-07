package com.simakov.diploma.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.UserRepository;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.Validator;
import com.simakov.diploma.Utilities.jwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private jwtUtil jwtutil;

    @PostMapping("/registration")
    public ResponseEntity<Response> addUser(@RequestBody User user) {
        Response resp = new Response();
        try {
            if (Validator.isUserEmpty(user)) {
                resp.setStatus("400");
                resp.setMessage("BAD_REQUEST");
                // } else if (!Validator.isValidEmail(user.getEmail())) {
                // resp.setStatus("400");
                // resp.setMessage("INVALID_EMAIL");
            } else {
                resp.setStatus("200");
                resp.setMessage("REGISTERED");
                String pass = user.getPassword();
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(pass);
                user.setPassword(hashedPassword);
                User reg = userRepo.save(user);
                resp.setObject(reg);
            }
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> verifyUser(@RequestBody User user) {
        User loggedUser = userRepo.findByPhoneAndUsertype(user.getPhone(), user.getUsertype());
        Response resp = new Response();
        if (loggedUser != null) {
            if (loggedUser.getBan() == 0) {
                String jwtToken = jwtutil.createToken(user.getPhone(), loggedUser.getPassword(), user.getUsertype(), 0);
                resp.setStatus("200");
                resp.setMessage("SUCCESS");
                resp.setAUTH_TOKEN(jwtToken);
            } else {
                resp.setStatus("500");
                resp.setMessage("YOU ARE BANNED");
            }
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/loginpass")
    public ResponseEntity<Response> verifyPassUser(@RequestBody User user) {
        User loggedUser = userRepo.findByPhoneAndUsertype(user.getPhone(), "customer");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Response resp = new Response();
        if (loggedUser != null) {
            if (loggedUser.getBan() == 0) {
                if (passwordEncoder.matches(user.getPassword(), loggedUser.getPassword())) {
                    String jwtToken = jwtutil.createToken(loggedUser.getPhone(), loggedUser.getPassword(),
                            loggedUser.getUsertype(), 0);
                    resp.setStatus("200");
                    resp.setMessage("SUCCESS");
                    resp.setAUTH_TOKEN(jwtToken);
                } else {
                    resp.setStatus("500");
                    resp.setMessage("ERROR");
                }
            } else {
                resp.setStatus("500");
                resp.setMessage("YOU ARE BANNED");
            }
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @GetMapping("/loginanon")
    public ResponseEntity<Response> loginAnon() {
        UUID corrId = UUID.randomUUID();
        Response resp = new Response();
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        resp.setObject(corrId);
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }
}