package com.eds.technicalchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VehicleWithSameChassisException extends RuntimeException{
    public VehicleWithSameChassisException(){
        super("Vehicle already exists with this chassis number!");
    }
}
