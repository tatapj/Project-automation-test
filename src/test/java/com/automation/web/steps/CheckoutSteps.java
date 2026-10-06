package com.automation.web.steps;

import com.automation.web.pages.CartPage;
import com.automation.web.pages.CheckoutPage;
import com.automation.web.pages.LoginPage;
import com.automation.web.pages.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutSteps {

    private WebDriver driver;

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private String username =
            "automation" + System.currentTimeMillis();

    private String password = "Automation123";

    @Given("I am logged in to DemoBlaze")
    public void iAmLoggedInToDemoBlaze() {

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

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open Sign Up
        driver.findElement(By.id("signin2")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("sign-username")
                )
        );

        // Create account
        driver.findElement(By.id("sign-username"))
                .sendKeys(username);

        driver.findElement(By.id("sign-password"))
                .sendKeys(password);

        driver.findElement(
                By.xpath("//button[text()='Sign up']")
        ).click();

        // Accept signup alert
        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

        // Open Login
        loginPage.openLoginForm();

        // Login
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Verify login
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("nameofuser")
                )
        );
    }

    @When("I select a product")
    public void iSelectAProduct() {

        productPage = new ProductPage(driver);

        productPage.selectProduct();
    }

    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() {

        productPage.addToCart();

        productPage.handleAddToCartAlert();
    }

    @When("I open the cart")
    public void iOpenTheCart() {

        cartPage = new CartPage(driver);

        cartPage.openCart();
    }

    @When("I click Place Order")
    public void iClickPlaceOrder() {

        cartPage.clickPlaceOrder();
    }

    @When("I enter the order information")
    public void iEnterTheOrderInformation() {

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterOrderInformation(
                "Tata Automation",
                "Indonesia",
                "Jakarta",
                "1234567890123456",
                "10",
                "2026"
        );
    }

    @When("I click Purchase")
    public void iClickPurchase() {

        checkoutPage.clickPurchase();
    }

    @Then("the order should be completed successfully")
    public void theOrderShouldBeCompletedSuccessfully() {

        Assertions.assertTrue(
                checkoutPage.isOrderSuccessful(),
                "Order was not completed successfully"
        );
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}