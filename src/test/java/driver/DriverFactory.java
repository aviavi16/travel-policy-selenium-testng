package driver;

import config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    private DriverFactory() {}

    public static void createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Stability flags for CI/Linux containers
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Consistent viewport
        options.addArguments("--window-size=1920,1080");

        // Hebrew locale (helps with text/formatting)
        options.addArguments("--lang=he-IL");

        if (TestConfig.headless()) {
            options.addArguments("--headless=new");
        }

        DriverManager.setDriver(new ChromeDriver(options));
    }
}
