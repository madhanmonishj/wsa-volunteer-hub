package com.example.wsa.volunteer.exception;

public class VolunteerRepositoryException extends VolunteerServiceException {
    public VolunteerRepositoryException() {
    }

    public VolunteerRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolunteerRepositoryException(String message) {
        super(message);
    }

    public VolunteerRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VolunteerRepositoryException(Throwable cause) {
        super(cause);
    }
}
