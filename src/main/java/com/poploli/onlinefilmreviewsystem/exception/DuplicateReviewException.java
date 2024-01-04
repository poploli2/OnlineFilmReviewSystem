package com.poploli.onlinefilmreviewsystem.exception;

public class DuplicateReviewException extends RuntimeException {
    public DuplicateReviewException(String message) {
        super(message);
    }
}
