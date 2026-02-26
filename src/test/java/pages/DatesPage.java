package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Waiter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesPage extends BasePage {

    // TODO: Validate and update these locators according to the real DOM.
    private final By departureField = By.xpath("//*[contains(text(),'תאריך יציאה') or contains(text(),'Departure Date')]/ancestor::*[self::label or self::div][1]//input|//input[contains(@aria-label,'יציאה') or contains(@aria-label,'Departure')]");
    private final By returnField = By.xpath("//*[contains(text(),'תאריך חזרה') or contains(text(),'Return Date')]/ancestor::*[self::label or self::div][1]//input|//input[contains(@aria-label,'חזרה') or contains(@aria-label,'Return')]");

    private final By totalDaysValue = By.xpath("//*[contains(.,'סה\"כ ימים') or contains(.,'סה״כ ימים') or contains(.,'Total Days')]/following::*[1]");

    private final By nextToPassengersButton = By.xpath(
            "//button[contains(.,'הלאה לפרטי הנוסעים') or .//*[contains(text(),'הלאה לפרטי הנוסעים')] or contains(.,'Next to Passenger Details') or .//*[contains(text(),'Next to Passenger Details')]]"
    );

    // Generic calendar selectors (usually needs adjustment)
    private final By calendarRoot = By.cssSelector("[role='dialog'], .react-datepicker, .mat-datepicker-content, .MuiPickersPopper-root");
    private final By nextMonthButton = By.cssSelector("[aria-label*='הבא'], [aria-label*='Next'], button[aria-label*='month']");
    private final By monthTitle = By.cssSelector(".react-datepicker__current-month, .MuiPickersCalendarHeader-label, .mat-calendar-period-button");

    public void setDepartureDate(LocalDate date) {
        waiter.click(departureField);
        selectDateFromPicker(date);
    }

    public void setReturnDate(LocalDate date) {
        waiter.click(returnField);
        selectDateFromPicker(date);
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

    /**
     * Generic, best-effort date picker selection.
     * Real-world: update locators according to the site's calendar component.
     */
    private void selectDateFromPicker(LocalDate target) {
        // Ensure calendar is open
        waiter.visible(calendarRoot);

        // Strategy:
        // 1) Move months forward until the displayed month matches target month/year
        // 2) Click the day
        // NOTE: Hebrew month names can appear; consider improving with locale parsing if needed.
        // TODO: Replace with robust month navigation once DOM is confirmed.

        // Best-effort: click "next month" up to 18 times max, then click day by aria-label/text.
        for (int i = 0; i < 18; i++) {
            if (isTargetMonthVisible(target)) break;
            if (waiter.isPresent(nextMonthButton)) {
                waiter.click(nextMonthButton);
            } else {
                break;
            }
        }

        // Click day
        String day = String.valueOf(target.getDayOfMonth());

        // Common patterns for day cells
        By dayCell = By.xpath(
                "//*[self::button or self::td or self::div][@aria-label and (contains(@aria-label,'" + day + "') or contains(@aria-label,'" + target + "'))]"
                + " | //button[normalize-space()='" + day + "']"
                + " | //div[normalize-space()='" + day + "' and (contains(@class,'day') or contains(@class,'Day') or @role='gridcell')]"
        );

        waiter.click(dayCell);
    }

    private boolean isTargetMonthVisible(LocalDate target) {
        // Very lightweight check: if month title contains year and/or month number.
        // TODO: Improve with strict parsing once month label format is known.
        try {
            String title = waiter.visible(monthTitle).getText();
            return title != null && title.contains(String.valueOf(target.getYear()));
        } catch (Exception ex) {
            return true; // if unknown, don't block
        }
    }
}
