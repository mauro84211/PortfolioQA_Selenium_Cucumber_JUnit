package com.portafolio.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class CheckoutPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='first-name']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='last-name']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='postal-code']")
    WebElement postalCode;

    @FindBy(xpath = "//*[@id='continue']")
    WebElement continueButton;

    @FindBy(xpath = "//div[contains(@class, 'inventory_item_name')]")
    private WebElement itemName;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private WebElement itemPrice;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxLabel;

    @FindBy(xpath = "//div[@class='cart_quantity']")
    WebElement cartQuantity;

    @FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
    WebElement totalLabel;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getItemNameText() {
        return itemName.getText();
    }

    public String getItemPriceText() {
        return itemPrice.getText();
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
    }

    public void clickContinue(){
        continueButton.click();
    }

    public String getTaxLabel() {
        return taxLabel.getText();
    }

    public String getShoppingCartBadge() {
        return cartQuantity.getText();
    }

    public String getTotalLabel() {
        return totalLabel.getText();
    }
}