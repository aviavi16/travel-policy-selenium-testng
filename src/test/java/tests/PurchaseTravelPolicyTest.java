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

        Allure.step("Purchase flow", () -> {
            Allure.step("Open and navigate to dates step", flow::openAndReachDatesStep);

            Allure.step("Choose dates and read total days", () -> {
                String totalDaysText = flow.chooseDatesAndReadTotalDays(departure, ret);
                Allure.addAttachment("Total days text", "text/plain", totalDaysText == null ? "" : totalDaysText);
                Assert.assertTrue(totalDaysText != null && !totalDaysText.isBlank(),
                        "Total days text should be visible (non-empty) on Dates step.");
            });

            Allure.step("Continue to passengers and verify page", () ->
                    Assert.assertTrue(flow.continueToPassengersAndVerifyOpened(),
                            "Passengers page should be opened."));
        });
    }
}
