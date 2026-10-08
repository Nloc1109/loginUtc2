package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicInteractionTest extends BaseTest {

    @Test
    @DisplayName("Thao tac co ban (1): Mo trang, nhap lieu, bam nut dang nhap")
    void testBasicFormInteraction() {
        // 1. Dieu huong toi trang dang nhap
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        // 2. Nhap lieu (luon clear truoc khi go text moi)
        WebElement username = driver.findElement(By.name("username"));
        username.clear();
        username.sendKeys("sinhvien01");

        WebElement password = driver.findElement(By.name("userpwd"));
        password.clear();
        password.sendKeys("matkhau123");

        // 3. Bam nut "Dang nhap"
        WebElement submitBtn = driver.findElement(By.cssSelector("input.submit_login"));
        submitBtn.click();

        // Kiem tra da thuc hien submit (van o trang login hoac trang thong bao sai mat khau)
        assertThat(driver.getCurrentUrl()).contains("/Login");
    }
}
