package com.sambit.citizenportalservice.service;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 08/01/2023 - 12:46 PM
 */
public interface SchedulerService {

    void fetchCountryDetailsByCountryCodeFromBigDataCloud();
    void fetchCountryDetailsByCountryNameFromAPINinjas();
    void fetchStateListByCountryNameFromUniversalTutorial();
    void fetchDistrictDataListByStateNameFromUniversalTutorial();
}
