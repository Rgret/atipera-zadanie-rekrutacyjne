package org.acme;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/repos")
public class GitHubReposQuery {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Hello() {
        return "Hello from Quarkus REST";
    }


    @Inject
    @RestClient
    GitHubApiClient gitHubApiClient;

    @Path("/{user}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<RepoDTO>> getRepos(@PathParam("user") String user) {
        return gitHubApiClient.getRepo(user)
                .map(repos -> repos.stream()
                        .filter(repo -> !repo.isFork())
                        .toList())
                .flatMap(repos -> {
                    List<Uni<RepoDTO>> modifiedRepos = repos.stream()
                            .map(repo -> gitHubApiClient.getBranches(user, repo.getName())
                                    .map(branches -> {
                                        repo.setBranches(branches);
                                        return repo;
                                    }))
                            .toList();

                    return Uni.join().all(modifiedRepos).andCollectFailures();
                }) 
                .onFailure(WebApplicationException.class)
                .transform(t -> {
                    WebApplicationException wae = (WebApplicationException) t;
                    Response response = wae.getResponse();

                    if (response.getStatus() == 403) {
                        String body = response.readEntity(String.class);
                        if (body.contains("rate limit exceeded")){
                            return new RateLimitExceededException(403,"GitHub API rate limit exceeded");
                        }
                        return new RateLimitExceededException(403,"Action not permitted. Details: " + body);
                    }
                    if (response.getStatus() == 404) {
                        return new GitHubUserNotFoundException(404,"User not found");
                    }

                    return new InternalServerException(500, "Internal Server Error.  Details:" + response.readEntity(String.class));
                });
    }
}
