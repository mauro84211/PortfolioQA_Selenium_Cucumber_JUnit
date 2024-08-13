package com.portafolio.steps;

import com.portafolio.pages.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected org.openqa.selenium.WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;

    CheckoutPage checkoutPage;


    public void navigateTo() {
        Duration duration = Duration.ofSeconds(10);
        Duration longduration = Duration.ofSeconds(20);
        driver.manage().timeouts().implicitlyWait(duration);

        WebDriverWait wait = new WebDriverWait(driver, longduration);
        // Navigate to the Parabank website
        driver.navigate().to("https://www.saucedemo.com/");


        // Initialize the LoginPage object
        loginPage = new LoginPage(driver);
    }
}
