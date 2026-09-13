package com.automation.web.steps;

import com.automation.web.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    private String username = "automation" + System.currentTimeMillis();
    private String password = "Automation123";

    @Given("I am on the DemoBlaze login page")
    public void iAmOnTheDemoBlazeLoginPage() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.demoblaze.com/");

        loginPage = new LoginPage(driver);

        // Create account first
        driver.findElement(
                org.openqa.selenium.By.id("signin2")
        ).click();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        org.openqa.selenium.By.id("sign-username")
                )
        );

        driver.findElement(
                org.openqa.selenium.By.id("sign-username")
        ).sendKeys(username);

        driver.findElement(
                org.openqa.selenium.By.id("sign-password")
        ).sendKeys(password);

        driver.findElement(
                org.openqa.selenium.By.xpath("//button[text()='Sign up']")
        ).click();

        // Handle signup alert
        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

        // Open login form
        loginPage.openLoginForm();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                org.openqa.selenium.By.id("nameofuser")
                        )
                ).isDisplayed()
        );

        driver.quit();
    }
}