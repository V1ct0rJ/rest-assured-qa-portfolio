package com.qa.portfolio.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    protected static RequestSpecification defaultSpec;

    @BeforeAll
    public static void setup() {
        System.out.println("--- [TESTBASE] REST Assured beállítások betöltése ---");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        defaultSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();
    }
}