package com.eds.technicalchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotificationsException extends RuntimeException{
    public NotificationsException(String message) {
        super(message);
    }
}
