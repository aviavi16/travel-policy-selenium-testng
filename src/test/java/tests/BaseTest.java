package tests;

import driver.DriverFactory;
import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtil;

public abstract class BaseTest {

    @BeforeMethod
    public void setup() {
        DriverFactory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        if (!result.isSuccess()) {
            attachScreenshot();
            attachDomHtmlSafe();
            attachUrlSafe();
            attachBrowserConsoleLogsSafe();
        }
        DriverManager.quitDriver();
    }

    @Attachment(value = "Screenshot (PNG)", type = "image/png")
    public byte[] attachScreenshot() {
        return ScreenshotUtil.png();
    }

    @Attachment(value = "URL", type = "text/plain")
    public String attachUrlSafe() {
        try {
            return DriverManager.getDriver().getCurrentUrl();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    @Attachment(value = "DOM HTML", type = "text/html")
    public String attachDomHtmlSafe() {
        try {
            return DriverManager.getDriver().getPageSource();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    @Attachment(value = "Browser Console Logs", type = "text/plain")
    public String attachBrowserConsoleLogsSafe() {
        try {
            LogEntries entries = DriverManager.getDriver().manage().logs().get(LogType.BROWSER);
            StringBuilder sb = new StringBuilder();

            for (LogEntry entry : entries) {
                sb.append("[")
                  .append(entry.getLevel())
                  .append("] ")
                  .append(entry.getMessage())
                  .append(" (timestamp=")
                  .append(entry.getTimestamp())
                  .append(")")
                  .append(System.lineSeparator());
            }

            if (sb.length() == 0) {
                return "No browser console logs captured.";
            }

            return sb.toString();
        } catch (Exception ex) {
            return "Console logs are not available for this driver/browser setup.";
        }
    }
}
