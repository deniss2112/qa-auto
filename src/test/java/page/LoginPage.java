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
 */
public class LoginPage extends BasePage {

    @FindBy(xpath= "//input[@type='email']")
    private WebElement logMail;

    @FindBy(xpath="//input[@type='password']")
    private WebElement logPass;

    @FindBy(xpath="//*[@class='button' and text()='GO']")
    private WebElement logButtonGo;

    @FindBy(className=("invalid-credentials"))
    private WebElement invalidCredentialsErrorMessage;

    /*public MainPage loginAs(String userEmail, String userPassword){
        logMail.clear();
        logPass.clear();
        logMail.sendKeys(userEmail);
        logPass.sendKeys(userPassword);
        logButtonGo.click();
        return new MainPage(driver);
    }

    public LoginPage loginAsReturnToLogin(String userEmail, String userPassword){
        logMail.clear();
        logPass.clear();
        logMail.sendKeys(userEmail);
        logPass.sendKeys(userPassword);
        logButtonGo.click();
        waiter(invalidCredentialsErrorMessage,6);
        return this;
    }*/

    public <T> T login(String userEmail, String userPassword) throws InterruptedException {
        logMail.clear();
        logPass.clear();
        logMail.sendKeys(userEmail);
        logPass.sendKeys(userPassword);
        logButtonGo.click();
        if (isElementDisplayed(logButtonGo,3)){
             return (T) this ;
        } else return (T) new MainPage(driver);
    }

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

    public boolean isInvalidCredentialMesgDisplayed(){
        return invalidCredentialsErrorMessage.isDisplayed();
    }

    public String getErrorMsgText(){
        return  invalidCredentialsErrorMessage.getText();
    }

    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
        waitForElement(logMail,30);
    }

}

