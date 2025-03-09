package org.acme;

import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GitHubUserNotFoundExceptionMapper implements ExceptionMapper<GitHubUserNotFoundException> {
    @Override
    public Response toResponse(GitHubUserNotFoundException exception) {
        return Response.status(exception.getStatusCode())
                .entity(Map.of(
                    "status", exception.getStatusCode(),
                    "message", exception.getMessage()
                ))
                .build();
    }
}
