package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatesPage extends BasePage {

    private static final DateTimeFormatter UI_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final By departureField = By.cssSelector("input[data-hrl-bo='startDateInput_input']");
    private final By returnField = By.cssSelector("input[data-hrl-bo='endDateInput_input']");
    private final By totalDaysValue = By.cssSelector("[data-hrl-bo='total-days']");
    private final By nextToPassengersButton = By.cssSelector("button#nextButton[data-hrl-bo='wizard-next-button']");

    public void setDepartureDate(LocalDate date) {
        setDateByTyping(departureField, date);
    }

    public void setReturnDate(LocalDate date) {
        setDateByTyping(returnField, date);
    }

    private void setDateByTyping(By field, LocalDate date) {
        String value = date.format(UI_DATE_FORMAT);
        waiter.type(field, value);
        DriverManager.getDriver().findElement(field).sendKeys(Keys.TAB);
    }

    public String getTotalDaysText() {
        try {
            WebElement el = waiter.visible(totalDaysValue);
            return el.getText().trim();
        } catch (Exception ex) {
            return "";
        }
    }

    public void clickNextToPassengers() {
        waiter.click(nextToPassengersButton);
    }
}
