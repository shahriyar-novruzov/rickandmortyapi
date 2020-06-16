package com.egemsoft.application.rickandmortyapi.exception;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
