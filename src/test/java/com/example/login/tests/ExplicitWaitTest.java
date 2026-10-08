package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ExplicitWaitTest extends BaseTest {

    @Test
    @DisplayName("Explicit Wait (WebDriverWait): elementToBeClickable va visibilityOfElementLocated tren UTC")
    void testExplicitWaitUtcPage() {
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Cho toi khi o nhap username hien thi va nhap lieu
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.clear();
        username.sendKeys("sinhvien01");

        // Cho toi khi nut "Dang nhap" hien ra va bam duoc
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.submit_login")));
        assertThat(btn.isDisplayed()).isTrue();
        btn.click();

        // Kiem tra phan tu van con hien thi sau khi click submit
        WebElement reloadedBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.submit_login")));
        assertThat(reloadedBtn).isNotNull();
    }

    @Test
    @DisplayName("Explicit Wait: Cho phan tu xuat hien bat dong bo (Dynamic SPA element)")
    void testExplicitWaitDynamicElement() {
        String html = "<!DOCTYPE html><html><body>"
                + "<button id='startBtn' onclick='setTimeout(function(){ "
                + "  var div = document.createElement(\"div\"); "
                + "  div.id = \"dynamicMsg\"; "
                + "  div.innerText = \"Du lieu tai thanh cong!\"; "
                + "  document.body.appendChild(div); "
                + "}, 400);'>Load Data</button>"
                + "</body></html>";

        driver.get("data:text/html;charset=utf-8," + html);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("startBtn")).click();

        // Cho toi khi phan tu dong xuat hien va nhin thay duoc
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicMsg")));
        assertThat(msg.getText()).isEqualTo("Du lieu tai thanh cong!");
    }
}
