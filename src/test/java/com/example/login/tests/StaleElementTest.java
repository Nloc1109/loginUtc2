package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StaleElementTest extends BaseTest {

    @Test
    @DisplayName("Xu ly ngoai le StaleElementReferenceException bang ExpectedConditions.refreshed")
    void testStaleElementRecovery() {
        String html = "<!DOCTYPE html><html><body>"
                + "<div id='container'>"
                + "  <button id='refresh-btn' onclick='replaceBtn()'>Click First Time</button>"
                + "</div>"
                + "<p id='status'>Initial</p>"
                + "<script>"
                + "  function replaceBtn() {"
                + "    var container = document.getElementById('container');"
                + "    container.innerHTML = '<button id=\"refresh-btn\" onclick=\"finish()\">Click Second Time</button>';"
                + "  }"
                + "  function finish() {"
                + "    document.getElementById('status').innerText = 'Re-clicked Successfully!';"
                + "  }"
                + "</script>"
                + "</body></html>";

        driver.get("data:text/html;charset=utf-8," + html);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 1. Lay tham chieu den button ban dau
        WebElement btn = driver.findElement(By.id("refresh-btn"));
        btn.click(); // Ham replaceBtn() duoc goi, DOM thay the phan tu cu bang phan tu moi

        // 2. Thu click tiep vao bien tham chieu cu -> phan tu cu da chet trong DOM nen nem loi StaleElementReferenceException
        assertThatThrownBy(btn::click)
                .isInstanceOf(StaleElementReferenceException.class);

        // 3. Cach xu ly chuan: dung ExpectedConditions.refreshed(...) de tim lai phan tu moi
        WebElement refreshedBtn = wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(By.id("refresh-btn"))
        ));
        refreshedBtn.click();

        // Xac nhan phan tu moi da duoc click thanh cong
        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        assertThat(status.getText()).isEqualTo("Re-clicked Successfully!");
    }
}
