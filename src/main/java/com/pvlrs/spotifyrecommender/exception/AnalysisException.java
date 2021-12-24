package com.pvlrs.spotifyrecommender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class AnalysisException extends RuntimeException {

    public AnalysisException(String message) {
        super(message);
    }
}
