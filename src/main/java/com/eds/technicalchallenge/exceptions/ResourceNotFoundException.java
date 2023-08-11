package com.eds.technicalchallenge.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Class classe) {
        super(classe.getSimpleName()+" not found");
    }
}
