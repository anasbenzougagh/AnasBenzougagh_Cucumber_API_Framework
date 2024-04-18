Feature: As a test, I want to perform CRUD flow

  @delete @api
  Scenario: Delete Added product

    When I send Delete request to "/products/{id}" to delete posted item
    Then status code should be 200
    And Response Content type is "application/json"