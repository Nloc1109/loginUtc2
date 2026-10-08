package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertConfirmTest extends BaseTest {

    @Test
    @DisplayName("Xu ly JavaScript Alert va Confirm Dialog")
    void testAlertAndConfirmHandling() {
        String html = "<!DOCTYPE html><html><body>"
                + "<button id='alertBtn' onclick='alert(\"Thong bao tu he thong!\")'>Show Alert</button>"
                + "<button id='confirmBtn' onclick='var r = confirm(\"Ban co chac chan muon xoa?\"); document.getElementById(\"res\").innerText = r ? \"ACCEPTED\" : \"DISMISSED\";'>Show Confirm</button>"
                + "<p id='res'></p>"
                + "</body></html>";

        driver.get("data:text/html;charset=utf-8," + html);

        // 1. Xu ly Alert
        driver.findElement(By.id("alertBtn")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Noi dung alert: " + alert.getText());
        assertThat(alert.getText()).isEqualTo("Thong bao tu he thong!");
        alert.accept();

        // 2. Xu ly Confirm Dialog (Dismiss/Cancel)
        driver.findElement(By.id("confirmBtn")).click();
        Alert confirm = driver.switchTo().alert();
        assertThat(confirm.getText()).contains("Ban co chac chan");
        confirm.dismiss(); // Bam Cancel

        assertThat(driver.findElement(By.id("res")).getText()).isEqualTo("DISMISSED");

        // 3. Xu ly Confirm Dialog (Accept/OK)
        driver.findElement(By.id("confirmBtn")).click();
        Alert confirm2 = driver.switchTo().alert();
        confirm2.accept(); // Bam OK

        assertThat(driver.findElement(By.id("res")).getText()).isEqualTo("ACCEPTED");
    }
}
