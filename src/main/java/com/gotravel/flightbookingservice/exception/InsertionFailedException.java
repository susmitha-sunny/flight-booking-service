package com.gotravel.flightbookingservice.exception;


public class InsertionFailedException extends Exception {
    public InsertionFailedException() {
        super();
    }

    public InsertionFailedException(final String message) {
        super(message);
    }

    public InsertionFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InsertionFailedException(final Throwable cause) {
        super(cause);
    }

    public InsertionFailedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
