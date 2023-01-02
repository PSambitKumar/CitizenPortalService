package com.sambit.citizenportalservice.service;

import com.sambit.citizenportalservice.model.Country;

import java.util.List;
import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 01/01/2023 - 9:11 PM
 */
public interface MainService {
    List<Map<String, Object>> getURL(String userId);
    List<Country> getCountryList();
}
