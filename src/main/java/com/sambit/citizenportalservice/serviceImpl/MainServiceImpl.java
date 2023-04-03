package com.sambit.citizenportalservice.serviceImpl;

import com.sambit.citizenportalservice.model.*;
import com.sambit.citizenportalservice.repository.*;
import com.sambit.citizenportalservice.service.MainService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 01/01/2023 - 9:12 PM
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<Map<String, Object>> getURL(String userId) {
        List<Map<String, Object>> response = new ArrayList<>();
        Map<String, Object> urlMap;
        List<Map<String, Object>> urlMapList;
        try {
            List<String> urlGroupNameList = urlRepository.findDistinctURLGroupNameByUserId(Long.parseLong(userId));
            List<URL> urlList = urlRepository.findAllByUserId(Long.parseLong(userId));
            if (urlList.size() > 0) {
                for (String groupName : urlGroupNameList) {
                    urlMap = new LinkedHashMap<>();
                    urlMapList = new ArrayList<>();
                    urlMap.put("urlGroupName", groupName);
                    for (URL url : urlList) {
                        if (url.getUrlGroup().getUrlGroupName().equals(groupName)) {
                            urlMapList.add(Map.of("urlName", url.getUrlName(), "routerLink", url.getRouterLink()));
                        }
                    }
                    urlMap.put("urlList", urlMapList);
                    response.add(urlMap);
                }
//                System.out.println("Response : " + response);
            } else {
                throw new Exception("No URL Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public List<Country> getCountryList() {
        return countryRepository.findAll();
    }

    @Override
    public Boolean isCountryExist(String body) throws JSONException {
        return countryRepository.findByCountryName(new JSONObject(body).getString("countryName")) != null;
    }

    @Override
    public Country addCountryData(String body) {
        Country country = new Country();
        try {
            JSONObject jsonObject = new JSONObject(body);
            country.setCountryName(jsonObject.getString("countryName"));
            country.setActive(jsonObject.getBoolean("status"));
            country.setCountryCode(jsonObject.getString("countryCode"));
            country = countryRepository.save(country);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return country;
    }

    @Override
    public Country getCountryById(Long countryId) {
        return countryRepository.findByCountryId(countryId);
    }

    @Override
    public boolean deleteCountryById(Long countryId) {
        try {
            countryRepository.deleteById(countryId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<State> getAllStateList() {
        return stateRepository.findAll();
    }

    @Override
    public List<District> getAllDistrictList() {
        return districtRepository.findAll();
    }

    @Override
    public List<State> getStateListByCountryId(Long countryId) {
        return stateRepository.findAllStatesByCountryId(countryId);
    }

    @Override
    public District addDistrictData(String body) {
        District district = new District();
        try {
            JSONObject jsonObject = new JSONObject(body);
            district.setDistrictName(jsonObject.getString("districtName"));
            district.setState(stateRepository.getStateByStateId(jsonObject.getLong("stateId")));
            district.setStatus(jsonObject.getBoolean("status"));
            district =  districtRepository.save(district);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return district;
    }
}
