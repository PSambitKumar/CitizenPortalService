package com.sambit.citizenportalservice.controller;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.repository.CountryRepository;
import com.sambit.citizenportalservice.service.SchedulerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 08/01/2023 - 12:15 AM
 */

@Component
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

//    Run Every Day at 01:35 PM
    @Scheduled(cron = "0 45 13 * * ?")
    public void fetchCountryDetailsByCountryCode(){
        System.out.println("Inside Fetch Country Details By Country Code Method.");
        try {
            schedulerService.fetchCountryDetailsByCountryCodeFromBigDataCloud();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Run Every Day at 04:05 PM
    @Scheduled(cron = "0 5 16 * * ?")
    public void fetchCountryDetailsByCountryNameFromAPINinjas() {
        System.out.println("Inside Fetch Country Details By Country Name Method.");
        try {
            schedulerService.fetchCountryDetailsByCountryNameFromAPINinjas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
