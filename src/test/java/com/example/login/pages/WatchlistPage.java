package com.example.login.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WatchlistPage extends BasePage {
    private final By addNewBtn = By.id("btn-add-new");
    private final By accountInput = By.id("account-number");
    private final By reasonInput = By.id("reason");
    private final By riskSelectLocator = By.id("risk-level");
    private final By submitBtn = By.id("btn-submit-watchlist");

    public WatchlistPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddNew() {
        click(addNewBtn);
    }

    public void fillAccountDetails(String account, String reason) {
        type(accountInput, account);
        type(reasonInput, reason);
        WebElement selectEl = wait.until(ExpectedConditions.visibilityOfElementLocated(riskSelectLocator));
        new Select(selectEl).selectByValue("HIGH_RISK");
    }

    public void submitForm() {
        click(submitBtn);
    }

    public boolean isAccountPresentInTable(String account) {
        By rowLocator = By.xpath("//td[contains(text(), '" + account + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator)).isDisplayed();
    }

    public String getRiskBadgeFor(String account) {
        By badgeLocator = By.xpath("//tr[contains(., '" + account + "')]//span[contains(@class, 'badge')]");
        return getText(badgeLocator);
    }
}
