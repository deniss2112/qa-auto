package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.LoginTest;

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

    public LoginPage(WebDriver driver) {
       this.driver = driver;
        driver.manage().window().maximize(); // open window in full screen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
        waiter(By.xpath("//input[@type='email']"),4);
       initLoginPage();
    }





}
