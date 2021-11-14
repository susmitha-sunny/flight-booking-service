package com.gotravel.flightbookingservice.exception;


public class ValueNotFoundException extends Exception {
    public ValueNotFoundException() {
        super();
    }

    public ValueNotFoundException(final String message) {
        super(message);
    }

    public ValueNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ValueNotFoundException(final Throwable cause) {
        super(cause);
    }

    public ValueNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
