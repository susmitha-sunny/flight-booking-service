package com.gotravel.flightbookingservice.exception;


public class InvalidRequestException extends Exception {
    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(final String message) {
        super(message);
    }

    public InvalidRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(final Throwable cause) {
        super(cause);
    }

    public InvalidRequestException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
