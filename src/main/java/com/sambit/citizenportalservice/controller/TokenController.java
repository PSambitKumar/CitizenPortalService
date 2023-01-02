package com.sambit.citizenportalservice.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sambit.citizenportalservice.service.TokenService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 4:58 PM
 */
@RestController
@RequestMapping("/api/token")
@CrossOrigin(origins = "http://localhost:4200")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "/generate")
    public ResponseEntity<?> generateRandomToken(@RequestParam(value = "userName") String userName) {
        System.out.println("Inside Generate Random Token Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            response.put("statusCode", HttpStatus.OK.value());
            response.put("status", "Success");
            response.put("message", "Token Generated Successfully.");
            response.put("token", tokenService.generateToken(userName));
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
