Feature: User API

  @api
  Scenario: Get user by ID
    Given I have a valid user ID
    When I send a GET request to get the user
    Then the response status code should be 200
    And the response should contain user information

  @api
  Scenario: Create a new user
    Given I have valid user creation data
    When I send a POST request to create the user
    Then the create user response status code should be 200
    And the response should contain the created user ID

  @api
  Scenario: Get the created user
    Given I have created a new user
    When I send a GET request for the created user
    Then the created user response status code should be 200
    And the response should contain the created user information

  @api
  Scenario: Update a user
    Given I have a user to update
    When I send a PUT request to update the user
    Then the update user response status code should be 200
    And the response should contain the updated user information

  @api
  Scenario: Delete a user
    Given I have a user to delete
    When I send a DELETE request to delete the user
    Then the delete user response status code should be 200

  @api
  Scenario: Get user tags
    When I send a GET request to get user tags
    Then the tags response status code should be 200
    And the response should contain user tags