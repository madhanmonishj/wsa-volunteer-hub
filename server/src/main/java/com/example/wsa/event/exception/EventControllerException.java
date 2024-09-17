package com.example.wsa.event.exception;

public class EventControllerException extends EventException{
    public EventControllerException(String message, String eMessage) {
    }

    public EventControllerException(String message) {
        super(message);
    }

    public EventControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventControllerException(Throwable cause) {
        super(cause);
    }

    public EventControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
