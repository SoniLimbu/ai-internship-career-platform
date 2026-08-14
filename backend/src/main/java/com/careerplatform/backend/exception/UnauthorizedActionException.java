package com.careerplatform.backend.exception;

/** Thrown when an authenticated user tries to act on a resource they don't own. */
public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}