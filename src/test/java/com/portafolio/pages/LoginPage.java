package com.portafolio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='user-name']")
    WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordinput;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement buttonLogIn;

    @FindBy(xpath = "//div[@id='login_button_container']/div/form/div[3]/h3")
    WebElement loginMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String pass) {
        passwordinput.sendKeys(pass);
    }

    public ProductsPage clickButtonLogIn() {
        buttonLogIn.click();
        return new ProductsPage(this.driver);
    }

    public String getLoginMessage() {
        return loginMessage.getText();
    }
}
