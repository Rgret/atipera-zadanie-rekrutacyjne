package org.acme;

public class RateLimitExceededException extends RuntimeException {
    private final int statusCode;

    public RateLimitExceededException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
