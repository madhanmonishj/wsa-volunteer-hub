package com.example.wsa.volunteer.exception;

public class VolunteerServiceException extends VolunteerControllerException {
    public VolunteerServiceException() {
    }

    public VolunteerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolunteerServiceException(String message) {
        super(message);
    }

    public VolunteerServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VolunteerServiceException(Throwable cause) {
        super(cause);
    }
}
