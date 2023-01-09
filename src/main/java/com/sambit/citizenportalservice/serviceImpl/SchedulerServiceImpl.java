package com.sambit.citizenportalservice.serviceImpl;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.model.State;
import com.sambit.citizenportalservice.repository.CountryRepository;
import com.sambit.citizenportalservice.repository.StateRepository;
import com.sambit.citizenportalservice.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 08/01/2023 - 12:47 PM
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("file-location");

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    public void fetchCountryDetailsByCountryCodeFromBigDataCloud() {
        AtomicReference<String> nativeLanguage = new AtomicReference<>("");
        AtomicReference<RestTemplate> restTemplate = new AtomicReference<>();
        AtomicReference<Map<?, ?>> response = new AtomicReference<>();
        AtomicReference<List<Map<String, Object>>> languages = new AtomicReference<>();
        List<Country> updatedCountryList = new ArrayList<>(), countryList;
        try {
            countryList = countryRepository.findAll();
            countryList.forEach(country -> {
                restTemplate.set(new RestTemplate());
                String url = resourceBundle.getString("bigDataCloud.countryInfo.link") +
                        "?code=" + country.getCountryCode() +
                        "&key=" + resourceBundle.getString("bigDataCloud.API.key"
                );
                response.set(restTemplate.get().getForObject(url, Map.class));
                if (response.get() != null) {
                    country.setIsoFullName((String) response.get().get("isoNameFull"));
                    country.setRegion((String) response.get().get("unRegion"));
                    country.setPhoneCode(String.valueOf(response.get().get("callingCode")));
                    languages.set((List<Map<String, Object>>) response.get().get("isoAdminLanguages"));
                    if (languages.get() != null) {
                        for (Map<String, Object> language : languages.get()) {
                            nativeLanguage.set(nativeLanguage.get() + language.get("nativeName") + ",");
                        }
                    }
                    country.setNativeLanguage(nativeLanguage.get());
                    System.out.println("Country : " + country);
                    updatedCountryList.add(country);
                }
                restTemplate.set(null);
                response.set(null);
                languages.set(null);
                nativeLanguage.set("");
            });
            countryRepository.saveAll(updatedCountryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fetchCountryDetailsByCountryNameFromAPINinjas() {
        AtomicReference<String> url = new AtomicReference<>();
        AtomicReference<RestTemplate> restTemplate = new AtomicReference<>();
        AtomicReference<List<Map<?, ?>>> response = new AtomicReference<>();
        List<Country> countryList, updatedCountryList = new ArrayList<>();
        try {
            countryList = countryRepository.findAll();
            countryList.forEach(country -> {
                restTemplate.set(new RestTemplate());
                url.set(resourceBundle.getString("API.Ninjas.countryInfo.link") +
                        "?name=" + country.getCountryName()
                );
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-Api-Key", resourceBundle.getString("API.Ninjas.API.key"));
                HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
                response.set(restTemplate.get().exchange(url.get(), HttpMethod.GET, entity, List.class).getBody());
                if (response.get() != null && response.get().size() > 0) {
                    System.out.println("Response : " + response.get().get(0));
                    country.setCapital((String) response.get().get(0).get("capital"));
                    country.setPopulation(String.valueOf(response.get().get(0).get("population")));
                    updatedCountryList.add(country);
                }
                response.set(null);
                restTemplate.set(null);
                url.set("");
            });
            countryRepository.saveAll(updatedCountryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fetchStateListByCountryNameFromUniversalTutorial() {
        String authToken;
        String url, url1;
        List<Country> countryList; List<?> response1 ;
        RestTemplate restTemplate, restTemplate1;
        HttpHeaders headers, headers1;
        HttpEntity<String> header, header1;
        Map<?, ?> response;
        List<State> stateList;
        State state;
        try {
            url = resourceBundle.getString("universal.tutorial.generateToken.link");
            restTemplate = new RestTemplate();
            headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Accept", "application/json");
            headers.set("api-token", resourceBundle.getString("universal.tutorial.apiToken"));
            headers.set("user-email", resourceBundle.getString("universal.tutorial.registerEmail"));
            header = new HttpEntity<>("parameters", headers);
            response = restTemplate.exchange(url, HttpMethod.GET, header, Map.class).getBody();
            if (response != null) {
                authToken = (String) response.get("auth_token");
                System.out.println("AuthToken : " + authToken);
                countryList = countryRepository.findAll();
                if (countryList.size() > 0) {
                    stateList = new ArrayList<>();
                    for (Country country : countryList) {
                        if (country.getCountryName().equalsIgnoreCase("Guinea‐Bissau") ||
                                country.getCountryName().equalsIgnoreCase("Timor‐Leste"))
                            continue;
                        url1 = (resourceBundle.getString("universal.tutorial.countryStateList.link") + country.getCountryName());
                        restTemplate1 = new RestTemplate();
                        headers1 = new HttpHeaders();
                        headers1.set("Content-Type", "application/json");
                        headers1.set("Accept", "application/json");
                        headers1.set("Authorization", "Bearer " + authToken);
                        header1 = new HttpEntity<>("parameters", headers1);
                        response1 = restTemplate1.exchange(url1, HttpMethod.GET, header1, List.class).getBody();
                        if (response1 != null && response1.size() > 0) {
                            System.out.println("URL : " + url1);
                            System.out.println("Response : " + response1);
                            for (Object stateObj : response1) {
                                state = new State();
                                System.out.println("State Name : " + ((Map<?, ?>) stateObj).get("state_name"));
                                state.setStateName((String) ((Map<?, ?>) stateObj).get("state_name"));
                                state.setCountry(country);
                                stateList.add(state);
                            }
                        }
                    }
                    stateList.forEach(System.out::println);
                    stateRepository.saveAll(stateList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
