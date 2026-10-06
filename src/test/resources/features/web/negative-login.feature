Feature: DemoBlaze Negative Login

  @web
  @negative
  Scenario: Login with invalid credentials
    Given I am on the DemoBlaze login page for negative test
    When I login with invalid credentials
    Then I should see a login error message