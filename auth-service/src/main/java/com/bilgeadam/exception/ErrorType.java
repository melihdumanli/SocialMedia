package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    AUTH_USER_PASSWORD_ERROR(1902,"Incorrect username or password", HttpStatus.FORBIDDEN),
    INTERNAL_ERROR(1903,"The system has encountered an error",HttpStatus.INTERNAL_SERVER_ERROR),
    AUTH_UNACCEPTABLE_TOKEN(1904,"Incorrect username or password", HttpStatus.FORBIDDEN),
    BAD_REQUEST_ERROR(1905,"Bad Request",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
