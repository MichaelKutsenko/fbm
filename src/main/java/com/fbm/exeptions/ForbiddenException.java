package com.fbm.exeptions;

/**
 * Created by Mocart on 27-Aug-17.
 */
public class ForbiddenException extends RuntimeException {
    private String error;

    public ForbiddenException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
