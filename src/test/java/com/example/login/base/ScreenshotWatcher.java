package com.example.login.base;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotWatcher implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext ctx) throws Exception {
        if (ctx.getExecutionException().isPresent()) {
            if (BaseTest.getDriver() != null) {
                File src = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
                Path dir = Path.of("build/screenshots");
                Files.createDirectories(dir);
                String safeName = ctx.getDisplayName().replaceAll("[^a-zA-Z0-9._-]", "_");
                Path dest = dir.resolve(safeName + ".png");
                Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("[ScreenshotWatcher] Screenshot saved to: " + dest.toAbsolutePath());
            }
        }
    }
}
