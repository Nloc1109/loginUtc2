package com.example.login.tests;

import com.example.login.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.assertThat;

public class DropdownTest extends BaseTest {

    @Test
    @DisplayName("Xu ly Dropdown (<select>): Chon theo visible text, value va kiem tra option duoc chon")
    void testDropdownSelection() {
        String html = "<!DOCTYPE html><html><body>"
                + "<select id='risk-level'>"
                + "<option value='LOW_RISK'>Rui ro thap (LOW)</option>"
                + "<option value='MEDIUM_RISK'>Rui ro trung binh (MEDIUM)</option>"
                + "<option value='HIGH_RISK'>Rui ro cao (HIGH)</option>"
                + "</select>"
                + "</body></html>";

        driver.get("data:text/html;charset=utf-8," + html);

        WebElement dropdownEl = driver.findElement(By.id("risk-level"));
        Select riskSelect = new Select(dropdownEl);

        // 1. Chon theo chu hien thi tren UI
        riskSelect.selectByVisibleText("Rui ro cao (HIGH)");
        assertThat(riskSelect.getFirstSelectedOption().getText()).contains("HIGH");

        // 2. Chon theo gia tri thuoc tinh value
        riskSelect.selectByValue("MEDIUM_RISK");
        assertThat(riskSelect.getFirstSelectedOption().getText()).contains("MEDIUM");

        riskSelect.selectByValue("HIGH_RISK");

        // 3. Kiem tra gia tri dang duoc chon
        String selectedText = riskSelect.getFirstSelectedOption().getText();
        assertThat(selectedText).contains("HIGH");
    }
}
