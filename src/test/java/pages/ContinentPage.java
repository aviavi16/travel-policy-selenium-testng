package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ContinentPage extends BasePage {

    // TODO: Update selectors after validating in DevTools.
    // This is a generic example: list items/buttons representing continents.
    private final By continentOptions = By.cssSelector("button, [role='button']");

    private final By nextToDatesButton = By.xpath(
            "//button[contains(.,'הלאה לבחירת תאריכי הנסיעה') or .//*[contains(text(),'הלאה לבחירת תאריכי הנסיעה')]]"
    );

    public void selectRandomContinent() {
        List<WebElement> candidates = waiter.visible(continentOptions).findElements(By.xpath("./ancestor-or-self::*"));
        // The above is conservative; real implementation should target a specific container.
        // Fallback: try to find buttons that contain known continent keywords.
        // TODO: Replace with stable locators.

        // Safer fallback: click one known text option if exists.
        By europe = By.xpath("//button[contains(.,'אירופה') or .//*[contains(text(),'אירופה')]]");
        if (waiter.isPresent(europe)) {
            waiter.click(europe);
            return;
        }

        // If we cannot find specific ones, do a best-effort random click on visible buttons.
        // TODO: Replace with stable list container and option locators.
        List<WebElement> buttons = driver.DriverManager.getDriver().findElements(By.tagName("button"));
        if (buttons.isEmpty()) {
            throw new IllegalStateException("No continent options found. Please update selectors in ContinentPage.");
        }
        WebElement chosen = buttons.get(new Random().nextInt(buttons.size()));
        waiter.scrollIntoView(chosen);
        chosen.click();
    }

    public void clickNextToDates() {
        waiter.click(nextToDatesButton);
    }
}
