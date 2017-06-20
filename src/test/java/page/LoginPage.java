package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


/**
 * Created by QA on 27.05.2017.
 *
 * LoginPage class describe Login Page at site Shotspotter
 *
 */
public class LoginPage extends BasePage {

    /**
     * Advertisement variable from data type WebElement by name: logMail
     */
    @FindBy(xpath= "//input[@type='email']")
    private WebElement logMail;

    /**
     * Advertisement variable from data type WebElement by name: logPass
     */
    @FindBy(xpath="//input[@type='password']")
    private WebElement logPass;

    /**
     * Advertisement variable from data type WebElement by name: logButtonGo
     */
    @FindBy(xpath="//*[@class='button' and text()='GO']")
    private WebElement logButtonGo;

    /**
     * Advertisement variable from data type WebElement by name: invalidCredentialsErrorMessage
     */
    @FindBy(className=("invalid-credentials"))
    private WebElement invalidCredentialsErrorMessage;

    /**
     * Common method to try to login to site Shotspotter as user
     *
     * @param userEmail String with User email with which we want to login to
     * @param userPassword String with User pass with which we want to login to
     * @param <T> type of page which must be defined later
     * @return Page of the site we are on
     */
    public <T> T login(String userEmail, String userPassword){
        logMail.clear();
        logPass.clear();
        logMail.sendKeys(userEmail);
        logPass.sendKeys(userPassword);
        logButtonGo.click();
        if (isElementDisplayed(logButtonGo,3)){
             return (T) this ;
        } else return (T) new MainPage(driver);
    }

    /**
     * Common method to verify that Login Page loaded
     *
     * @return Boolean statement about verify result
     */
    public boolean isLoginPageLoaded(){
        boolean loginPageUrl = driver.getCurrentUrl().contains("https://alerts.shotspotter.biz/");
        boolean loginPageTitle = driver.getTitle().contains("Shotspotter - Login");
        boolean logingPageEmail = driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
        if(loginPageUrl==true && loginPageTitle==true && logingPageEmail==true){
                return true;
            } else {
                return false;
            }
        }

    /**
     * Common method to verify that Invalid Credential Message Displayed on page
     *
     * @return Boolean statement about verify result
     */
    public boolean isInvalidCredentialMesgDisplayed(){
        return invalidCredentialsErrorMessage.isDisplayed();
    }

    /**
     * Common method to get text from Error massage
     *
     * @return String with text about Error
     */
    public String getErrorMsgText(){
        return  invalidCredentialsErrorMessage.getText();
    }

    /**
     * Constructor to LoginPage class
     *
     * @param driver variable of WebDriver which we Advertisement in BasePage class
     */
    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
        waitForElementIsDisplay(logMail,30);
    }

}

