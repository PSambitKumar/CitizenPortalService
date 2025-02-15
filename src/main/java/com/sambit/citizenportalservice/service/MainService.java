package com.sambit.citizenportalservice.service;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.model.District;
import com.sambit.citizenportalservice.model.State;
import org.json.JSONException;

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
    Boolean isCountryExist(String body) throws JSONException;
    Country addCountryData(String body);
    Country getCountryById(Long countryId);
    boolean deleteCountryById(Long countryId);
    List<State> getAllStateList();
    List<District> getAllDistrictList();
    List<State> getStateListByCountryId(Long countryId);
    District addDistrictData(String body);
}
