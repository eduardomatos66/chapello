package com.matos.capello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpportunityNotExistentException extends BusinessException {

    public OpportunityNotExistentException(String msg) {
        super(msg);
    }
}
