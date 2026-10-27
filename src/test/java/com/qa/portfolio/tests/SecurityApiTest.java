package com.qa.portfolio.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*; // <-- Ez a lényeges sor, ami behúzza a given(), when(), then()-t!
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.equalTo;
public class SecurityApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @DisplayName("Biztonsági teszt: Jogosulatlan hozzáférés ellenőrzése")
    public void testUnauthorizedAccessWithoutPasscode() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("/users/1")
                .then()
                .statusCode(equalTo(200));
    }

    @Test
    @DisplayName("Biztonsági teszt: Hamis passcode kezelése")
    public void testAccessWithInvalidPasscode() {
        given()
                .header("Content-Type", "application/json")
                .header("X-Umbrella-Passcode", "rossz_jelszo_123")
                .when()
                .get("/users/1")
                .then()
                .statusCode(equalTo(200));
    }
}