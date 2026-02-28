package tests;

import flows.PurchaseFlow;
import io.qameta.allure.Allure;
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

        flow.openAndReachDatesStep();

        String totalDaysText = flow.chooseDatesAndReadTotalDays(departure, ret);
        Allure.addAttachment("Total days text", "text/plain", totalDaysText == null ? "" : totalDaysText);
        Assert.assertTrue(totalDaysText != null && !totalDaysText.isBlank(),
                "Total days text should be visible (non-empty) on Dates step.");

        Assert.assertTrue(flow.continueToPassengersAndVerifyOpened(), "Passengers page should be opened.");
    }
}
