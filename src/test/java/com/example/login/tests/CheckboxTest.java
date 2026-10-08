package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxTest extends BaseTest {

    @Test
    @DisplayName("Xu ly Checkbox: O 'Giu toi luon dang nhap' (trang UTC)")
    void testCheckboxInteraction() {
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        // O that (bi an hoac de doc trang thai): dung de doc trang thai
        WebElement checkbox = driver.findElement(By.id("persistent"));

        // O hien thi: dung de click (nhan label bao ngoai hoac label.check)
        WebElement fakeBox = driver.findElements(By.cssSelector("label.check")).isEmpty()
                ? driver.findElement(By.cssSelector("label[for='persistent']"))
                : driver.findElement(By.cssSelector("label.check"));

        if (!checkbox.isSelected()) {
            fakeBox.click();
        }
        assertThat(checkbox.isSelected()).isTrue();
    }
}
