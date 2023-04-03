package com.sambit.citizenportalservice.controller;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.model.District;
import com.sambit.citizenportalservice.model.State;
import com.sambit.citizenportalservice.service.MainService;
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
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping(value = "/getURL")
    public ResponseEntity<?> getURL(@RequestParam(value = "userId") String userId) {
        System.out.println("Inside Get URL Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String, Object>> urlList;
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

    @PostMapping(value = "/addCountryData")
    public ResponseEntity<?> addCountryData(@RequestBody String body) {
        System.out.println("Inside Add Country Data Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        Country country;
        try {
            System.out.println("Body : " + body);
            if (!mainService.isCountryExist(body)) {
                country = mainService.addCountryData(body);
                if (country.getCountryId() != null) {
                    response.put("statusCode", HttpStatus.OK.value());
                    response.put("status", "Success");
                    response.put("message", country.getCountryName() + " Added Successfully.");
                    response.put("data", country);
                } else {
                    response.put("statusCode", HttpStatus.NOT_FOUND.value());
                    response.put("status", "Failure");
                    response.put("message", "Country Data Not Added.");
                }
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "Country Already Exist.");
            }

        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getCountryById")
    public ResponseEntity<?> getCountryById(@RequestParam(value = "countryId") Long countryId) {
        System.out.println("Inside Get Country By Id Method.");
        System.out.println("Country Id : " + countryId);
        Map<String, Object> response = new LinkedHashMap<>();
        Country country;
        try {
            country = mainService.getCountryById(countryId);
            System.out.println("Country Details : " + country);
            if (country != null) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "Country Data Fetched Successfully.");
                response.put("data", country);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "Country Data Not Found.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCountryById")
    public ResponseEntity<?> deleteCountryById(@RequestParam(value = "countryId") Long countryId) {
        System.out.println("Inside Delete Country By Id Method.");
        System.out.println("Country Id : " + countryId);
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            if (mainService.deleteCountryById(countryId)) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "Country Data Deleted Successfully.");
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "Country Data Not Found.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllStateList")
    public ResponseEntity<?> getAllStateList() {
        System.out.println("Inside Get All State List Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        List<State> stateList;
        try {
            stateList = mainService.getAllStateList();
            System.out.println("State List : " + stateList);
            if (stateList != null && stateList.size() > 0) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "State List Fetched Successfully.");
                response.put("data", stateList);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "State List Not Found.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        System.out.println("Response : " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllDistrictList")
    public ResponseEntity<?> getAllDistrictList(){
        System.out.println("Inside Get All District List Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        List<District> districtList;
        try {
            districtList = mainService.getAllDistrictList();
            if (districtList != null && districtList.size() > 0) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "District List Fetched Successfully.");
                response.put("data", districtList);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "District List Not Found.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getStateListByCountryId")
    public ResponseEntity<?> getStateListByCountryId(@RequestParam(value = "countryId", required = false) Long countryId) {
        System.out.println("Inside Get State List By Country Id Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        List<State> stateList;
        try {
            stateList = mainService.getStateListByCountryId(countryId);
            if (stateList.size() > 0) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", "State List Fetched Successfully.");
                response.put("data", stateList);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "No State List Assigned To This Country.");
            }
            System.out.println("State List : " + stateList);
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/addDistrictData")
    public ResponseEntity<?> addDistrictData(@RequestBody String body) {
        System.out.println("Inside Add District Data Method.");
        Map<String, Object> response = new LinkedHashMap<>();
        District district;
        try {
            district = mainService.addDistrictData(body);
            if (district.getDistrictId() != null) {
                response.put("statusCode", HttpStatus.OK.value());
                response.put("status", "Success");
                response.put("message", district.getDistrictName() + " Added Successfully.");
                response.put("data", district);
            } else {
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                response.put("status", "Failure");
                response.put("message", "District Data Not Added.");
            }
        } catch (Exception e) {
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
