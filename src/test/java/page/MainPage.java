package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * MainPage class describe Main Page at site Shotspotter
 */
public class MainPage extends BasePage {

    /**
     * Declaration variable from data type WebElement by name: settingsIcon
     */
    @FindBy(className = ("settings"))
    private WebElement settingsIcon;

    /**
     * Declaration variable from data type WebElement by name: settingsMenu
     */
    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingsMenu;

    /**
     * Declaration variable from data type WebElement by name: logoutMenuItem
     */
    @FindBy(xpath = "//li[contains(text(),'Logout')]")
    private WebElement logoutMenuItem;

    @FindBy(xpath = "//filter-menu/div[@class='selected-option']")
    private WebElement incedentsTimeFrameSwitch;

    @FindBy(xpath = "//div[@class='available-options']//*[@class='time-increment' and text()='24']")
    private WebElement timeFrameSwitch24Hours;

    @FindBy(xpath = "//div[@class='available-options']//*[@class='time-increment' and text()='3']")
    private WebElement timeFrameSwitch3Days;

    @FindBy(xpath = "//div[@class='available-options']//*[@class='time-increment' and text()='7']")
    private WebElement timeFrameSwitch7Days;

    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;

    @FindBy(xpath = "//*[text()='List'")
    private WebElement listButton;

    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incedentsCardsList;

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

    public int getResultsCount(){
        wait ?
        return Integer.parseInt(resultsCount.getText().replace("Results", ""));
    }

    public void switchTimeFramePeriod(int timeFramePeriod) {
        incedentsTimeFrameSwitch.click();
        switch (timeFramePeriod){
            case 24:
                timeFrameSwitch24Hours.click();
                wait ?
                break;
            case 3:
                timeFrameSwitch3Days.click();
                break;
            case 7:
                timeFrameSwitch7Days.click();
                break;
        }
    }

    public int getIncedentsCardsCount() {
        listButton.click();
        int incedentsCardsCount = incedentsCardsList.size();
        return incedentsCardsCount;
    }

    /**
     * Constructor to MainPage class
     *
     * @param driver variable of WebDriver which we Declaration in BasePage class
     */
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElementIsDisplay(settingsIcon, 30);
    }



}
