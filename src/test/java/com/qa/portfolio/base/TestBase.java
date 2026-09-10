package com.qa.portfolio.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setup() {
        System.out.println("--- [TESTBASE] REST Assured globális beállítások betöltése ---");

        // Beállítjuk az alapértelmezett URL-t (ugyanúgy, mint a Postman {{url}} változójánál!)
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Opcionális: Ha részletes logokat szeretnénk látni hiba esetén
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}