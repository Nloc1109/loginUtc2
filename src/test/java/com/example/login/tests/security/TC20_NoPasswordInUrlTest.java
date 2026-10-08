package com.example.login.tests.security;

import com.example.login.base.BaseTest;
import com.example.login.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class TC20_NoPasswordInUrlTest extends BaseTest {

    @Test
    @DisplayName("TC20 - Không đưa mật khẩu hay token vào URL")
    void testNoPasswordOrTokenInUrl() {
        LoginPage loginPage = new LoginPage(driver).open();

        String testUser = "user_qa_random_98765";
        String testPass = "wrongPassword123!";

        loginPage.loginAs(testUser, testPass);

        String postSubmitUrl = driver.getCurrentUrl();

        assertThat(postSubmitUrl)
                .doesNotContain(testPass)
                .doesNotContain("userpwd=")
                .doesNotContain("password=");

        assertThat(driver.findElements(By.cssSelector("input.submit_login"))).isNotEmpty();
    }
}
