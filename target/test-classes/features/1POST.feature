Feature: As a test, I want to perform CRUD flow

  @post @api
  Scenario: Post new product

    When I send Post a new "apples" with the price of 7 dollars as request to "/products" endpoint
    Then status code should be 201
    And Response Content type is "application/json"