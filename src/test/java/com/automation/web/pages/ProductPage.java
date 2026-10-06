package com.automation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By product = By.xpath("//a[text()='Samsung galaxy s6']");
    private By addToCartButton = By.xpath("//a[text()='Add to cart']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectProduct() {
        wait.until(
                ExpectedConditions.elementToBeClickable(product)
        ).click();
    }

    public void addToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton)
        ).click();
    }

    public void handleAddToCartAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}