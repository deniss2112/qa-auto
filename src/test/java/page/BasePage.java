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
 */
public class BasePage {

    /**
     *
     */
    protected WebDriver driver;

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

    protected WebElement waitForElementIsDisplay(WebElement element) {
        return waitForElementIsDisplay(element,10);
    }

    /**
     * Waits until element is clickable using specific max timeout
     *
     * @param element WebElement to wait for
     * @param timesec max timeout in seconds
     */
    protected void waitForElementToClick(WebElement element, int timesec) {
        WebDriverWait wait = new WebDriverWait(driver, timesec);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean isElementPresent(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() ==0){
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    public boolean isElementDisplayed(WebElement element, int timeout){
        try {
            waitForElementIsDisplay(element, timeout).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public  BasePage(WebDriver driver){
        this.driver=driver;
    }

}
