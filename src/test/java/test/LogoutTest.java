package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by HTML on 10.06.2017.
 */
public class LogoutTest {
    private WebDriver driver;
    private final Logger log = Logger.getLogger(LoginTest.class);
    public String userEmail ="sst.tau@gmail.com";
    public String userPassword ="P@ssword123";

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // open window in full screen
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
    }

    @Test
    public void testLogout()throws InterruptedException{

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Wrong URL on Login page");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong Title on Login page");

        MainPage mainPage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(mainPage.isMainPageLoaded(), "Main page isn't loaded");
        loginPage=mainPage.logOut();
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Logout Failed");

    }

    @AfterMethod
    public  void closeWindow(){
       driver.quit();
    }
}
