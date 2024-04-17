Feature: As a test, I want to perform CRUD flow

  @delete @api
  Scenario: Get Added product

    When I send Get of deleted item request to "/products/{id}"
    Then status code should be 404
    And Response Content type is "application/json"