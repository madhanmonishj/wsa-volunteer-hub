package com.example.wsa.organiser.exception;

import com.example.wsa.exception.WSAVolunteerException;

/**
 * Organiser  Exception - captures all the organiser exception
 */
public class OrganiserException extends WSAVolunteerException {
    public OrganiserException() {
    }

    public OrganiserException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrganiserException(String message) {
        super(message);
    }

    public OrganiserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrganiserException(Throwable cause) {
        super(cause);
    }
}
