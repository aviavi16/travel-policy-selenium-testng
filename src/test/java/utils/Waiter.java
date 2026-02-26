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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        WebElement element = clickable(locator);
        scrollIntoView(element);
        element.click();
    }

    public void type(By locator, String text) {
        WebElement element = visible(locator);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
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
}
