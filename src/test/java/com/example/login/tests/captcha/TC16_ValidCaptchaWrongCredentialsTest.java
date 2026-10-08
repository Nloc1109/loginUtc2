package com.example.login.tests.captcha;

import com.example.login.base.BaseTest;
import com.example.login.pages.CaptchaLoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TC16_ValidCaptchaWrongCredentialsTest extends BaseTest {

    @Test
    @DisplayName("TC16 - Tài khoản sai nhưng CAPTCHA đúng")
    void testValidCaptchaWithWrongCredentials() {
        CaptchaLoginPage page = new CaptchaLoginPage(driver).open();

        page.loginWithCaptcha("user_qa_random_98765", "wrongPassword123!", "QA88");

        String error = page.getErrorMessage();
        assertThat(error).contains("Tên đăng nhập hoặc mật khẩu không chính xác");
        assertThat(error).doesNotContain("Mã bảo vệ (CAPTCHA) không chính xác");
    }
}
