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

    @ExceptionHandler(VehicleWithInvalidYearException.class)
    ProblemDetail vehicleWithInvalidYearException(VehicleWithInvalidYearException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Error Vehicle year");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("solution", "Vehicle year must be less than or equal to the current year(" + LocalDate.now().getYear() + ")");
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(VehicleWithSameChassisException.class)
    ProblemDetail vehicleWithSameChassisException(VehicleWithSameChassisException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Error Vehicle chassis");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("solution", "Change chassis number" );
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

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
}
