package com.miczarne.cia.security.auth.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum AuthorizationErrors {
    INCORRECT_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Cannot login for provided credentials"),
    ACCOUNT_EXISTS(HttpStatus.BAD_REQUEST, "Account already exists"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Password is not matching requirements!"),
    INVALID_USERNAME(HttpStatus.BAD_REQUEST, "Username is not an mail!");


    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final String errorMessage;

    AuthorizationErrors(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
