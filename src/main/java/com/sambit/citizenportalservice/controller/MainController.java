package com.sambit.citizenportalservice.controller;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.service.MainService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 01/01/2023 - 8:41 PM
 */
@RestController
@RequestMapping("/api/main")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping(value = "/getURL")
    public ResponseEntity<?> getURL(@RequestParam(value = "userId") String userId) {
        System.out.println("Inside Get URL Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String, Object>> urlList = null;
        try {
            urlList = mainService.getURL(userId);
            if (urlList != null && urlList.size() > 0) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "URL List Fetched Successfully.");
                response.put("data", urlList);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "URL List Not Found.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getCountryList")
    public ResponseEntity<?> getCountryList() {
        System.out.println("Inside Get Country List Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        List<Country> countryList;
        try {
            countryList = mainService.getCountryList();
            if (countryList != null && countryList.size() > 0) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "Country List Fetched Successfully.");
                response.put("data", countryList);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "Country List Not Found.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
