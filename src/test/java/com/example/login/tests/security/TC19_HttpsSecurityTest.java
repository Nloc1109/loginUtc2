package com.example.login.tests.security;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class TC19_HttpsSecurityTest extends BaseTest {

    @Test
    @DisplayName("TC19 - Thông tin đăng nhập chỉ truyền qua giao thức an toàn HTTPS")
    void testCredentialsOnlyTransmittedViaHttps() {
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).startsWith("https://");

        WebElement form = driver.findElement(By.cssSelector("form[action]"));
        String formAction = form.getAttribute("action");

        assertThat(formAction).doesNotStartWith("http://");
    }
}
