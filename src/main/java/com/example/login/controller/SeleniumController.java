package com.example.login.controller;

import com.example.login.service.SeleniumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/selenium")
public class SeleniumController {

    private final SeleniumService seleniumService;

    public SeleniumController(SeleniumService seleniumService) {
        this.seleniumService = seleniumService;
    }

    @GetMapping("/test")
    public Map<String, String> testSelenium(@RequestParam(defaultValue = "https://www.google.com") String url) {
        String title = seleniumService.scrapePageTitle(url);
        return Map.of(
            "status", "success",
            "url", url,
            "pageTitle", title
        );
    }
}
