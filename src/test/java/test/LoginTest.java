package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import page.LoginPage;
import page.MainPage;

/**
 * Class LoginTest implement methods to different options to Login to Shotspotter site
 */
public class LoginTest {

    /**
     * Declaration variable driver
     */
    private WebDriver driver;
    /**
     * Create new Logger to use it in test methods
     */
    private final Logger log = Logger.getLogger(LoginTest.class);
    /**
     * String with registered user eMail
     */
    public String userEmail ="sst.tau@gmail.com";//"denvert1@shotspotter.net"
    /**
     * String with registered user Password
     */
    public String userPassword ="P@ssword123";//"Test123!"

    /**
     * Common method that performed before each test method, to open browser window,
     * setup it by window size and go to site URL
     */
    @BeforeMethod// Run this method before the first test method in the current class is invoked
    @Parameters("browser")
    public void setUp(@Optional("Firefox") String browserName ) throws Exception {
        if (browserName.equalsIgnoreCase("Firefox")) {
            //Create a new instance to the Firefox driver
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:/Users/QA/Downloads/chromedriver_win32/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else {
            throw new Exception("Browser is not correct");
        }
        driver.manage().window().maximize(); // open window in full screen
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
    }

    /**
     * Common test method to testing Login with positiv result
     *
     */
    @Test
    public void testLoninPositiv(){
        log.info("start testLoninPositiv 01");

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
     */

    //все негатив тесты с помошью дата провайдера  (в дата провайдере 3 значения) емаил пасворд ерор месседж

    @DataProvider
    public static Object[][] loginData(){
        return new Object[][] {
                {"","","The provided credentials are not correct."},
                {"","P@ssword123","The provided credentials are not correct."},
                {"sst.tau@gmail.com","","The provided credentials are not correct."},
                {"sst.tau@gmail.com","P@ssword12","The provided credentials are not correct."},
                {"sst.tau@gmail.comm","P@ssword1233","The provided credentials are not correct."},
                {"P@ssword123","sst.tau@gmail.com","The provided credentials are not correct."},
                {" sst.tau@gmail.com","P@ssword123","The provided credentials are not correct."},
                {"sst.tau@gmail.com ","P@ssword123","The provided credentials are not correct."},
                {"sst.tau@gmail.com"," P@ssword123","The provided credentials are not correct."},
                {"sst.tau@gmail.com","P@ssword123 ","The provided credentials are not correct."},
                {"Sst.tau@gmail.com","P@ssword123","The provided credentials are not correct."},
                {"sst.tau@gmail.com","P@Ssword123","The provided credentials are not correct."},
                {"                 ","P@ssword123","The provided credentials are not correct."},
                {"sst.tau@gmail.com","           ","The provided credentials are not correct."},

        };
    }

    @Test(dataProvider = "loginData" )
    public  void  testLoginInvalidPass(String userEmail, String userPassword, String errorMessage){
        log.info("start testLoginInvalidPass 02");

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");

        loginPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page isn't loaded");
        //Verify that text about invalid password displayed
        Assert.assertTrue(loginPage.isInvalidCredentialMesgDisplayed(), "No message about login failed");

        String textAboutInvalidPass = loginPage.getErrorMsgText();
        //Verify that text about invalid password correct
        Assert.assertEquals(textAboutInvalidPass,errorMessage);

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
