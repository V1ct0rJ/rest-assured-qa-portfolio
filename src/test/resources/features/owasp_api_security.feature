@security @owasp @umbrella
Feature: OWASP API Security Top 10 Validations
  As a Security QA Engineer
  I want to test API endpoints against common OWASP vulnerabilities
  So that unauthorized access and data leaks are prevented in production

  @bola @negative
  Scenario: Prevent unauthorized access to non-existent or restricted records (BOLA simulation)
    Given I set the target endpoint to "/users/99999"
    When I send a GET request
    Then the response status code should be 404

  @excessive-data @positive
  Scenario: Validate that user response payload does not expose sensitive internal fields
    Given I set the target endpoint to "/users/1"
    When I send a GET request
    Then the response status code should be 200
    And the response body field "name" should be "Leanne Graham"
    And the response body field "email" should be "Sincere@april.biz"
    And the response body should not contain field "password"
    And the response body should not contain field "passwordHash"
    And the response body should not contain field "internalId"