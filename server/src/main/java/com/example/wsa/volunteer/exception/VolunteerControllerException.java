package com.example.wsa.volunteer.exception;

public class VolunteerControllerException extends VolunteerException {
    public VolunteerControllerException() {
    }

    public VolunteerControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolunteerControllerException(String message) {
        super(message);
    }

    public VolunteerControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VolunteerControllerException(Throwable cause) {
        super(cause);
    }
}
