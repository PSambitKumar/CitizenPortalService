package com.sambit.citizenportalservice.controller;

import com.sambit.citizenportalservice.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 08/01/2023 - 12:15 AM
 */

@Component
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    private static final Logger logger = LoggerFactory.getLogger(SchedulerController.class);

//    Run Every Day at 01:35 PM
//    @Scheduled(cron = "0 45 13 * * ?")
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void fetchCountryDetailsByCountryCode(){
        System.out.println("Inside Fetch Country Details By Country Code Method.");
        try {
            schedulerService.fetchCountryDetailsByCountryCodeFromBigDataCloud();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Run Every Day at 04:05 PM
//    @Scheduled(cron = "0 5 16 * * ?")
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void fetchCountryDetailsByCountryNameFromAPINinjas() {
        System.out.println("Inside Fetch Country Details By Country Name Method.");
        try {
            schedulerService.fetchCountryDetailsByCountryNameFromAPINinjas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(cron = "0 33 0 * * ?")
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void fetchStateListByCountryNameFromUniversalTutorial(){
        System.out.println("Inside Fetch State List By Country Name Method.");
        try {
          schedulerService.fetchStateListByCountryNameFromUniversalTutorial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 0 0 1 1 ?")
    public void fetchDistrictDataListByStateNameFromUniversalTutorial(){
        System.out.println("Inside Fetch District Data List By State Name Method.");
        try {
            schedulerService.fetchDistrictDataListByStateNameFromUniversalTutorial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}