package com.example.login.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Path;

public class CaptchaLoginPage extends BasePage {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By captchaField = By.id("captcha-input");
    private final By submitButton = By.id("btn-submit");
    private final By errorMessage = By.id("error-message");

    public CaptchaLoginPage(WebDriver driver) {
        super(driver);
    }

    public CaptchaLoginPage open() {
        Path path = Path.of("src/test/resources/static/login_captcha.html").toAbsolutePath();
        driver.get(path.toUri().toString());
        return this;
    }

    public void loginWithCaptcha(String username, String password, String captcha) {
        type(usernameField, username);
        type(passwordField, password);
        type(captchaField, captcha);
        click(submitButton);
    }

    public void submitWithoutCaptcha(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(submitButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
