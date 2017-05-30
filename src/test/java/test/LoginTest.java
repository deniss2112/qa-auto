package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import page.LoginPage;
import page.MainPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 20.05.2017.
 */
public class LoginTest {

    private static WebDriver driver;
    private final static Logger log = Logger.getLogger(LoginTest.class);

    //Check if element present and displayed by locator
    public boolean isElementPresent(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() ==0){
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    //method to wait some element
    public void waiter(By locator, int timesec){
        WebDriverWait wait = new WebDriverWait(driver,timesec);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @BeforeMethod// Run this method before the first test method in the current class is invoked
    public void setUp(){
        //Create a new instance to the Firefox driver
        driver = new FirefoxDriver();

    }

    @Test
    public void testLoninPositiv()throws InterruptedException{
        log.info("start test 01");

        String userEmail ="denvert1@shotspotter.net";
        String userPassword ="Test123!";

        //Open browser, open login page
        LoginPage loginPage = new LoginPage(driver);
        //Check title and URL on login page
        Assert.assertEquals(driver.getTitle(), "Shotspotter - Login", "Title is not valid");
        Assert.assertEquals(driver.getCurrentUrl(), "https://alerts.shotspotter.biz/", "Wrong URL on login page");

        MainPage mainPage = loginPage.loginAs(userEmail,userPassword);

        boolean mainPageUrl = driver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main");
        //Verify main page URL
        Assert.assertTrue(mainPageUrl, "Main page URL wrong");
        Assert.assertTrue(mainPage.isMainPageLoaded(), "Element wasn't shown");
        log.info("end test 01");
    }

    @Test
    public  void  testToLoginInvalidPass() throws InterruptedException{
        log.info("start test 02");

        String userEmail ="denvert1@shotspotter.net";
        String userPassword ="Test123";

        waiter(By.xpath("//input[@type='email']"),4);

        Assert.assertEquals(driver.getTitle(), "Shotspotter - Login", "Title is not valid");
        Assert.assertEquals(driver.getCurrentUrl(), "https://alerts.shotspotter.biz/", "Wrong URL on login page");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(userEmail,userPassword);

        waiter(By.xpath("//*[@class='invalid-credentials' and text()='The provided credentials are not correct.']"),6);

        // search text about invalid password, and return true if it is
        boolean textAboutInvalidPass = driver.getPageSource().contains("The provided credentials are not correct.");
        //Verify that text about invalid password present
        Assert.assertTrue(textAboutInvalidPass, "Element wasn't shown");
        //Verify that text about invalid password present
        //Assert.assertTrue(isElementPresent(By.xpath("//*[@class='invalid-credentials' and text()='The provided credentials are not correct.']")),"Element wasn't shown");
        log.info("end test 02");
    }

    @AfterMethod // Run this method after all the test methods in the current class have been run
    public  void closeWindow(){
        //Close all browser window
        driver.quit();
    }
}
