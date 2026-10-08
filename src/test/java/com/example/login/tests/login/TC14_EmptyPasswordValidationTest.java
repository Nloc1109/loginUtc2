package com.example.login.tests.login;

import com.example.login.base.BaseTest;
import com.example.login.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class TC14_EmptyPasswordValidationTest extends BaseTest {

    @Test
    @DisplayName("TC14 - Bỏ trống mật khẩu (validation)")
    void testEmptyPasswordValidation() {
        LoginPage loginPage = new LoginPage(driver).open();

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("userpwd"));

        usernameInput.clear();
        usernameInput.sendKeys("user_qa_random_98765");
        passwordInput.clear();

        driver.findElement(By.cssSelector("input.submit_login")).click();

        assertThat(loginPage.isOnLoginPage()).isTrue();
        assertThat(driver.getCurrentUrl()).contains("/Login");
        assertThat(driver.findElement(By.name("userpwd")).getText()).isEmpty();
    }
}
