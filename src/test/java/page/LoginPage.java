package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.LoginTest;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by QA on 27.05.2017.
 */
public class LoginPage {
    private WebDriver driver;
    private WebElement logMail;
    private WebElement logPass;
    private WebElement logButtonGo;

    private void waiter(By locator, int timesec){
        WebDriverWait wait = new WebDriverWait(driver,timesec);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void initLoginPage(){
        logMail = driver.findElement(By.xpath("//input[@type='email']"));
        logPass = driver.findElement(By.xpath("//input[@type='password']"));
        logButtonGo = driver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
    }

    public MainPage loginAs(String userEmail, String userPassword){
        logMail.clear();
        logPass.clear();
        logMail.sendKeys(userEmail);
        logPass.sendKeys(userPassword);
        logButtonGo.click();
        return new MainPage(driver);
    }

    public void loginAsInvalidPass(String userEmail, String userPassword){
        logMail.clear();
        logPass.clear();
        logMail.sendKeys(userEmail);
        logPass.sendKeys(userPassword);
        logButtonGo.click();
        waiter(By.xpath("//*[@class='invalid-credentials' and text()='The provided credentials are not correct.']"),6);
    }

    public void isLoginPageLoaded(){
        boolean loginPageUrl = driver.getCurrentUrl().contains("https://alerts.shotspotter.biz/");
        boolean loginPageTitle = driver.getTitle().contains("Shotspotter - Login");
        if(loginPageUrl==true && loginPageTitle==true){
        } else {
            throw new IllegalStateException("Login page isn't loaded");
        }
    }

    public void isLoginFailed(){
        boolean textAboutInvalidPass = driver.getPageSource().contains("The provided credentials are not correct.");
        if(textAboutInvalidPass==true){
        } else {
            throw new IllegalStateException("Text about login fail absent");
        }
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize(); // open window in full screen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
        waiter(By.xpath("//input[@type='email']"),4);
        initLoginPage();
    }

}
