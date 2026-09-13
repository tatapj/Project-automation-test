Feature: DemoBlaze Login

  @web
  Scenario: Login with valid credentials
    Given I am on the DemoBlaze login page
    When I login with valid credentials
    Then I should be logged in successfully