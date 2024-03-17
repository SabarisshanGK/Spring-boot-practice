package com.sabarisshan.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class ResourceUpdateRequestException extends RuntimeException{
    public ResourceUpdateRequestException(String message) {
        super(message);
    }
}
