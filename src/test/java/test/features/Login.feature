Feature: Login Test Suite

  @smoke @login
  Scenario: login successfully
    Given fill user name with "kloia" on the page
    Given fill password with "kloia1234" on the page
    When click login on the page
    Then verify successful login

