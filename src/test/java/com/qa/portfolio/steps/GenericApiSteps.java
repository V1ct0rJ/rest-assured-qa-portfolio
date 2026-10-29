package com.qa.portfolio.steps;

import com.qa.portfolio.client.UmbrellaApiClient;
import com.qa.portfolio.context.TestContext;
import com.qa.portfolio.factory.SubjectDataFactory;
import com.qa.portfolio.model.SubjectDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GenericApiSteps {

    private final UmbrellaApiClient apiClient;
    private String currentEndpoint;

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

    @Given("I set path parameter {string} to {string}")
    public void iSetPathParameterTo(String name, String value) {
        apiClient.setPathParam(name, value);
    }

    @Given("I add query parameter {string} with value {string}")
    public void iAddQueryParameterWithValue(String name, String value) {
        apiClient.setQueryParam(name, value);
    }

    // --- DINAMIKUS ADATGENERÁLÁS (Module 28) ---

    @Given("I generate a dynamic subject request body")
    public void iGenerateADynamicSubjectRequestBody() {
        SubjectDto dynamicSubject = SubjectDataFactory.createRandomSubject();
        apiClient.setRequestBody(dynamicSubject);
        TestContext.set(TestContext.REQUEST_BODY, dynamicSubject);
        System.out.println("🎲 Dinamikus Subject generálva: " + dynamicSubject.getName());
    }

    // --- WHEN LÉPÉSEK (Végrehajtás) ---

    @When("I send a GET request")
    public void iSendAGETRequest() {
        Response response = apiClient.executeGet(this.currentEndpoint);
        // Elmentjük a válasz objektumot a TestContext-be is a többi StepDefinition osztály számára!
        TestContext.setResponse(response != null ? response : apiClient.getLastResponse());
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() {
        Response response = apiClient.executePost(this.currentEndpoint);
        // Elmentjük a válasz objektumot a TestContext-be is a többi StepDefinition osztály számára!
        TestContext.setResponse(response != null ? response : apiClient.getLastResponse());
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

    @Then("the response time should be less than {long} milliseconds")
    public void theResponseBodyTimeShouldBeLessThanMilliseconds(long maxTimeMs) {
        apiClient.getLastResponse().then().time(lessThan(maxTimeMs));
        System.out.println("⏱️ Válaszidő ellenőrizve: kevesebb mint " + maxTimeMs + " ms (Aktuális: " + apiClient.getLastResponse().getTime() + " ms)");
    }

    @And("the response body should not contain field {string}")
    public void theResponseBodyShouldNotContainField(String fieldName) {
        // Ellenőrizzük, hogy a JSON válasz nem tartalmazza a tiltott érzékeny mezőt
        com.qa.portfolio.context.TestContext.getResponse()
                .then()
                .body("$", not(hasKey(fieldName)));
    }
}