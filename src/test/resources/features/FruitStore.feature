Feature: POJO test for Fruit API

  @api
  Scenario: POJO test

    When Create a POJO utility file to store response then use TestNG soft and hard assertion
    And Assert using hamCrest
    Then Validate Json schema
