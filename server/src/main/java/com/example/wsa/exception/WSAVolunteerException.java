package com.example.wsa.exception;

/**
 * Main Exception Class for wrapping all the exceptions
 */
public class WSAVolunteerException  extends Exception {
    public WSAVolunteerException() {
    }

    public WSAVolunteerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WSAVolunteerException(String message) {
        super(message);
    }

    public WSAVolunteerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WSAVolunteerException(Throwable cause) {
        super(cause);
    }
}