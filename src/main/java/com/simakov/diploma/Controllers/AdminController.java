package com.simakov.diploma.Controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.UserRepository;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.jwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private jwtUtil jwtutil;

    @PostMapping("/login")
    public ResponseEntity<Response> verifyUser(@RequestBody User user) {
        User loggedUser = userRepo.findByPhoneAndUsertype(user.getPhone(), "admin");
        Response resp = new Response();
        if (loggedUser != null) {
            String jwtToken = jwtutil.createToken(loggedUser.getPhone(), loggedUser.getPassword(), "admin", 0);
            resp.setStatus("200");
            resp.setMessage("SUCCESS");
            resp.setAUTH_TOKEN(jwtToken);

        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @PostMapping("/loginpass")
    public ResponseEntity<Response> verifyPassAdmin(@RequestBody User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User loggedUser = userRepo.findByPhoneAndUsertype(user.getPhone(), "admin");
        Response resp = new Response();
        if (loggedUser != null) {
            if (passwordEncoder.matches(user.getPassword(), loggedUser.getPassword())) {
                String jwtToken = jwtutil.createToken(loggedUser.getPhone(), loggedUser.getPassword(), "admin", 0);
                resp.setStatus("200");
                resp.setMessage("SUCCESS");
                resp.setAUTH_TOKEN(jwtToken);
            } else {
                resp.setStatus("500");
                resp.setMessage("ERROR");
            }
        } else {
            resp.setStatus("500");
            resp.setMessage("ERROR");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }
}
