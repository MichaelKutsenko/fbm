package com.fbm.exeptions;

public class FbmServerException extends RuntimeException {
private String error;

    public FbmServerException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
