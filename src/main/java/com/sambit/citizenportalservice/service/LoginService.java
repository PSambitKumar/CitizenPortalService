package com.sambit.citizenportalservice.service;

import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 5:54 PM
 */
public interface LoginService {
    public Map<String, Object> loginUser(String userName, String password) throws Exception;
}
