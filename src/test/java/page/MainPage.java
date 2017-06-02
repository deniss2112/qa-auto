package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by QA on 30.05.2017.
 */
public class MainPage {
    private WebDriver driver;
    private WebElement settingsIcon;

    private boolean isElementPresent(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() ==0){
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    private void waiter(By locator, int timesec){
        WebDriverWait wait = new WebDriverWait(driver,timesec);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void initMainPageElement(){
        settingsIcon = driver.findElement(By.className("settings"));
    }

    public void isMainPageLoaded(){
        boolean mainPageUrl = driver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main");
        if(mainPageUrl==true && isElementPresent(By.className("settings"))==true){
        } else {
            throw new IllegalStateException("Main page isn't loaded");
        }

    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        initMainPageElement();
        waiter(By.className("settings"),6);
    }

}
