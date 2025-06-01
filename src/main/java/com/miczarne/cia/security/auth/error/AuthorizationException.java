package com.miczarne.cia.security.auth.error;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizationException extends RuntimeException {

    private final AuthorizationErrors authError;
    private final String username;

    public AuthorizationException(String username, AuthorizationErrors authError) {
        super(authError.getErrorMessage());
        this.authError = authError;
        this.username = username;
    }
}
