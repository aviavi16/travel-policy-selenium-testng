package utils;

import io.qameta.allure.Allure;
import pages.SessionTimeoutPage;

import java.io.ByteArrayInputStream;

public final class Guards {
    private Guards() {}

    public static void aroundAction(String actionName, Runnable action) {
        failIfSessionTimedOut();
        action.run();
        failIfSessionTimedOut(actionName);
    }

    public static void failIfSessionTimedOut() {
        failIfSessionTimedOut("unknown");
    }

    public static void failIfSessionTimedOut(String actionName) {
        SessionTimeoutPage timeoutPage = new SessionTimeoutPage();
        if (timeoutPage.isVisible()) {
            String msg = timeoutPage.getMessage();
            Allure.addAttachment("Session timeout message", msg == null ? "" : msg);
            Allure.addAttachment("Session timeout screenshot", "image/png",
                    new ByteArrayInputStream(ScreenshotUtil.png()), ".png");
            throw new AssertionError("Session timed out after action '" + actionName
                    + "' / user must re-login. Message: " + msg);
        }
    }
}
