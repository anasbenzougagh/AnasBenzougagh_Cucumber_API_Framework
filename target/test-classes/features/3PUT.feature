Feature: As a test, I want to perform CRUD flow

  @put @api
  Scenario: Put Added product

    When I send Put request to "/products/{id}" updating "Update apples" and 10
    Then status code should be 200
    And Response Content type is "application/json"