package com.qa.portfolio.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UmbrellaApiClient {

    private RequestSpecification requestSpec;
    private Response lastResponse;

    public UmbrellaApiClient() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        // Új, tiszta kérés specifikáció inicializálása
        this.requestSpec = given().contentType("application/json; charset=UTF-8");
    }

    public void setHeader(String headerName, String headerValue) {
        this.requestSpec.header(headerName, headerValue);
    }

    public void setRequestBody(String jsonPayload) {
        this.requestSpec.body(jsonPayload);
    }

    // ➕ ÚJ METÓDUS: Path paraméter hozzáadása a requestSpec-hez
    public void setPathParam(String name, String value) {
        this.requestSpec.pathParam(name, value);
    }

    // ➕ ÚJ METÓDUS: Query paraméter hozzáadása a requestSpec-hez
    public void setQueryParam(String name, String value) {
        this.requestSpec.queryParam(name, value);
    }

    public Response executeGet(String endpoint) {
        this.lastResponse = this.requestSpec.when().get(endpoint);
        return this.lastResponse;
    }

    public Response executePost(String endpoint) {
        this.lastResponse = this.requestSpec.when().post(endpoint);
        return this.lastResponse;
    }

    public Response getLastResponse() {
        return this.lastResponse;
    }
}