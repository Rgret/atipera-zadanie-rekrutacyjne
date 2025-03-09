package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class GitHubReposQueryTest {
    @Test
    void shouldReturnNonForkRepositoriesWithBranches() {
        given()
            .when()
                .get("/repos/rgret")
            .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0))
                .body("[0].name", not(emptyOrNullString()))
                .body("[0].fork", equalTo(false))
                .body("[0].owner.login", not(emptyOrNullString()))
                .body("[0].branches.size()", greaterThanOrEqualTo(1))
                .body("[0].branches[0].name", not(emptyOrNullString()))
                .body("[0].branches[0].commit.sha", not(emptyOrNullString()));
    }
}