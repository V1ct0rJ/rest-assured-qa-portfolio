package com.qa.portfolio.tests;

import com.qa.portfolio.base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeUserApiTest extends TestBase {

    @Test
    @DisplayName("GET /users/99999 - Nem létező felhasználó lekérése (404 Not Found)")
    void testGetNonExistingUser_ShouldReturn404() {
        given()
                .pathParam("id", 99999)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(404) // Elvárjuk a 404-es HTTP státuszkódot!
                .body(equalTo("{}")); // A JSONPlaceholder üres JSON objektumot ({}) ad vissza 404 esetén
    }

    @Test
    @DisplayName("GET /invalid-endpoint - Nem létező végpont hívása (404 Not Found)")
    void testInvalidEndpoint_ShouldReturn404() {
        given()
                .when()
                .get("/nonExistingEndpoint")
                .then()
                .statusCode(404);
    }
}