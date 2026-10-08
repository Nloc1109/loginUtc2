package com.example.login.tests;

import com.example.login.base.BaseTest;
import com.example.login.base.ScreenshotWatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenshotWatcherTest extends BaseTest {

    @Test
    @DisplayName("ScreenshotWatcher: Tu dong chup va luu anh man hinh khi test fail")
    void testScreenshotGeneratedOnFailure() throws Exception {
        driver.get("https://vanphongdientu.utc.edu.vn/Login");

        ScreenshotWatcher watcher = new ScreenshotWatcher();

        // Tao ExtensionContext gia lap bang Java Dynamic Proxy
        ExtensionContext dummyContext = (ExtensionContext) Proxy.newProxyInstance(
                ExtensionContext.class.getClassLoader(),
                new Class<?>[]{ExtensionContext.class},
                (proxy, method, methodArgs) -> {
                    if ("getExecutionException".equals(method.getName())) {
                        return Optional.of(new AssertionError("Gia lap test that bai"));
                    }
                    if ("getDisplayName".equals(method.getName())) {
                        return "testScreenshotGeneratedOnFailure";
                    }
                    return null;
                }
        );

        watcher.afterTestExecution(dummyContext);

        Path screenshotFile = Path.of("build/screenshots/testScreenshotGeneratedOnFailure.png");
        assertThat(Files.exists(screenshotFile)).isTrue();
        assertThat(Files.size(screenshotFile)).isGreaterThan(100);
    }
}
