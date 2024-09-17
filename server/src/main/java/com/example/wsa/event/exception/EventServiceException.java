package com.example.wsa.event.exception;

public class EventServiceException extends EventControllerException{
    public EventServiceException(String message, String eMessage) {
        super(message, eMessage);
    }

    public EventServiceException(String message) {
        super(message);
    }

    public EventServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventServiceException(Throwable cause) {
        super(cause);
    }

    public EventServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
