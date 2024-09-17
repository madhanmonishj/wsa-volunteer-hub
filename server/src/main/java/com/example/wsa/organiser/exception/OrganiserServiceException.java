package com.example.wsa.organiser.exception;

/**
 * Organiser Service Exception - captures all the organiser Service exception
 */
public class OrganiserServiceException extends OrganiserControllerException {
    public OrganiserServiceException() {
    }

    public OrganiserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrganiserServiceException(String message) {
        super(message);
    }

    public OrganiserServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrganiserServiceException(Throwable cause) {
        super(cause);
    }
}
