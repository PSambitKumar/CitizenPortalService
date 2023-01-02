package com.sambit.citizenportalservice.serviceImpl;

import com.sambit.citizenportalservice.model.User;
import com.sambit.citizenportalservice.repository.UserRepository;
import com.sambit.citizenportalservice.service.LoginService;
import com.sambit.citizenportalservice.service.TokenService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 5:55 PM
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager = null;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public Map<String, Object> loginUser(String userName, String password) throws Exception {
        JSONObject jsonObject;
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userName,
                            password
                    )
            );
            System.out.println("Authentication: " + authentication);

            if (authentication.isAuthenticated()) {
                User user = userRepository.findByUserName(userName);
                return Map.of("fullName", user.getFullName(),
                        "userId", user.getUserId(),
                        "mobile", user.getMobile(),
                        "email", user.getEmail(),
                        "dob", user.getDob(),
                        "address", user.getAddress(),
                        "userType", user.getRole().getRoleName(),
                        "authToken", "Bearer " + tokenService.generateToken(authentication.getName())
                );
            } else {
                throw new UsernameNotFoundException("Invalid Credentials!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
