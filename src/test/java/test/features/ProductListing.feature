Feature: Product List Test Suite

  @smoke @product
  Scenario: select product
    Given fill user name with "kloia" on the page
    Given fill password with "kloia1234" on the page
    When click login on the page
    Then verify successful login
    And click product list button
    Then verify product list page
    When select 12. product from the list
