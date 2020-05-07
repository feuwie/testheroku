package com.simakov.diploma.Controllers;

// import com.nexmo.client.verify.VerifyRequest;
// import com.simakov.diploma.Model.Code;
import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.UserRepository;
// import com.simakov.diploma.Model.User;
import com.simakov.diploma.Response.Response;
import com.simakov.diploma.Utilities.jwtUtil;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.exception.*;

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

import com.simakov.diploma.Constants.Constants;

@CrossOrigin(origins = "*")
@RestController
public class VerifyController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private jwtUtil jwtutil;

    @PostMapping("/verifyphone")
    public ResponseEntity<Response> verifyPhone(@RequestBody User user) {
        Response resp = new Response();
        try {
            Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            Verification verification = Verification.creator(Constants.SERVICE_ID, user.getPhone(), "sms").create();
            resp.setStatus("200");
            resp.setMessage("PASS TO CODE");
            resp.setObject(verification.getServiceSid());
        } catch (ApiException err) {
            resp.setStatus(Integer.toString(err.getCode()));
            resp.setMessage(err.getMessage());
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkphone")
    // public ResponseEntity<Response> checkPhone(@RequestBody Code code) {
    public ResponseEntity<Response> checkPhone(@RequestParam(name = "str") String str,
            @RequestParam(name = "code") String code, @RequestParam(name = "phone") String phone) {
        Response resp = new Response();
        try {
            Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            // VerificationCheck verificationCheck =
            // VerificationCheck.creator(code.getStr(), code.getCode())
            // .setTo(code.getPhone()).create();
            VerificationCheck verificationCheck = VerificationCheck.creator(str, code).setTo(phone).create();
            resp.setStatus("200");
            resp.setMessage("PASS TO CODE");
            resp.setObject(verificationCheck.getStatus());
        } catch (ApiException err) {
            resp.setStatus(Integer.toString(err.getCode()));
            resp.setMessage(err.getMessage());
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/verifyemail")
    public ResponseEntity<Response> verifyEmail(@RequestBody String email) {
        Response resp = new Response();
        try {
            Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            Verification verification = Verification.creator(Constants.SERVICE_ID, email, "email")
                    .create();
            resp.setStatus("200");
            resp.setMessage("PASS TO CODE");
            resp.setObject(verification.getServiceSid());
        } catch (ApiException err) {
            resp.setStatus(Integer.toString(err.getCode()));
            resp.setMessage(err.getMessage());
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkemail")
    // public ResponseEntity<Response> checkPhone(@RequestBody Code code) {
    public ResponseEntity<Response> checkEmail(@RequestParam(name = "code") String code,
            @RequestParam(name = "email") String email) {
        Response resp = new Response();
        try {
            Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            VerificationCheck verificationCheck = VerificationCheck.creator(Constants.SERVICE_ID, code)
                    .setTo(email).create();
            resp.setStatus("200");
            resp.setMessage("PASS TO CODE");
            resp.setObject(verificationCheck.getStatus());
        } catch (ApiException err) {
            resp.setStatus(Integer.toString(err.getCode()));
            resp.setMessage(err.getMessage());
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addemail")
    // public ResponseEntity<Response> checkPhone(@RequestBody Code code) {
    public ResponseEntity<Response> addEmail(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
            @RequestBody String email) {
        Response resp = new Response();
        try {
            // Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
            // VerificationCheck verificationCheck =
            // VerificationCheck.creator(Constants.SERVICE_ID, code)
            // .setTo("kakas789@mail.ru").create();
            User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
            User user = userRepo.findByPhoneAndUsertype(loggedUser.getPhone(), loggedUser.getUsertype());
            user.setEmail(email);
            userRepo.save(user);
            resp.setStatus("200");
            resp.setMessage("PASS TO CODE");
            // resp.setObject(verificationCheck.getStatus());
        } catch (ApiException err) {
            resp.setStatus(Integer.toString(err.getCode()));
            resp.setMessage(err.getMessage());
        } catch (Exception e) {
            resp.setStatus("500");
            resp.setMessage(e.getMessage());
        }
        return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
    }
}