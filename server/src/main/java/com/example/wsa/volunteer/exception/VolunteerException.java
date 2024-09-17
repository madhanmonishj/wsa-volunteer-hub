package com.example.wsa.volunteer.exception;

import com.example.wsa.exception.WSAVolunteerException;

public class VolunteerException extends WSAVolunteerException {
    public VolunteerException() {
    }

    public VolunteerException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolunteerException(String message) {
        super(message);
    }

    public VolunteerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VolunteerException(Throwable cause) {
        super(cause);
    }
}
