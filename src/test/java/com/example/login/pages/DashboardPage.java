package com.example.login.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    private final By userEmailLabel = By.id("user-email");
    private final By watchlistMenu = By.cssSelector("a[href='/watchlist']");
    private final By adminSettingsMenu = By.id("menu-admin-settings");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getLoggedInUserEmail() {
        return getText(userEmailLabel);
    }

    public boolean isWatchlistMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(watchlistMenu)).isDisplayed();
    }

    public boolean hasAdminSettingsMenu() {
        return driver.findElements(adminSettingsMenu).size() > 0;
    }

    public WatchlistPage goToWatchlistPage() {
        click(watchlistMenu);
        return new WatchlistPage(driver);
    }
}
