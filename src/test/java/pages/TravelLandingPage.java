package pages;

import config.TestConfig;
import driver.DriverManager;
import org.openqa.selenium.By;

public class TravelLandingPage extends BasePage {

    // TODO: Prefer stable attributes if available (data-testid / aria-label)
    private final By firstTimePurchaseButton = By.xpath(
            "//button[contains(.,'לרכישה בפעם הראשונה') or .//*[contains(text(),'לרכישה בפעם הראשונה')]]"
    );

    public void open() {
        DriverManager.getDriver().get(TestConfig.baseUrl());
    }

    public void clickFirstTimePurchase() {
        waiter.click(firstTimePurchaseButton);
    }
}
