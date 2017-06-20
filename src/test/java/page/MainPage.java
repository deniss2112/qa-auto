package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by QA on 30.05.2017.
 * MainPage class describe Main Page at site Shotspotter
 */
public class MainPage extends BasePage {

    /**
     * Advertisement variable from data type WebElement by name: settingsIcon
     */
    @FindBy(className = ("settings"))
    private WebElement settingsIcon;

    /**
     * Advertisement variable from data type WebElement by name: settingsMenu
     */
    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingsMenu;

    /**
     * Advertisement variable from data type WebElement by name: logoutMenuItem
     */
    @FindBy(xpath = "//li[contains(text(),'Logout')]")
    private WebElement logoutMenuItem;

    /**
     * Common method to verify that Main Page loaded
     *
     * @return Boolean statement about verify result
     */
    public boolean isMainPageLoaded() {
        boolean mainPageUrl = driver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main");
        if (mainPageUrl == true && isElementDisplayed(settingsIcon, 10) == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Common method to Logout user
     *
     * @return LoginPage to which we must return
     */
    public LoginPage logOut() {
        settingsIcon.click();
        waitForElementIsDisplay(settingsMenu,5);
        waitForElementToClick(logoutMenuItem,5);
        logoutMenuItem.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Constructor to MainPage class
     *
     * @param driver variable of WebDriver which we Advertisement in BasePage class
     */
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElementIsDisplay(settingsIcon, 30);
    }

}
