package com.example.login.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return !driver.getCurrentUrl().contains("/Login");
    }
}
