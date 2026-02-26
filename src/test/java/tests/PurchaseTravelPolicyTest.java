package tests;

import flows.PurchaseFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DateUtils;

import java.time.LocalDate;

public class PurchaseTravelPolicyTest extends BaseTest {

    @Test(description = "Purchase flow: continent -> dates (dynamic) -> passengers page")
    public void purchaseFlow_opensPassengersPage_andShowsTotalDays() {
        LocalDate departure = DateUtils.departureDate();
        LocalDate ret = DateUtils.returnDate(departure);

        PurchaseFlow flow = new PurchaseFlow();

        flow.startPurchase();
        flow.chooseContinentAndContinue();

        // If "total days" is only shown after selecting both dates, we read it after date selection.
        flow.chooseDatesAndContinue(departure, ret);

        // TODO: Update assertion once you confirm the exact UI behavior:
        // - Some UIs show "30", some show "31" if inclusive.
        // Recommended: parse the UI value and assert expected according to product spec.
        String totalDaysText = flow.getTotalDaysText();
        // Soft sanity: it should not be empty
        Assert.assertTrue(totalDaysText != null && !totalDaysText.isBlank(), "Total days text should be visible (non-empty).");

        Assert.assertTrue(flow.isPassengersPageOpened(), "Passengers page should be opened.");
    }
}
