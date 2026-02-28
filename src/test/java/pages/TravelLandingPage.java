package pages;

import config.TestConfig;
import driver.DriverManager;
import org.openqa.selenium.By;

public class TravelLandingPage extends BasePage {

    // TODO: Prefer stable attributes if available (data-testid / aria-label)
    private final By firstTimePurchaseButton = By.cssSelector("button[data-hrl-bo='purchase-for-new-customer']");

    public void open() {
        DriverManager.getDriver().get(TestConfig.baseUrl());
    }

    public void clickFirstTimePurchase() {
        waiter.click(firstTimePurchaseButton);
    }
}
