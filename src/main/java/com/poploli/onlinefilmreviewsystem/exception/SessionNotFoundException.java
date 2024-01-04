package com.poploli.onlinefilmreviewsystem.exception;


public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}
