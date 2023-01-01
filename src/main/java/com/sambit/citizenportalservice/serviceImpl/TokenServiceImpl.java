package com.sambit.citizenportalservice.serviceImpl;

import com.sambit.citizenportalservice.jwtConfig.JWTConfiguration;
import com.sambit.citizenportalservice.service.TokenService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 5:02 PM
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JWTConfiguration jwtConfiguration;

    @Override
    public String generateToken(String username) {
        return jwtConfiguration.generateToken(Objects.requireNonNullElse(username, "Admin"));
    }

    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
