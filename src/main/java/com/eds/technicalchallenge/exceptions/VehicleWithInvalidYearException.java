package com.eds.technicalchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VehicleWithInvalidYearException extends RuntimeException{
    public VehicleWithInvalidYearException(){
        super("Vehicle year greater than current year!");
    }
}
