package com.example.webrisetest.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionNotFoundException extends RuntimeException {

    private final String message;

    public SubscriptionNotFoundException(String message) {
        this.message = message;
    }
}
