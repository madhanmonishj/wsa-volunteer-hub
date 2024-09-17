package com.example.wsa.organiser.exception;

/**
 * Organiser Controller Exception - captures all the organiser controller exception
 */
public class OrganiserControllerException extends OrganiserException {
    public OrganiserControllerException() {
    }

    public OrganiserControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrganiserControllerException(String message) {
        super(message);
    }

    public OrganiserControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrganiserControllerException(Throwable cause) {
        super(cause);
    }
}
