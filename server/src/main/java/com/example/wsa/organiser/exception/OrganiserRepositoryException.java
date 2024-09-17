package com.example.wsa.organiser.exception;

/**
 * Organiser Repository Exception - captures all the organiser repository exception
 */
public class OrganiserRepositoryException extends OrganiserServiceException{
    public OrganiserRepositoryException() {
    }

    public OrganiserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrganiserRepositoryException(String message) {
        super(message);
    }

    public OrganiserRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrganiserRepositoryException(Throwable cause) {
        super(cause);
    }
}
