package com.example.login.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class SeleniumService {

    /**
     * Khởi tạo WebDriver (Chrome headless) và lấy tiêu đề của một trang web.
     * @param url Đường dẫn trang web cần mở
     * @return Tiêu đề trang web
     */
    public String scrapePageTitle(String url) {
        // Tự động tải và cấu hình ChromeDriver tương thích với trình duyệt Chrome trên máy
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Chạy ngầm, không bật UI
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
            return driver.getTitle();
        } finally {
            driver.quit();
        }
    }
}
