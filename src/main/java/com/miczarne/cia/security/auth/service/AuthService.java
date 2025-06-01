package com.miczarne.cia.security.auth.service;

import com.miczarne.cia.security.auth.error.AuthorizationErrors;
import com.miczarne.cia.security.auth.error.AuthorizationException;
import com.miczarne.cia.security.auth.model.AuthRequest;
import com.miczarne.cia.security.auth.model.AuthResponse;
import com.miczarne.cia.user.model.UserEntity;
import com.miczarne.cia.user.model.UserRoleEntity;
import com.miczarne.cia.user.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(
            JwtService jwtService,
            UserService userService,
            CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        final Optional<UserEntity> userOpt = userService.getUser(authRequest.getUsername(), authRequest.getPassword());

        if (userOpt.isEmpty()) {
            throw new AuthorizationException(authRequest.getUsername(), AuthorizationErrors.INCORRECT_CREDENTIALS);
        }

        final UserEntity user = userOpt.get();
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtService.generateToken(userDetails);

        return new AuthResponse(
                user.getUsername(),
                token,
                user.getRoles()
                        .stream()
                        .map(UserRoleEntity::getName)
                        .collect(Collectors.toSet())
        );
    }

    public AuthResponse register(AuthRequest authRequest) {
        if (userService.checkIfUserExists(authRequest.getUsername())) {
            throw new AuthorizationException(authRequest.getUsername(), AuthorizationErrors.ACCOUNT_EXISTS);
        }

        if (!isValidUsername(authRequest.getUsername())) {
            throw new AuthorizationException(authRequest.getUsername(), AuthorizationErrors.INVALID_USERNAME);
        }

        if (!isValidPassword(authRequest.getPassword())) {
            throw new AuthorizationException(authRequest.getUsername(), AuthorizationErrors.INVALID_PASSWORD);
        }

        userService.addNewUser(authRequest.getUsername(), authRequest.getPassword());
        return authenticate(authRequest);
    }

    private boolean isValidUsername(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if ("@$!%*?&".indexOf(ch) >= 0) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}
