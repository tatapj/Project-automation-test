Feature: DemoBlaze End to End Checkout

  @web
  Scenario: Successfully checkout a product
    Given I am logged in to DemoBlaze
    When I select a product
    And I add the product to the cart
    And I open the cart
    And I click Place Order
    And I enter the order information
    And I click Purchase
    Then the order should be completed successfully