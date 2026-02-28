package pages;

import org.openqa.selenium.By;

public class PassengersPage extends BasePage {

    private final By pageMarker = By.cssSelector("[data-hrl-bo='screen_title']");

    public boolean isOpened() {
        return waiter.isPresent(pageMarker);
    }
}
