package flows;

import config.TestConfig;
import driver.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ContinentPage;
import pages.DatesPage;
import pages.PassengersPage;
import pages.TravelLandingPage;

import java.time.LocalDate;

public class PurchaseFlow {

    private static final Logger log = LoggerFactory.getLogger(PurchaseFlow.class);

    private final TravelLandingPage landingPage = new TravelLandingPage();
    private final ContinentPage continentPage = new ContinentPage();
    private final DatesPage datesPage = new DatesPage();
    private final PassengersPage passengersPage = new PassengersPage();

    private void debug(String message, Object... args) {
        if (TestConfig.debugFlow()) {
            log.info(message, args);
        }
    }

    public void startPurchase() {
        debug("[FLOW] startPurchase: opening landing page");
        landingPage.open();
        debug("[FLOW] after open url={}", DriverManager.getDriver().getCurrentUrl());
        landingPage.clickFirstTimePurchase();
        debug("[FLOW] after first-time purchase click url={}", DriverManager.getDriver().getCurrentUrl());
    }

    public void chooseContinentAndContinue() {
        debug("[FLOW] chooseContinentAndContinue: selecting continent");
        continentPage.selectRandomContinent();
        debug("[FLOW] continent selected");
        continentPage.clickNextToDates();
        debug("[FLOW] after next-to-dates url={}", DriverManager.getDriver().getCurrentUrl());
    }

    public void chooseDatesAndContinue(LocalDate departure, LocalDate ret) {
        datesPage.setDepartureDate(departure);
        datesPage.setReturnDate(ret);
        datesPage.clickNextToPassengers();
    }

    public void chooseDates(LocalDate departure, LocalDate ret) {
        debug("[FLOW] chooseDates: departure={}, return={}", departure, ret);
        datesPage.setDepartureDate(departure);
        datesPage.setReturnDate(ret);
        debug("[FLOW] dates selected url={}", DriverManager.getDriver().getCurrentUrl());
    }

    public void continueFromDatesToPassengers() {
        debug("[FLOW] continueFromDatesToPassengers: clicking next button");
        datesPage.clickNextToPassengers();
        debug("[FLOW] after next-to-passengers url={}", DriverManager.getDriver().getCurrentUrl());
    }

    public String getTotalDaysText() {
        return datesPage.getTotalDaysText();
    }

    public boolean isPassengersPageOpened() {
        return passengersPage.isOpened();
    }
}
