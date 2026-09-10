package com.qa.portfolio.tests;

import com.qa.portfolio.base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ParameterizedUserTest extends TestBase {

    // --- 1. PÉLDA: Egyetlen paraméteres teszt (@ValueSource) ---
    @ParameterizedTest(name = "GET /users/{0} - Felhasználó létezésének ellenőrzése")
    @ValueSource(ints = {1, 2, 3, 4, 5}) // A JUnit 5 ötször fogja lefutattni ezt a tesztet ezekkel a számokkal!
    @DisplayName("Több felhasználó lekérése ID alapján (ValueSource)")
    void testGetUsersById_ShouldReturn200(int userId) {
        given()
                .pathParam("id", userId) // Dinamikus path paraméter: /users/{id}
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    // --- 2. PÉLDA: Több paraméteres teszt (@CsvSource) ---
    @ParameterizedTest(name = "ID: {0} -> Elvárt név: {1}")
    @CsvSource({
            "1, Leanne Graham",
            "2, Ervin Howell",
            "3, Clementine Bauch"
    })
    @DisplayName("Felhasználói ID és elvárt név ellenőrzése (CsvSource)")
    void testUserNames_ShouldMatchExpected(int userId, String expectedName) {
        given()
                .pathParam("id", userId)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo(expectedName));
    }
}