package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by QA on 03.06.2017.
 *
 * BasePage class describe base elements on WebPage on Shotspotter site
 *
 */
public class BasePage {

    /**
     * Advertisement variable driver
     */
    protected WebDriver driver;

    /**
     * Common method to get current Page URL
     *
     * @return String with current Page URL
     */
    public String getPageURL(){
        String loginPageUrl = driver.getCurrentUrl();
        return loginPageUrl;
    }

    /**
     * Common method to get current Page title
     *
     * @return String with current Page title
     */
    public String getPageTitle(){
        String loginPageTitle = driver.getTitle();
        return loginPageTitle;
    }

    /**
     * Waits until element is displayed using specific max timeout
     *
     * @param element WebElement to wait for
     * @param timesec max timeout in seconds
     * @return WebElement after expected condition
     */
    protected WebElement waitForElementIsDisplay (WebElement element, int timesec) {
        WebDriverWait wait = new WebDriverWait(driver, timesec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until element is displayed using default 10sec timeout
     *
     * @param element WebElement to wait for
     * @return WebElement after expected condition
     */
    protected WebElement waitForElementIsDisplay(WebElement element) {
        return waitForElementIsDisplay(element,10);
    }

    /**
     * Waits until element is clickable using specific max timeout
     *
     * @param element WebElement to wait for
     * @param timesec max timeout in seconds
     * @return WebElement after expected condition
     */
    protected WebElement waitForElementToClick(WebElement element, int timesec) {
        WebDriverWait wait = new WebDriverWait(driver, timesec);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Common method to check visibility of element
     *
     * @param element WebElement to wait for
     * @param timeout max timeout to wait in seconds
     * @return Boolean statement to visibility element
     */
    public boolean isElementDisplayed(WebElement element, int timeout){
        try {
            waitForElementIsDisplay(element, timeout).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /**
     * Constructor to BasePage class
     *
     * @param driver initialized WebDriver in Base page class
     */
    public  BasePage(WebDriver driver){
        this.driver=driver;
    }

}
