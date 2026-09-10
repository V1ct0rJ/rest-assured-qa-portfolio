package com.qa.portfolio.tests;

import com.qa.portfolio.base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaTest extends TestBase {

    @Test
    @DisplayName("GET /users/1 - Válasz JSON szerkezeti ellenőrzése Schema alapján")
    void testGetSingleUser_ShouldMatchJsonSchema() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                // ITT TÖRTÉNIK A SCHEMA VALIDÁCIÓ!
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}