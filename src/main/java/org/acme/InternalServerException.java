package org.acme;

public class InternalServerException extends RuntimeException {
    private final int statusCode;

    public InternalServerException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
