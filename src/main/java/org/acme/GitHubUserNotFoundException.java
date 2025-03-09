package org.acme;

public class GitHubUserNotFoundException extends RuntimeException {
    private final int statusCode;

    public GitHubUserNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
