package com.miczarne.cia.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class GlobalErrorMessage {
     private LocalDateTime exceptionTime;
     private HttpStatus status;
     private String error;
     private String message;
     private String path;
}
