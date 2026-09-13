package com.automation.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserApiSteps {

    private Response response;

    private final String baseUrl = "https://dummyapi.io/data/v1";
    private final String appId = "63a804408eb0cb069b57e43a";

    private String userId;
    private String createdUserId;


    // =========================
    // GET USER
    // =========================

    @Given("I have a valid user ID")
    public void iHaveAValidUserId() {

        Response usersResponse = given()
                .header("app-id", appId)
                .when()
                .get(baseUrl + "/user");

        usersResponse.then().log().all();

        assertEquals(200, usersResponse.getStatusCode());

        userId = usersResponse.jsonPath().getString("data[0].id");

        assertNotNull(userId);
    }

    @When("I send a GET request to get the user")
    public void iSendAGetRequestToGetTheUser() {

        response = given()
                .header("app-id", appId)
                .when()
                .get(baseUrl + "/user/" + userId);

        response.then().log().all();
    }

    @Then("the response status code should be 200")
    public void theResponseStatusCodeShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the response should contain user information")
    public void theResponseShouldContainUserInformation() {

        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("firstName"));
        assertNotNull(response.jsonPath().getString("lastName"));
    }


    // =========================
    // CREATE USER
    // =========================

    @Given("I have valid user creation data")
    public void iHaveValidUserCreationData() {
        System.out.println("User creation data is prepared");
    }

    @When("I send a POST request to create the user")
    public void iSendAPostRequestToCreateTheUser() {

        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", "John");
        userData.put("lastName", "Automation");
        userData.put("email", "john" + System.currentTimeMillis() + "@example.com");

        response = given()
                .header("app-id", appId)
                .contentType("application/json")
                .body(userData)
                .when()
                .post(baseUrl + "/user/create");

        response.then().log().all();

        createdUserId = response.jsonPath().getString("id");
    }

    @Then("the create user response status code should be 200")
    public void theCreateUserResponseStatusCodeShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the response should contain the created user ID")
    public void theResponseShouldContainTheCreatedUserId() {
        assertNotNull(createdUserId);
    }

    @Given("I have created a new user")
    public void iHaveANewlyCreatedUser() {
        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", "Test");
        userData.put("lastName", "User");
        userData.put("email", "test" + System.currentTimeMillis() + "@example.com");

        response = given()
                .header("app-id", appId)
                .contentType("application/json")
                .body(userData)
                .when()
                .post(baseUrl + "/user/create");

        response.then().log().all();

        createdUserId = response.jsonPath().getString("id");

        assertEquals(200, response.getStatusCode());
        assertNotNull(createdUserId);
    }

    @When("I send a GET request for the created user")
    public void iSendAGetRequestForTheCreatedUser() {
        response = given()
                .header("app-id", appId)
                .when()
                .get(baseUrl + "/user/" + createdUserId);

        response.then().log().all();
    }

    @Then("the created user response status code should be 200")
    public void theCreatedUserResponseStatusCodeShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the response should contain the created user information")
    public void theResponseShouldContainTheCreatedUserInformation() {
        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("firstName"));
        assertNotNull(response.jsonPath().getString("lastName"));
    }

    @Given("I have a user to update")
    public void iHaveAUserToUpdate() {

        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", "Update");
        userData.put("lastName", "Test");
        userData.put("email", "update" + System.currentTimeMillis() + "@example.com");

        response = given()
                .header("app-id", appId)
                .contentType("application/json")
                .body(userData)
                .when()
                .post(baseUrl + "/user/create");

        response.then().log().all();

        assertEquals(200, response.getStatusCode());

        createdUserId = response.jsonPath().getString("id");

        assertNotNull(createdUserId);
    }

    @When("I send a PUT request to update the user")
    public void iSendAPutRequestToUpdateTheUser() {

        Map<String, String> updateData = new HashMap<>();
        updateData.put("firstName", "UpdatedName");
        updateData.put("lastName", "UpdatedLastName");

        response = given()
                .header("app-id", appId)
                .contentType("application/json")
                .body(updateData)
                .when()
                .put(baseUrl + "/user/" + createdUserId);

        response.then().log().all();
    }

    @Then("the update user response status code should be 200")
    public void theUpdateUserResponseStatusCodeShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the response should contain the updated user information")
    public void theResponseShouldContainTheUpdatedUserInformation() {

        assertNotNull(response.jsonPath().getString("id"));
        assertEquals(
                "UpdatedName",
                response.jsonPath().getString("firstName")
        );
        assertEquals(
                "UpdatedLastName",
                response.jsonPath().getString("lastName")
        );
    }

    @Given("I have a user to delete")
    public void iHaveAUserToDelete() {

        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", "Delete");
        userData.put("lastName", "Test");
        userData.put("email", "delete" + System.currentTimeMillis() + "@example.com");

        response = given()
                .header("app-id", appId)
                .contentType("application/json")
                .body(userData)
                .when()
                .post(baseUrl + "/user/create");

        response.then().log().all();

        assertEquals(200, response.getStatusCode());

        createdUserId = response.jsonPath().getString("id");

        assertNotNull(createdUserId);
    }

    @When("I send a DELETE request to delete the user")
    public void iSendADeleteRequestToDeleteTheUser() {

        response = given()
                .header("app-id", appId)
                .when()
                .delete(baseUrl + "/user/" + createdUserId);

        response.then().log().all();
    }

    @Then("the delete user response status code should be 200")
    public void theDeleteUserResponseStatusCodeShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @When("I send a GET request to get user tags")
    public void iSendAGetRequestToGetUserTags() {

        response = given()
                .header("app-id", appId)
                .when()
                .get(baseUrl + "/tag");

        response.then().log().all();
    }

    @Then("the tags response status code should be 200")
    public void theTagsResponseStatusCodeShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the response should contain user tags")
    public void theResponseShouldContainUserTags() {
        assertNotNull(response.jsonPath().get("data"));
    }
}