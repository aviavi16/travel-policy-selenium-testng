package tests;

import driver.DriverFactory;
import driver.DriverManager;
import io.qameta.allure.Attachment;
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
            attachUrlSafe();
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
}
