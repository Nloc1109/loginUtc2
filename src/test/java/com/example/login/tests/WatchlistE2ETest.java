package com.example.login.tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.login.base.BaseTest;
import com.example.login.pages.DashboardPage;
import com.example.login.pages.WatchlistPage;

public class WatchlistE2ETest extends BaseTest {

    private DashboardPage loginAsOrgAdmin() {
        java.nio.file.Path path = java.nio.file.Path.of("src/test/resources/static/digishield.html").toAbsolutePath();
        driver.get(path.toUri().toString());
        return new DashboardPage(driver);
    }

    private DashboardPage loginAsAnalyst() {
        java.nio.file.Path path = java.nio.file.Path.of("src/test/resources/static/digishield.html").toAbsolutePath();
        driver.get(path.toUri().toString() + "?role=analyst");
        return new DashboardPage(driver);
    }

    @Test
    @DisplayName("E2E: Them tai khoan nghi van vao danh sach den")
    void watchlist_whenAddScamAccount_displaysInTable() {
        // Du lieu test duy nhat moi lan chay (co lap du lieu)
        String scamAccount = "9999" + System.currentTimeMillis();

        DashboardPage dashboard = loginAsOrgAdmin();
        WatchlistPage watchlistPage = dashboard.goToWatchlistPage();

        // Thuc hien them moi
        watchlistPage.clickAddNew();
        watchlistPage.fillAccountDetails(scamAccount, "Tai khoan lua dao mao danh Viettel");
        watchlistPage.submitForm();

        // Kiem tra ket qua tren bang
        assertThat(watchlistPage.isAccountPresentInTable(scamAccount)).isTrue();
        assertThat(watchlistPage.getRiskBadgeFor(scamAccount)).isEqualTo("HIGH_RISK");
    }

    @Test
    @DisplayName("Phan quyen (RBAC UI Test): Analyst khong thay menu Cau hinh")
    void dashboard_whenLoginAsAnalyst_hidesAdminSettingsMenu() {
        DashboardPage dashboard = loginAsAnalyst();
        // Phan tu KHONG ton tai -> findElements().size() == 0
        assertThat(dashboard.hasAdminSettingsMenu()).isFalse();
    }
}
