package pages;

import config.TestConfig;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatesPage extends BasePage {

    private static final DateTimeFormatter UI_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Logger log = LoggerFactory.getLogger(DatesPage.class);

    private final By departureField = By.cssSelector("input[data-hrl-bo='startDateInput_input']");
    private final By returnField = By.cssSelector("input[data-hrl-bo='endDateInput_input']");
    private final By totalDaysValue = By.cssSelector("[data-hrl-bo='total-days']");
    private final By nextToPassengersButton = By.cssSelector("button#nextButton[data-hrl-bo='wizard-next-button']");

    private void debug(String message, Object... args) {
        if (TestConfig.debugFlow()) {
            log.info(message, args);
        }
    }

    public void setDepartureDate(LocalDate date) {
        setDateByTyping(departureField, date);
    }

    public void setReturnDate(LocalDate date) {
        setDateByTyping(returnField, date);
    }

    private void setDateByTyping(By field, LocalDate date) {
        String value = date.format(UI_DATE_FORMAT);
        debug("[DATES] typing value='{}' into field={}", value, field);
        waiter.type(field, value);
        DriverManager.getDriver().findElement(field).sendKeys(Keys.TAB);
        debug("[DATES] typed value and tabbed. currentUrl={}", DriverManager.getDriver().getCurrentUrl());
    }

    public String getTotalDaysText() {
        try {
            WebElement el = waiter.visible(totalDaysValue);
            String text = el.getText().trim();
            debug("[DATES] total-days text='{}'", text);
            return text;
        } catch (Exception ex) {
            debug("[DATES] total-days element not visible on current page. url={}", DriverManager.getDriver().getCurrentUrl());
            return "";
        }
    }

    public void clickNextToPassengers() {
        debug("[DATES] clicking next-to-passengers locator={}", nextToPassengersButton);
        waiter.click(nextToPassengersButton);
    }
}
