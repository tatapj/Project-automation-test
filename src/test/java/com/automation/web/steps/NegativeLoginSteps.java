package com.automation.web.steps;

import com.automation.web.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NegativeLoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I am on the DemoBlaze login page for negative test")
    public void iAmOnTheDemoBlazeLoginPageForNegativeTest() {

        ChromeOptions options = new ChromeOptions();

        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://www.demoblaze.com/");

        loginPage = new LoginPage(driver);

        loginPage.openLoginForm();
    }

    @When("I login with invalid credentials")
    public void iLoginWithInvalidCredentials() {

        loginPage.enterUsername("invalid_user_12345");
        loginPage.enterPassword("wrong_password_12345");
        loginPage.clickLogin();
    }

    @Then("I should see a login error message")
    public void iShouldSeeALoginErrorMessage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        Alert alert = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent()
        );

        String alertMessage = alert.getText();

        Assertions.assertTrue(
                alertMessage.contains("Wrong password")
                        || alertMessage.contains("User does not exist"),
                "Expected login error message, but got: " + alertMessage
        );

        alert.accept();
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}