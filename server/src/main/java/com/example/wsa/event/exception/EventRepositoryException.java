package com.example.wsa.event.exception;

public class EventRepositoryException extends EventServiceException{
    public EventRepositoryException(String message, String eMessage) {
        super(message, eMessage);
    }

    public EventRepositoryException(String message) {
        super(message);
    }

    public EventRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventRepositoryException(Throwable cause) {
        super(cause);
    }

    public EventRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
