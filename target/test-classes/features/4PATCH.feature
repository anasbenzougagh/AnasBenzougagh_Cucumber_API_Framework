Feature: As a test, I want to perform CRUD flow

  @patch @api
  Scenario: Patch Added product

    When I send Patch request to "/products/{id}" patching the item to "Patch Apples"
    Then status code should be 200
    And Response Content type is "application/json"