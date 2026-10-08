package com.example.login.tests.captcha;

import com.example.login.base.BaseTest;
import com.example.login.pages.CaptchaLoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TC17_EmptyCaptchaValidationTest extends BaseTest {

    @Test
    @DisplayName("TC17 - Để trống CAPTCHA khi đang bắt buộc")
    void testEmptyCaptchaValidation() {
        CaptchaLoginPage page = new CaptchaLoginPage(driver).open();

        page.submitWithoutCaptcha("user_qa_random_98765", "wrongPassword123!");

        String error = page.getErrorMessage();
        assertThat(error).contains("Vui lòng nhập mã bảo vệ (CAPTCHA)");
    }
}
