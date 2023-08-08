package com.eds.technicalchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VehicleWithInvalidYearException extends RuntimeException{
    public VehicleWithInvalidYearException(){
        super("Vehicle year greater than current year!");
    }
}
