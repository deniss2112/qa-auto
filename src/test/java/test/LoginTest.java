package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import page.BasePage;
import page.LoginPage;
import page.MainPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by QA on 20.05.2017.
 *
 * Class LoginTest implement methods to different options to Login to Shotspotter site
 */
public class LoginTest {

    /**
     * Advertisement variable driver
     */
    private WebDriver driver;
    /**
     * Create new Logger to use it in test methods
     */
    private final Logger log = Logger.getLogger(LoginTest.class);
    /**
     * String with registered user eMail
     */
    public String userEmail ="sst.tau@gmail.com";
    /**
     * String with registered user Password
     */
    public String userPassword ="P@ssword123";

    /**
     * Common method that performed before each test method, to open browser window,
     * setup it by window size and go to site URL
     */
    @BeforeMethod// Run this method before the first test method in the current class is invoked
    public void setUp(){
        //Create a new instance to the Firefox driver
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // open window in full screen
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
    }

    /**
     * Common test method to testing Login with positiv result
     *
     * @throws InterruptedException
     */
    @Test
    public void testLoninPositiv()throws InterruptedException{
        log.info("start testLoninPositiv 01");

        //String userEmail ="denvert1@shotspotter.net";
        // String userPassword ="Test123!";

        //Open browser, open login page
        LoginPage loginPage = new LoginPage(driver);

        //Check title and URL on login page
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Wrong URL on Login page");
        Assert.assertEquals(loginPage.getPageURL(),  "https://alerts.shotspotter.biz/", "Wrong Title on Login page");
        //Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");

        //loginPage.isLoginPageLoaded();
        MainPage mainPage = loginPage.login(userEmail,userPassword);

        //Check URL and settings icon on main page
        Assert.assertTrue(mainPage.isMainPageLoaded(), "Main page isn't loaded");
        log.info("no errors in test 01");
    }

    /**
     * Common test method to testing Login with negativ result
     *
     * @throws InterruptedException
     */
    @Test
    public  void  testLoginInvalidPass() throws InterruptedException{
        log.info("start testLoginInvalidPass 02");

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");

        loginPage = loginPage.login(userEmail, "123");
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");
        //Verify that text about invalid password displayed
        Assert.assertTrue(loginPage.isInvalidCredentialMesgDisplayed(), "No message about login failed");

        String textAboutInvalidPass = loginPage.getErrorMsgText();
        //Verify that text about invalid password correct
        Assert.assertEquals(textAboutInvalidPass,"The provided credentials are not correct.");

        log.info("no errors in test 02");
    }

    /**
     * Common method that performed after each test method, to close browser window
     */
    @AfterMethod // Run this method after all the test methods in the current class have been run
    public  void closeWindow(){
        //Close all browser window
        driver.quit();
    }
}
