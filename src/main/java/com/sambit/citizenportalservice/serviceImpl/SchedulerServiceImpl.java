package com.sambit.citizenportalservice.serviceImpl;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.repository.CountryRepository;
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
                url.set(resourceBundle.getString("API-Ninjas.countryInfo.link") +
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
}
