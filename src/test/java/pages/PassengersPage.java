package pages;

import org.openqa.selenium.By;

public class PassengersPage extends BasePage {

    // TODO: Replace with a stable element unique to the passengers screen.
    // Examples: page header, first passenger form field label, etc.
    private final By pageMarker = By.xpath("//*[contains(.,'פרטי הנוסעים') or contains(.,'נוסעים')]");

    public boolean isOpened() {
        return waiter.isPresent(pageMarker);
    }
}
