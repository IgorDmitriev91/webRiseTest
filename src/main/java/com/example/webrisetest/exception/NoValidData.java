package com.example.webrisetest.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoValidData extends RuntimeException{
    private final String message;

    public NoValidData( String message) {
        this.message = message;
    }
}

