package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementStateAndDataTest extends BaseTest {

    @Test
    @DisplayName("Thao tac co ban (2): Doc du lieu va kiem tra trang thai phan tu")
    void testReadDataAndCheckState() {
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        WebElement username = driver.findElement(By.name("username"));
        WebElement submitBtn = driver.findElement(By.cssSelector("input.submit_login"));
        WebElement forgotLink = driver.findElement(By.cssSelector("a[href='/Login/GetPass']"));

        // 4. Doc thuoc tinh trong HTML
        String hint = username.getAttribute("placeholder");
        String label = submitBtn.getAttribute("value");
        String forgot = forgotLink.getText();

        // Kiem tra gia tri thuoc tinh va text
        assertThat(hint).isNotBlank();
        assertThat(label).isNotBlank();
        assertThat(forgot).contains("quên");

        // 5. Kiem tra trang thai phan tu
        boolean isVisible = submitBtn.isDisplayed();
        boolean isEnabled = submitBtn.isEnabled();

        assertThat(isVisible).isTrue();
        assertThat(isEnabled).isTrue();
    }
}
