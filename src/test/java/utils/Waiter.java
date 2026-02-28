package utils;

import config.TestConfig;
import driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waiter {
    private final WebDriverWait wait;

    public Waiter() {
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TestConfig.timeoutSeconds()));
    }

    public WebElement visible(By locator) {
        Guards.failIfSessionTimedOut("before waiting for visibility");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement clickable(By locator) {
        Guards.failIfSessionTimedOut("before waiting for clickability");
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        Guards.failIfSessionTimedOut("before click");

        wait.until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                scrollIntoView(element);

                if (!element.isDisplayed() || !element.isEnabled()) {
                    return false;
                }

                if (!isTopMostAtCenter(element)) {
                    return false;
                }

                element.click();
                return true;
            } catch (NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
                return false;
            }
        });

        Guards.failIfSessionTimedOut("after click");
    }

    public void type(By locator, String text) {
        Guards.failIfSessionTimedOut("before type");
        WebElement element = visible(locator);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
        Guards.failIfSessionTimedOut("after type");
    }

    public boolean isPresent(By locator) {
        try {
            DriverManager.getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
        } catch (Exception ignored) {
        }
    }

    private boolean isTopMostAtCenter(WebElement element) {
        try {
            Object result = ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                    "const el = arguments[0];"
                            + "const r = el.getBoundingClientRect();"
                            + "if (r.width <= 0 || r.height <= 0) return false;"
                            + "const x = Math.floor(r.left + r.width / 2);"
                            + "const y = Math.floor(r.top + r.height / 2);"
                            + "const vw = window.innerWidth || document.documentElement.clientWidth;"
                            + "const vh = window.innerHeight || document.documentElement.clientHeight;"
                            + "if (x < 0 || y < 0 || x >= vw || y >= vh) return false;"
                            + "const top = document.elementFromPoint(x, y);"
                            + "return !!top && (top === el || el.contains(top));",
                    element
            );
            return Boolean.TRUE.equals(result);
        } catch (Exception ex) {
            return false;
        }
    }
}
