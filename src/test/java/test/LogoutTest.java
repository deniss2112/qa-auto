package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

/**
 * Class LogoutTest implement methods to different options to Logout register user from Shotspotter site
 */
public class LogoutTest {
    /**
     * Declaration variable driver
     */
    private WebDriver driver;
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
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("Firefox") String browserName ) throws Exception {
        if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "src/test/resources/geckodriver.exe");
            //Create a new instance to the Firefox driver
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else {
            throw new Exception("Browser is not correct");
        }
        driver.manage().window().maximize(); // open window in full screen
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
    }

    /**
     * Common test method to testing Logout with positiv result
     *
     * @throws InterruptedException
     */
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

    /**
     * Common method that performed after each test method, to close browser window
     */
    @AfterMethod
    public  void closeWindow(){
       driver.quit();
    }
}
