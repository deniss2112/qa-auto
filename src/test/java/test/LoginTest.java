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

    private WebDriver driver;
    private final Logger log = Logger.getLogger(LoginTest.class);

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

    @BeforeMethod// Run this method before the first test method in the current class is invoked
    public void setUp(){
        //Create a new instance to the Firefox driver
        driver = new FirefoxDriver();

    }

    @Test
    public void testLoninPositiv()throws InterruptedException{
        log.info("start testLoninPositiv 01");

        String userEmail ="denvert1@shotspotter.net";
        String userPassword ="Test123!";

        //Open browser, open login page
        LoginPage loginPage = new LoginPage(driver);

        //Check title and URL on login page
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");
        //loginPage.isLoginPageLoaded();
        MainPage mainPage = loginPage.loginAs(userEmail,userPassword);

        //Check URL and settings icon on main page
        Assert.assertTrue(mainPage.isMainPageLoaded(), "Main page isn't loaded");
        log.info("end test 01");
    }

    @Test
    public  void  testLoginInvalidPass() throws InterruptedException{
        log.info("start testLoginInvalidPass 02");

        String userEmail ="denvert1@shotspotter.net";
        String userPassword ="Test123";

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");
        loginPage.loginAsInvalidPass(userEmail,userPassword);

        //Verify that text about invalid password present
        Assert.assertTrue(loginPage.isLoginFailed(), "Text about invalid password absent");

        log.info("end testLoginInvalidPass 02");
    }

    @AfterMethod // Run this method after all the test methods in the current class have been run
    public  void closeWindow(){
        //Close all browser window
        driver.quit();
    }
}
