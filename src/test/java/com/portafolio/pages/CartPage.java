package com.portafolio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

public class CartPage  {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'inventory_item_name')]")
    WebElement itemName;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    WebElement itemPrice;

    @FindBy(xpath = "//*[@id='checkout']")
    WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getItemNameText() {
        return itemName.getText();
    }

    public String getItemPriceText() {
        return itemPrice.getText();
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}