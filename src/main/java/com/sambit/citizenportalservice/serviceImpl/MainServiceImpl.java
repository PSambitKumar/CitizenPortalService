package com.sambit.citizenportalservice.serviceImpl;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.model.URL;
import com.sambit.citizenportalservice.model.URLGroup;
import com.sambit.citizenportalservice.repository.CountryRepository;
import com.sambit.citizenportalservice.repository.URLGroupRepository;
import com.sambit.citizenportalservice.repository.URLRepository;
import com.sambit.citizenportalservice.repository.UserRepository;
import com.sambit.citizenportalservice.service.MainService;
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
                System.out.println("Response : " + response);
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
}
