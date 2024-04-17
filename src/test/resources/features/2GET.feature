Feature: As a test, I want to perform CRUD flow

  @get @api
  Scenario: Get Added product

    When I send Get request to "/products/{id}" to get added item
    Then status code should be 200
    And Response Content type is "application/json"