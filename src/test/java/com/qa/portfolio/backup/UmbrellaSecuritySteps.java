/*package com.qa.portfolio.backup;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UmbrellaSecuritySteps {

    private String currentAgent;
    private String passcode;
    private Response response;

    // --- SETUP: Beállítjuk a baseURI-t a tesztek futása előtt ---
    @Before
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // --- GIVEN LÉPÉSEK ---

    @Given("Agent {string} attempts access to {string}")
    public void agentAttemptsAccessTo(String agentName, String terminalName) {
        this.currentAgent = agentName;
        System.out.println("--- Umbrella Mainframe Access Log ---");
        System.out.println("Terminal: " + terminalName + " | Agent: " + agentName);
    }

    @Given("she inputs security passcode {string}")
    public void sheInputsSecurityPasscode(String code) {
        this.passcode = code;
    }

    @Given("Researcher {string} is authenticated on NEST Terminal")
    public void researcherIsAuthenticatedOnNESTTerminal(String researcherName) {
        this.currentAgent = researcherName;
        System.out.println("--- NEST Mainframe Log ---");
        System.out.println("Authenticated Researcher: " + researcherName);
    }

    // --- WHEN LÉPÉSEK ---

    @When("the system validates credentials via endpoint {string}")
    public void theSystemValidatesCredentialsViaEndpoint(String endpoint) {
        // A kapott végpontot hívjuk meg a baseURI-vel (https://jsonplaceholder.typicode.com)
        response = given()
                .header("X-Umbrella-Passcode", passcode != null ? passcode : "NONE")
                .when()
                .get(endpoint);
    }

    @When("he requests system data for subject ID {int}")
    public void heRequestsSystemDataForSubjectID(int subjectId) {
        response = given()
                .pathParam("id", subjectId)
                .when()
                .get("/users/{id}");
    }

    // --- THEN LÉPÉSEK ---

    @Then("the mainframe should respond with HTTP status {int}")
    public void theMainframeShouldRespondWithHTTPStatus(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("the system response should confirm access denied")
    public void theSystemResponseShouldConfirmAccessDenied() {
        // A 404-es negatív tesztünknél az üres JSON-t ({}) várjuk el
        response.then().body(equalTo("{}"));
    }

    @Then("the response body field {string} should be {string}")
    public void theResponseBodyFieldShouldBe(String jsonField, String expectedValue) {
        response.then().body(jsonField, equalTo(expectedValue));
    }
}*/