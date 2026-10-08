package com.example.login.tests;

import com.example.login.base.BaseTest;
import com.example.login.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginE2ETest extends BaseTest {

    @Test
    @DisplayName("POM: Mo trang login -> dung dia chi /Login")
    void login_whenOpen_navigatesToLoginPage() {
        LoginPage loginPage = new LoginPage(driver).open();
        assertThat(loginPage.isOnLoginPage()).isTrue();
    }

    @Test
    @DisplayName("POM: Sai mat khau -> van o lai trang Login")
    void login_whenWrongPassword_staysOnLoginPage() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.loginAs("sinhvien01", "SaiMatKhau");
        assertThat(loginPage.isOnLoginPage()).isTrue();
    }
}
