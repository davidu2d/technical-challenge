package com.eds.technicalchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    ProblemDetail resourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Resource Already Exists");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail resourceNotFoundException(ResourceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Resource not found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(NotificationsException.class)
    ProblemDetail notificationsException(NotificationsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Domain errors");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ApiKeyNotFoundException.class)
    ProblemDetail apiKeyNotFoundException(ApiKeyNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Api Key error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(RateLimitException.class)
    ProblemDetail rateLimitException(RateLimitException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.TOO_MANY_REQUESTS, e.getLocalizedMessage());
        problemDetail.setTitle("Rate Limit error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }
}
