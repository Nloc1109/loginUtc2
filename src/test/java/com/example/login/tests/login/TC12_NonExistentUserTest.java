package com.example.login.tests.login;

import com.example.login.base.BaseTest;
import com.example.login.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class TC12_NonExistentUserTest extends BaseTest {

    @Test
    @DisplayName("TC12 - Đăng nhập với username giả định không tồn tại")
    void testLoginWithNonExistentUser() {
        LoginPage loginPage = new LoginPage(driver).open();

        String testUser = "user_qa_random_98765";
        String testPass = "wrongPassword123!";

        loginPage.loginAs(testUser, testPass);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.submit_login")));

        assertThat(loginPage.isOnLoginPage()).isTrue();
        assertThat(driver.getCurrentUrl()).contains("/Login");
    }
}
