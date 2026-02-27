package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SessionTimeoutPage extends BasePage {

    private final By timeoutTitle = By.cssSelector("h1[data-hrl-bo='error-title'][role='alert']");

    public boolean isVisible() {
        try {
            List<WebElement> matches = DriverManager.getDriver().findElements(timeoutTitle);
            return !matches.isEmpty() && matches.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMessage() {
        try {
            List<WebElement> matches = DriverManager.getDriver().findElements(timeoutTitle);
            if (matches.isEmpty()) {
                return "";
            }
            return matches.get(0).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
