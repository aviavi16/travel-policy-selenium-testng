package flows;

import pages.ContinentPage;
import pages.DatesPage;
import pages.PassengersPage;
import pages.TravelLandingPage;

import java.time.LocalDate;

public class PurchaseFlow {

    private final TravelLandingPage landingPage = new TravelLandingPage();
    private final ContinentPage continentPage = new ContinentPage();
    private final DatesPage datesPage = new DatesPage();
    private final PassengersPage passengersPage = new PassengersPage();

    public void startPurchase() {
        landingPage.open();
        landingPage.clickFirstTimePurchase();
    }

    public void chooseContinentAndContinue() {
        continentPage.selectRandomContinent();
        continentPage.clickNextToDates();
    }

    public void chooseDatesAndContinue(LocalDate departure, LocalDate ret) {
        datesPage.setDepartureDate(departure);
        datesPage.setReturnDate(ret);
        datesPage.clickNextToPassengers();
    }

    public String getTotalDaysText() {
        return datesPage.getTotalDaysText();
    }

    public boolean isPassengersPageOpened() {
        return passengersPage.isOpened();
    }
}
