package org.acme;

import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InternalServerExceptionMapper implements ExceptionMapper<InternalServerException> {
    @Override
    public Response toResponse(InternalServerException exception) {
        return Response.status(exception.getStatusCode())
                .entity(Map.of(
                    "status", exception.getStatusCode(),
                    "message", exception.getMessage()
                ))
                .build();
    }
}
