package com.example.login.tests.captcha;

import com.example.login.base.BaseTest;
import com.example.login.pages.CaptchaLoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TC15_WrongCaptchaTest extends BaseTest {

    @Test
    @DisplayName("TC15 - Nhập sai CAPTCHA cùng thông tin đăng nhập không hợp lệ")
    void testLoginWithWrongCaptcha() {
        CaptchaLoginPage page = new CaptchaLoginPage(driver).open();

        page.loginWithCaptcha("user_qa_random_98765", "wrongPassword123!", "SAI_CODE");

        String error = page.getErrorMessage();
        assertThat(error).contains("Mã bảo vệ (CAPTCHA) không chính xác");
    }
}
