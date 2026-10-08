package com.example.login.tests.login;

import com.example.login.base.BaseTest;
import com.example.login.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class TC13_EmptyBothFieldsTest extends BaseTest {

    @Test
    @DisplayName("TC13 - Bỏ trống cả hai trường tên đăng nhập và mật khẩu")
    void testEmptyBothFields() {
        LoginPage loginPage = new LoginPage(driver).open();

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("userpwd"));
        WebElement submitBtn = driver.findElement(By.cssSelector("input.submit_login"));

        usernameInput.clear();
        passwordInput.clear();
        submitBtn.click();

        assertThat(loginPage.isOnLoginPage()).isTrue();
        assertThat(driver.getCurrentUrl()).contains("/Login");
        assertThat(driver.findElement(By.cssSelector("input.submit_login")).isDisplayed()).isTrue();
    }
}
