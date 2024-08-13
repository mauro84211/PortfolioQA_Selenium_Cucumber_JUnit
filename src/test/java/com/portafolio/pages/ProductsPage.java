package com.portafolio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement carButton;

    @FindBy(xpath = "//div[3]/a")
    private WebElement shoppingCart;

    @FindBy(css = ".inventory_list")
    private WebElement productsList;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage clickCarButton() {
        carButton.click();
        return new CartPage(this.driver);
    }

    public boolean isCarButtonDisplayed() {
        return shoppingCart.isDisplayed();
    }

    public ProductDetailPage clickProductImage(String productName) {
        Duration longduration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, longduration);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='" + productName + "']")));
        element.click();

        return new ProductDetailPage(driver);
    }

    public void addToCart(String productName) {
        List<WebElement> products = productsList.findElements(By.cssSelector(".inventory_item"));
        products.stream()
                .filter(product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
                .findFirst()
                .ifPresent(product -> product.findElement(By.cssSelector(".btn_inventory")).click());
    }

    public String getShoppingCartBadge() {
        return shoppingCartBadge.getText();
    }

    public boolean isProductRemoveButtonDisplayed(String productName) {
        return productsList.findElements(By.cssSelector(".inventory_item"))
                .stream()
                .filter(product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
                .map(product -> product.findElement(By.xpath("//button[contains(.,'Remove')]")))
                .anyMatch(WebElement::isDisplayed);
    }
}