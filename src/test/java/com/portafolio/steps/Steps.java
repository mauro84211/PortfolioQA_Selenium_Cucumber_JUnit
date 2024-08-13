package com.portafolio.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Steps extends BaseTest {

    /**
     * Sets up the environment before each scenario.
     *
     * @param scenario the scenario object representing the current test scenario
     */
    @Before()
    public void before(Scenario scenario) {
        // Set up the WebDriver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
        driver.manage().timeouts().getImplicitWaitTimeout();

        // Navigate to the desired page
        navigateTo();

    }

    /**
     * This method is called after each test case.
     * It quits the driver.
     */
    @After()
    public void after() {
        driver.quit();
    }


    /**
     * Asserts that the user is in the LogIn Page by checking the title of the page.
     */
    @Given("User is in LogIn Page")
    public void userIsInLogInPage() {
        // Assert that the title of the page is correct
        assertEquals("Swag Labs", driver.getTitle());
    }

    /**
     * Enters the provided username and password on the login page.
     *
     * @param username The username to enter.
     * @param password The password to enter.
     */
    @Given("User enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }

    @When("User clicks Login")
    public void userClicksLogin() {
        loginPage.clickButtonLogIn();
    }

    @Then("User should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Then("The User Locked Message Show Up")
    public void theUserLockedMessageShowUp() {
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getLoginMessage());
    }

    @Given("User logs in with valid credentials {string} and {string}")
    public void userLogsInWithValidCredentialsAnd(String username, String password) {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        productsPage = loginPage.clickButtonLogIn();
    }

    @Then("User sees the products")
    public void userSeesTheProducts() {
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        assertTrue(productsPage.isCarButtonDisplayed());
    }

    @When("User clicks on a {string} image")
    public void user_clicks_on_a_sauce_labs_backpack_image(String productName) {
        productDetailPage = productsPage.clickProductImage(productName);
    }

    @Then("User sees the product {string} and product {string}")
    public void user_sees_the_product_and_product(String productName, String productPrice) {
        assertEquals(productName, productDetailPage.getProductName());
        assertEquals("$" + productPrice, productDetailPage.getProductPrice());
    }


    @When("User add to cart a {string}")
    public void userAddToCartAProduct(String productName) {
        productsPage.addToCart(productName);
    }

    @Then("Increase the number of products in the shopping cart")
    public void increaseTheNumberOfProductsInTheShoppingCart() {
        assertEquals("1", productsPage.getShoppingCartBadge());
    }


    @Then("The option to remove the {string} from the shopping cart is activated")
    public void theOptionToRemoveTheProductFromTheShoppingCartIsActivated(String productName) {
        assertTrue(productsPage.isProductRemoveButtonDisplayed(productName));
    }


    @Given("User is logged with valid credentials")
    public void userIsLoggedWithValidCredentials() {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        productsPage = loginPage.clickButtonLogIn();
    }

    @When("User go to the Shopping Cart page")
    public void userGoToTheShoppingCartPage() {
        productsPage.clickCarButton();
        cartPage = productsPage.clickCarButton();
    }

    @Then("User can see a list with the {string} and {string} selected")
    public void userCanSeeAListWithTheAndSelected(String product, String price) {
        assertEquals(product, cartPage.getItemNameText());
        assertEquals("$" + price, cartPage.getItemPriceText());
    }

    @And("User go to Checkout")
    public void userGoToCheckout() {
        checkoutPage = cartPage.clickCheckoutButton();
    }

    @When("User fill the form")
    public void userFillTheForm() {
        checkoutPage.fillCheckoutForm("UserName", "UserLastName", "UserPostalCode");
        checkoutPage.clickContinue();
    }

    @Then("User can see their facture with {string} and {string}")
    public void userCanSeeTheirFactureWithAnd(String name, String price) {
        assertEquals(name, checkoutPage.getItemNameText());
        assertEquals("$" + price, checkoutPage.getItemPriceText());
        assertEquals("1", checkoutPage.getShoppingCartBadge());

        float tax = Float.parseFloat(price) / 12.4921f;
        String expectedTax = String.format("%.2f", tax);
        assertTrue(checkoutPage.getTaxLabel().contains(expectedTax));

        float total = Float.parseFloat(price) + tax;
        String expectedTotal = String.format("%.2f", total);
        assertTrue(checkoutPage.getTotalLabel().contains(expectedTotal));
    }
}