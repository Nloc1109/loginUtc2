package com.example.login.tests.security;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class TC18_BlockProtectedResourceTest extends BaseTest {

    @Test
    @DisplayName("TC18 - Chặn truy cập trang bảo vệ khi chưa đăng nhập")
    void testBlockProtectedResourceWithoutSession() {
        driver.manage().deleteAllCookies();

        driver.get("https://vanphongdientu.utc.edu.vn/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.submit_login")));

        assertThat(driver.getCurrentUrl()).contains("/Login");
        assertThat(driver.findElements(By.name("username"))).isNotEmpty();
    }
}
