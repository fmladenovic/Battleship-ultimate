package com.sbz.battleship.domain.exception;

public class InternalServerError extends Exception {

    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerError(Throwable cause) {
        super(cause);
    }

    public InternalServerError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
