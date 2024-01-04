package com.poploli.onlinefilmreviewsystem.exception;

public class UserNotApprovedException extends RuntimeException {
    public UserNotApprovedException(String message) {
        super(message);
    }
}
