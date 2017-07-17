package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
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

    @FindBy(xpath = "//li[contains(text(),'About')]")
    private WebElement aboutMenuItem;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    private WebElement closeButtonInAboutMenu;

    @FindBy(xpath = "//a[contains(text(), 'terms of service')]")
    private WebElement termsOfServiceLink;

    @FindBy(xpath = "//filter-menu/div[@class='selected-option']")
    private WebElement incedentsTimeFrameSwitch;

    @FindBy(xpath = "//div[@class='selected-option']//*[@class='time-increment' and text()='7']")
    private WebElement timeFrameSwitch7DaysSelected;

    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;

    @FindBy(xpath = "//*[text()='List']")
    private WebElement listButton;

    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incedentsCardsList;

    @FindBy(xpath = "//*[@class='gmnoprint' and img[contains(@src,'assets/markers/small')]]")
    private WebElement markerOnMap;

    private WebElement getTimeFramePeriodOption(int period){
        return driver.findElement(By.xpath(
                String.format("//div[@class='available-options']//*[@class='time-increment' and text()='%d']", period)));
    }
    /*
    The most commonly used ones are:

    %s - insert a string
    %d - insert a signed integer (decimal)
    %f - insert a real number, standard notation

     */

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
        return Integer.parseInt(resultsCount.getText().replace(" RESULTS", ""));
    }

    public void switchTimeFramePeriod(int timeFramePeriod) {
        incedentsTimeFrameSwitch.click();
        waitForElementToClick(getTimeFramePeriodOption(timeFramePeriod),10);
        getTimeFramePeriodOption(timeFramePeriod).click();
        waitResultCounterUpdated(10);
    }

    public void waitResultCounterUpdated(int maxTimeoutSec){
        int initialResultCount = getResultsCount();
            for(int i=0; i<maxTimeoutSec; i++){
                try{
                    Thread.sleep(1000);
                    int currentResultCount = getResultsCount();
                    if(initialResultCount != currentResultCount){
                        break;
                    }
                } catch (InterruptedException ie){}
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


    public void openIncedentsList() {
        listButton.click();
        waitForElementToClick(incedentsCardsList.get(1), 5);
    }

    public List<String> getIncedentsCardsCities() {
        List<String> listCities = new ArrayList<String>();
        for(WebElement incedentCard: incedentsCardsList){
            String cityText =incedentCard.findElement(By.xpath("//div[@class='city S']")).getText();
            listCities.add(cityText);
        }
        return listCities;
    }

    public List<String> getIncedentsCardsStreets() {
        List<String> listStreets =  new ArrayList<String>();
        for(WebElement incedentCard: incedentsCardsList){
            String streetText =incedentCard.findElement(By.xpath("//div[@class='address']")).getText();
            listStreets.add(streetText);
        }
        return listStreets;
    }

    public List<String> getIncedentsCardsTimeStamp() {
        List<String> listTimeStamp =  new ArrayList<String>();
        for(WebElement incedentCard: incedentsCardsList){
            String timeStampText =incedentCard.findElement(By.xpath("//div[@class='cell day']/div[@class='content']")).getText();
            listTimeStamp.add(timeStampText);
        }
        return listTimeStamp;
    }

    public List<String> getSomeFromList(String whatWeNeedGet) {
        List<String> list =  new ArrayList<String>();
        for(WebElement incedentCard: incedentsCardsList){
            String neededText =incedentCard.findElement(By.xpath("//div[@class='cell day']/div[@class='content']")).getText();
            list.add(neededText);
            return (List<String>) driver.findElement(By.xpath(
                    String.format("//div[@class='available-options']//*[@class='time-increment' and text()='%s']", whatWeNeedGet)));
        }
        return list;
    }

    public void goToTermsOfServicePage(){
        settingsIcon.click();
        waitForElementIsDisplay(settingsMenu,5);
        waitForElementToClick(aboutMenuItem,5);
        aboutMenuItem.click();
        waitForElementIsDisplay(termsOfServiceLink, 5);
        termsOfServiceLink.click();
    }

    public void closeAboutWindow(){
        waitForElementToClick(closeButtonInAboutMenu, 5);
        closeButtonInAboutMenu.click();
    }
}
