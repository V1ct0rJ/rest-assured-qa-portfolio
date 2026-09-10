package com.qa.portfolio.tests;

import com.qa.portfolio.base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthApiTest extends TestBase {

    @Test
    @DisplayName("Bearer Token küldése beépített auth() használatával")
    void testBearerTokenAuthentication() {
        String mockToken = "my_secret_jwt_token_12345";

        given()
                .auth().oauth2(mockToken) // <-- REST Assured automatikusan beteszi az Authorization: Bearer <token> fejlécet!
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Egyedi kérésfejlécek (Custom Headers / API Key) küldése")
    void testCustomHeaderAndApiKey() {
        given()
                .header("x-api-key", "secret-api-key-999") // API Key fejléc
                .header("Custom-Client-Id", "QA-Automation-Framework") // Egyedi azonosító fejléc
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }
}