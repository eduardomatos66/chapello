package com.matos.capello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstituteNotExistentException extends BusinessException {

    public InstituteNotExistentException(String msg) {
        super(msg);
    }
}