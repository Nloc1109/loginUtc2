# Dự Án Kiểm Thử Tự Động Web UI Với Selenium & Spring Boot Gradle

Dự án kiểm thử tự động (Automation Testing / E2E Testing) cho chức năng Đăng nhập và các nghiệp vụ Web UI, xây dựng trên nền tảng **Java 21**, **Spring Boot**, **Gradle**, và **Selenium WebDriver**.

---

## 🛠️ Công Nghệ Sử Dụng

- **Ngôn ngữ:** Java 21 (LTS)
- **Framework:** Spring Boot 4.x
- **Build Tool:** Gradle Wrapper (`gradlew`)
- **Thư viện kiểm thử:**
  - `org.seleniumhq.selenium:selenium-java:4.33.0`
  - `io.github.bonigarcia:webdrivermanager:6.1.0` (Tự động tải và đồng bộ ChromeDriver)
  - `JUnit 5` (Jupiter) & `AssertJ`
- **Mô hình thiết kế:** Page Object Model (POM)

---

## 📂 Cấu Trúc Dự Án

```text
Login/
├── build.gradle                               # Cấu hình dependency & build
├── src/
│   ├── main/java/com/example/login/
│   │   ├── LoginApplication.java              # Ứng dụng Spring Boot chính
│   │   ├── controller/SeleniumController.java # REST API thử nghiệm Selenium
│   │   └── service/SeleniumService.java       # Service khởi tạo Chrome headless
│   │
│   ├── test/java/com/example/login/
│   │   ├── base/
│   │   │   ├── BaseTest.java                  # Khởi tạo ChromeDriver, Headless mode, Timeout
│   │   │   └── ScreenshotWatcher.java         # Tự động chụp ảnh màn hình khi test FAIL
│   │   │
│   │   ├── pages/                             # Tầng Page Object Model (POM)
│   │   │   ├── BasePage.java                  # Lớp cha bọc Explicit Wait, click, type
│   │   │   ├── LoginPage.java                 # Trang đăng nhập UTC
│   │   │   ├── HomePage.java                  # Trang chủ sau đăng nhập
│   │   │   ├── DashboardPage.java             # Trang Dashboard DigiShield
│   │   │   ├── WatchlistPage.java             # Trang quản trị danh sách đen
│   │   │   └── CaptchaLoginPage.java          # Trang đăng nhập kiểm thử CAPTCHA
│   │   │
│   │   └── tests/                             # Các kịch bản kiểm thử (Test Scripts)
│   │       ├── BasicInteractionTest.java      # TC01: Thao tác cơ bản 1 (Mở trang, nhập, bấm nút)
│   │       ├── ElementStateAndDataTest.java   # TC02: Thao tác cơ bản 2 (Đọc thuộc tính & trạng thái)
│   │       ├── DropdownTest.java              # TC03: Xử lý Dropdown (<select>)
│   │       ├── CheckboxTest.java              # TC04: Xử lý Checkbox bị ẩn & label hiển thị
│   │       ├── AlertConfirmTest.java          # TC05: Xử lý JavaScript Alert & Confirm Dialog
│   │       ├── WindowTabTest.java             # TC06: Chuyển Tab / Cửa sổ mới (target="_blank")
│   │       ├── ExplicitWaitTest.java          # TC07: Explicit Wait (WebDriverWait & ExpectedConditions)
│   │       ├── StaleElementTest.java          # TC08: Xử lý lỗi StaleElementReferenceException
│   │       ├── LoginE2ETest.java              # TC09: Kiểm thử E2E với POM (Đúng & Sai mật khẩu)
│   │       ├── WatchlistE2ETest.java          # TC10: Kịch bản E2E hoàn chỉnh & RBAC UI Test
│   │       ├── ScreenshotWatcherTest.java     # TC11: Tự động chụp ảnh khi test gãy
│   │       │
│   │       ├── login/                         # Nhóm kiểm thử đăng nhập mở rộng
│   │       │   ├── TC12_NonExistentUserTest.java           # TC12: Username không tồn tại
│   │       │   ├── TC13_EmptyBothFieldsTest.java           # TC13: Bỏ trống cả 2 trường
│   │       │   └── TC14_EmptyPasswordValidationTest.java   # TC14: Bỏ trống mật khẩu (Validation)
│   │       │
│   │       ├── captcha/                       # Nhóm kiểm thử CAPTCHA (QA Test Mode)
│   │       │   ├── TC15_WrongCaptchaTest.java              # TC15: Nhập sai mã CAPTCHA
│   │       │   ├── TC16_ValidCaptchaWrongCredentialsTest.java # TC16: CAPTCHA đúng nhưng tài khoản sai
│   │       │   └── TC17_EmptyCaptchaValidationTest.java    # TC17: Để trống CAPTCHA khi bắt buộc
│   │       │
│   │       └── security/                      # Nhóm kiểm thử bảo mật giao diện
│   │           ├── TC18_BlockProtectedResourceTest.java    # TC18: Chặn trang bảo vệ khi chưa đăng nhập
│   │           ├── TC19_HttpsSecurityTest.java             # TC19: Dữ liệu chỉ truyền qua HTTPS
│   │           └── TC20_NoPasswordInUrlTest.java           # TC20: Không để lộ mật khẩu/token trên URL
```

---

## 🚀 Hướng Dẫn Cài Đặt & Chạy

### 1. Yêu cầu môi trường
- Đã cài đặt **JDK 21** (`java -version`).
- Đã cài đặt trình duyệt **Google Chrome** (Selenium & WebDriverManager sẽ tự động nhận diện).

### 2. Chạy ứng dụng Spring Boot
Mở Terminal/PowerShell tại thư mục dự án:
```powershell
.\gradlew.bat bootRun
```
Ứng dụng sẽ khởi chạy tại cổng mặc định: `http://localhost:8080`.

---

### 3. Chạy kiểm thử tự động (Selenium Tests)

#### 👉 Chạy toàn bộ 20 Test Cases:
```powershell
.\gradlew.bat test
```

#### 👉 Chạy một Test Case hoặc nhóm cụ thể:
- Chạy nhóm kiểm thử Đăng nhập (TC12 - TC14):
  ```powershell
  .\gradlew.bat test --tests "*login*"
  ```
- Chạy nhóm kiểm thử CAPTCHA (TC15 - TC17):
  ```powershell
  .\gradlew.bat test --tests "*captcha*"
  ```
- Chạy nhóm kiểm thử Security (TC18 - TC20):
  ```powershell
  ```powershell
  .\gradlew.bat test --tests "*security*"
  ```
- Chạy riêng một Test Case bất kỳ (ví dụ TC12):
  ```powershell
  .\gradlew.bat test --tests "*TC12*"
  ```

---

## 📊 Xem Báo Cáo Kiểm Thử (Test Report) & Ảnh Chụp Lỗi

### 1. Báo cáo kiểm thử HTML:
Sau khi chạy test, Gradle sẽ tự động xuất báo cáo chi tiết trực quan tại:
```
build/reports/tests/test/index.html
```
*(Bạn có thể click đúp chuột vào file này để mở trên trình duyệt).*

### 2. Ảnh chụp màn hình khi test FAIL:
Nếu có bất kỳ test case nào bị thất bại (gãy assertion hoặc timeout), cơ chế `ScreenshotWatcher` sẽ tự động chụp ảnh màn hình thời điểm xảy ra lỗi và lưu tại:
```
build/screenshots/
```

---

## 📋 Danh Sách Chi Tiết 20 Test Cases

| STT | Mã Commit / Test | Tên Test Case | Mục Tiêu & Kịch Bản Kiểm Thử |
| :---: | :---: | :--- | :--- |
| 1 | **TC01** | Thao tác cơ bản (1) | Mở trang đăng nhập UTC, nhập thông tin và bấm nút Đăng nhập |
| 2 | **TC02** | Thao tác cơ bản (2) | Đọc dữ liệu thuộc tính HTML (`placeholder`, `value`, `getText`) và kiểm tra trạng thái hiển thị (`isDisplayed`, `isEnabled`) |
| 3 | **TC03** | Xử lý Dropdown | Thao tác thẻ `<select>` qua lớp `Select` (`selectByVisibleText`, `selectByValue`, `getFirstSelectedOption`) |
| 4 | **TC04** | Xử lý Checkbox | Xử lý ô checkbox bị ẩn (`display:none`) trên trang UTC bằng cách click vào `<label>` hiển thị |
| 5 | **TC05** | Xử lý JS Alert / Confirm | Chuyển ngữ cảnh sang dialog của trình duyệt (`switchTo().alert()`), lấy nội dung, chấp nhận (`accept`) hoặc hủy (`dismiss`) |
| 6 | **TC06** | Chuyển Tab / Cửa sổ mới | Xử lý link mở tab mới (`target="_blank"`), chuyển tab bằng `getWindowHandles()` và đóng tab phụ |
| 7 | **TC07** | Explicit Wait chuẩn mực | Sử dụng `WebDriverWait` và `ExpectedConditions` (`elementToBeClickable`, `visibilityOfElementLocated`) để chống Flaky test |
| 8 | **TC08** | Xử lý Stale Element | Khắc phục `StaleElementReferenceException` khi trang web re-render DOM bằng `ExpectedConditions.refreshed(...)` |
| 9 | **TC09** | Page Object Model (POM) | Áp dụng cấu trúc POM chuẩn mực, kiểm thử đăng nhập hợp lệ và sai mật khẩu |
| 10 | **TC10** | E2E Watchlist & RBAC UI | Kịch bản E2E thêm tài khoản danh sách đen và kiểm tra phân quyền ẩn menu theo vai trò (Role-Based Access Control) |
| 11 | **TC11** | Chụp ảnh màn hình tự động | Kiểm thử JUnit 5 Extension `ScreenshotWatcher` tự động ghi lại hình ảnh màn hình khi phát hiện test thất bại |
| 12 | **TC12** | Username không tồn tại | Đăng nhập với tài khoản giả định không có trong hệ thống; xác nhận từ chối và vẫn ở trang đăng nhập |
| 13 | **TC13** | Bỏ trống cả 2 trường | Bấm Đăng nhập khi không nhập gì; xác nhận từ chối truy cập và giữ nguyên tại form đăng nhập |
| 14 | **TC14** | Bỏ trống mật khẩu | Nhập tên đăng nhập nhưng để trống mật khẩu; kiểm tra validation yêu cầu mật khẩu bắt buộc |
| 15 | **TC15** | Nhập sai CAPTCHA | Nhập sai mã bảo vệ cùng thông tin đăng nhập; xác nhận thông báo lỗi CAPTCHA không chính xác |
| 16 | **TC16** | CAPTCHA đúng, tài khoản sai | Vượt qua kiểm tra CAPTCHA nhưng xác thực tài khoản thất bại với thông báo sai tên đăng nhập/mật khẩu |
| 17 | **TC17** | Để trống CAPTCHA | Nhập thông tin nhưng bỏ trống ô CAPTCHA; kiểm tra validation yêu cầu bắt buộc nhập mã bảo vệ |
| 18 | **TC18** | Chặn trang bảo vệ | Truy cập trực tiếp trang tài nguyên nội bộ khi chưa có session; xác nhận bị chuyển hướng về `/Login` |
| 19 | **TC19** | Bảo mật HTTPS | Xác minh thông tin đăng nhập và endpoint form chỉ được truyền tải qua giao thức mã hóa an toàn HTTPS |
| 20 | **TC20** | Không lộ mật khẩu trên URL | Gửi form đăng nhập và xác minh tuyệt đối không để lộ mật khẩu hoặc token trên thanh địa chỉ (Query String) |
