package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class WindowTabTest extends BaseTest {

    @Test
    @DisplayName("Chuyen Tab / Cua so moi (trang UTC)")
    void testSwitchTabAndWindow() {
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        String mainTab = driver.getWindowHandle();
        driver.findElement(By.cssSelector("a[href='http://hotrokythuat.utc.edu.vn']")).click();

        // Cho tab moi xuat hien
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        assertThat(driver.getCurrentUrl()).contains("hotrokythuat.utc.edu.vn");
        driver.close(); // Dong tab tro giup

        driver.switchTo().window(mainTab); // Ve lai trang dang nhap
        assertThat(driver.getCurrentUrl()).contains("/Login");
    }
}
