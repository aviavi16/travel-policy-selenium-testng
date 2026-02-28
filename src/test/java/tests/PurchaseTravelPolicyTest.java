package tests;

import flows.PurchaseFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DateUtils;

import java.time.LocalDate;

public class PurchaseTravelPolicyTest extends BaseTest {

    @Test(description = "Purchase flow: continent -> dates (dynamic) -> passengers page")
    public void purchaseFlow_showsTotalDays_andOpensPassengersPage() {
        LocalDate departure = DateUtils.departureDate();
        LocalDate ret = DateUtils.returnDate(departure);

        PurchaseFlow flow = new PurchaseFlow();

        flow.startPurchase();
        flow.chooseContinentAndContinue();

        // Choose dates first, assert total-days on the Dates step,
        // then move to passengers page.
        flow.chooseDates(departure, ret);

        String totalDaysText = flow.getTotalDaysText();
        Assert.assertTrue(totalDaysText != null && !totalDaysText.isBlank(),
                "Total days text should be visible (non-empty) on Dates step.");

        flow.continueFromDatesToPassengers();

        Assert.assertTrue(flow.isPassengersPageOpened(), "Passengers page should be opened.");
    }
}
