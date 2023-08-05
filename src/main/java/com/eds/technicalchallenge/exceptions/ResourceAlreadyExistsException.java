package com.eds.technicalchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(Class classe) {
        super(classe.getSimpleName()+" already exists");
    }
}
