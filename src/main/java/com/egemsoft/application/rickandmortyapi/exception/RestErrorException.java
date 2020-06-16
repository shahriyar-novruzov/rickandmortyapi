package com.egemsoft.application.rickandmortyapi.exception;

import com.egemsoft.application.rickandmortyapi.model.RestErrorResponse;

public class RestErrorException extends RuntimeException {

    public RestErrorException(RestErrorResponse response) {
        super(response.getMessage());
    }
}
