package com.queueless.exception;

public class NoWaitingCustomersException extends RuntimeException {

    public NoWaitingCustomersException(String message) {
        super(message);
    }
}