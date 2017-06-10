package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by QA on 03.06.2017.
 */
public class BasePage {
    protected WebDriver driver;

    public String getPageURL(){
        String loginPageUrl = driver.getCurrentUrl();
        return loginPageUrl;
    }

    public String getPageTitle(){
        String loginPageTitle = driver.getTitle();
        return loginPageTitle;
    }

    protected WebElement waitForElement (WebElement element, int timesec) {
        WebDriverWait wait = new WebDriverWait(driver, timesec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElement(WebElement element) {
        return waitForElement(element,10);
    }

    protected WebElement waitForElementToClick (WebElement element, int timesec) {
        WebDriverWait wait = new WebDriverWait(driver, timesec);
        return wait.until(ExpectedConditions.element));
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
            waitForElement(element, timeout).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public  BasePage(WebDriver driver){
        this.driver=driver;
    }

}
