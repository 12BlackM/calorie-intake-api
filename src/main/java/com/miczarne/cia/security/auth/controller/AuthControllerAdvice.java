package com.miczarne.cia.security.auth.controller;

import com.miczarne.cia.model.GlobalErrorMessage;
import com.miczarne.cia.security.auth.error.AuthorizationErrors;
import com.miczarne.cia.security.auth.error.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<GlobalErrorMessage> handleAuthorizationException(AuthorizationException ex, WebRequest request) {

        GlobalErrorMessage error = GlobalErrorMessage.builder()
                .exceptionTime(LocalDateTime.now())
                .error(getError(ex))
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .status(ex.getAuthError().getHttpStatus())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String getError(AuthorizationException ex) {
        if (ex.getAuthError() == AuthorizationErrors.ACCOUNT_EXISTS) {
            return String.format("Account for %s already exists", ex.getUsername());
        }

        return ex.getMessage();
    }

}
