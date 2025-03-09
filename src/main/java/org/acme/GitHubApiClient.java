package org.acme;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;


@RegisterRestClient(configKey = "github-api")
public interface GitHubApiClient {
    
    @GET
    @Path("/users/{user}/repos")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<RepoDTO>> getRepo(@PathParam("user") String user);

    @GET
    @Path("/repos/{user}/{repo}/branches")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<RepoDTO.Branch>> getBranches(@PathParam("user") String user, @PathParam("repo") String repo);
}
