package com.qa.portfolio.tests;

import com.qa.portfolio.base.TestBase;
import com.qa.portfolio.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

// Megörökli a TestBase-ből a baseURI-t!
public class UserApiTest extends TestBase {

    @Test
    @DisplayName("GET /users/1 - Státuszkód és JSON mezők ellenőrzése (Fluent API)")
    void testGetSingleUser_ShouldReturn200AndCorrectJson() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("/users/1") // A baseURI mögé fűzi a /users/1-et
                .then()
                .statusCode(200) // Postman: pm.response.to.have.status(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Leanne Graham"))
                .body("email", equalTo("Sincere@april.biz"));
    }

    @Test
    @DisplayName("GET /users/1 - POJO Deserialization teszt")
    void testGetSingleUser_ShouldMapToUserObject() {
        // ACT: A REST Assured automatikusan beolvassa a JSON-t a User POJO-nkba!
        User user = given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(User.class); // <-- Itt történik a Deserialization!

        // ASSERT: JUnit 5 assertion-ökkel ellenőrizzük a Java objektumot
        assertNotNull(user, "A beolvasott User nem lehet null!");
        assertEquals(1, user.getId());
        assertEquals("Leanne Graham", user.getName());

        System.out.println("Sikeresen beolvasott POJO: " + user);
    }

    @Test
    @DisplayName("POST /users - Új felhasználó létrehozása (Serialization teszt)")
    void testCreateUser_ShouldReturn201AndCreatedUser() {
        // 1. ARRANGE: Létrehozunk egy Java objektumot a küldendő adatokkal
        User newUser = new User(0, "Új Tesztelő", "uj.tesztelo@qa.com");

        // 2. ACT & ASSERT: Elküldjük POST-ként a Java objektumot
        User createdUser = given()
                .header("Content-Type", "application/json")
                .body(newUser) // <-- REST Assured automatikusan JSON-ná alakítja (Serialization)!
                .when()
                .post("/users")
                .then()
                .statusCode(201) // 201 Created státuszkód elvárás
                .body("name", equalTo("Új Tesztelő"))
                .body("email", equalTo("uj.tesztelo@qa.com"))
                .extract()
                .as(User.class); // A választ visszalakítjuk Java objektummá!

        // 3. JUNIT ASSERTION: Ellenőrizzük, hogy a szerver adott-e neki ID-t
        assertTrue(createdUser.getId() > 0, "A szervernek generálnia kell egy ID-t!");
        System.out.println("Sikeresen létrehozott User a szerveren: " + createdUser);
    }
}