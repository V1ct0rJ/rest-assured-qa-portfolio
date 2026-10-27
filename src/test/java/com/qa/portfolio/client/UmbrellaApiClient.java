package com.qa.portfolio.client;

import com.qa.portfolio.context.TestContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UmbrellaApiClient {

    private RequestSpecification requestSpec;
    private Response lastResponse;

    public UmbrellaApiClient() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        resetRequestSpec();
    }

    private void resetRequestSpec() {
        this.requestSpec = given().contentType("application/json; charset=UTF-8");
    }

    public void setHeader(String headerName, String headerValue) {
        this.requestSpec.header(headerName, headerValue);
    }

    /**
     * Beállítja a kérés törzsét nyers String / JSON formátumban.
     */
    public void setRequestBody(String jsonPayload) {
        this.requestSpec.body(jsonPayload);
    }

    /**
     * Beállítja a kérés törzsét tetszőleges Java DTO / POJO objektumként.
     * A Rest-Assured automatikusan szerializálja JSON formátumba.
     */
    public void setRequestBody(Object payload) {
        this.requestSpec.body(payload);
    }

    public void setPathParam(String name, String value) {
        this.requestSpec.pathParam(name, value);
    }

    public void setQueryParam(String name, String value) {
        this.requestSpec.queryParam(name, value);
    }

    public Response executeGet(String endpoint) {
        this.lastResponse = this.requestSpec.when().get(endpoint);

        // 1. Elmentjük a szálbiztos TestContext-be a többi StepDef számára
        TestContext.setResponse(this.lastResponse);

        // 2. Tiszta lapot nyitunk a következő kérésnek
        resetRequestSpec();

        return this.lastResponse;
    }

    public Response executePost(String endpoint) {
        this.lastResponse = this.requestSpec.when().post(endpoint);

        // 1. Elmentjük a szálbiztos TestContext-be a többi StepDef számára
        TestContext.setResponse(this.lastResponse);

        // 2. Tiszta lapot nyitunk a következő kérésnek
        resetRequestSpec();

        return this.lastResponse;
    }

    public Response getLastResponse() {
        if (this.lastResponse == null) {
            this.lastResponse = TestContext.getResponse();
        }
        return this.lastResponse;
    }
}