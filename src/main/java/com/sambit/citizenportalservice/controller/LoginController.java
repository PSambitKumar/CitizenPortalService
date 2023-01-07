package com.sambit.citizenportalservice.controller;

import com.sambit.citizenportalservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 5:40 PM
 */
@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/user")
    public ResponseEntity<?> loginUser(@RequestParam(value = "userName") String userName,
                                       @RequestParam(value = "password") String password) {
        System.out.println("Inside Login User Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            response.put("statusCode", HttpStatus.OK.value());
            response.put("status", "Success");
            response.put("message", "User Logged In Successfully.");
            response.put("userDetails", loginService.loginUser(userName, password));
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
