package com.qa.portfolio.steps;

import com.qa.portfolio.client.UmbrellaApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class GenericApiSteps {

    private final UmbrellaApiClient apiClient;
    private String currentEndpoint;

    // Dependency Injection (vagy sima példányosítás) a klienshez
    public GenericApiSteps() {
        this.apiClient = new UmbrellaApiClient();
    }

    // --- GIVEN LÉPÉSEK (Előkészítés) ---

    @Given("I set the target endpoint to {string}")
    public void iSetTheTargetEndpointTo(String endpoint) {
        this.currentEndpoint = endpoint;
    }

    @Given("I add header {string} with value {string}")
    public void iAddHeaderWithValue(String name, String value) {
        apiClient.setHeader(name, value);
    }

    @Given("I set request body:")
    public void iSetRequestBody(String payload) {
        apiClient.setRequestBody(payload);
    }

    // --- WHEN LÉPÉSEK (Végrehajtás) ---

    @When("I send a GET request")
    public void iSendAGETRequest() {
        apiClient.executeGet(this.currentEndpoint);
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() {
        apiClient.executePost(this.currentEndpoint);
    }

    // --- THEN LÉPÉSEK (Ellenőrzések / Assert-ek) ---

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        apiClient.getLastResponse().then().statusCode(expectedStatusCode);
    }

    @Then("the response body field {string} should be {string}")
    public void theResponseBodyFieldShouldBe(String jsonPath, String expectedValue) {
        apiClient.getLastResponse().then().body(jsonPath, equalTo(expectedValue));
    }

    @Then("the response body field {string} should be integer {int}")
    public void theResponseBodyFieldShouldBeInteger(String jsonPath, int expectedValue) {
        apiClient.getLastResponse().then().body(jsonPath, equalTo(expectedValue));
    }

    @Given("I set path parameter {string} to {string}")
    public void iSetPathParameterTo(String name, String value) {
        // ITT TÖRTÉNIK AZ ÁTUGRÁS az UmbrellaApiClient új metódusába:
        apiClient.setPathParam(name, value);
    }

    @Given("I add query parameter {string} with value {string}")
    public void iAddQueryParameterWithValue(String name, String value) {
        // ITT TÖRTÉNIK AZ ÁTUGRÁS az UmbrellaApiClient új metódusába:
        apiClient.setQueryParam(name, value);
    }
}