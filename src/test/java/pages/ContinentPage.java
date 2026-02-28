package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ContinentPage extends BasePage {

    private final By europeOption = By.cssSelector("[role='radio'][data-hrl-bo='europe']");
    private final By nextToDatesButton = By.cssSelector("button[data-hrl-bo='wizard-next-button']");

    public void selectRandomContinent() {
        waiter.click(europeOption);
    }

    public void clickNextToDates() {
        waiter.click(nextToDatesButton);
    }
}
