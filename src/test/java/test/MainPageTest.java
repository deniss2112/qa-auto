package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;


public class MainPageTest {
    private WebDriver driver;
    private  MainPage mainPage;
    public String userEmail ="denvert1@shotspotter.net";
    public String userPassword ="Test123!";

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("Firefox") String browserName) throws Exception {
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
        LoginPage loginPage = new LoginPage(driver);
        mainPage = loginPage.login(userEmail,userPassword);
    }

    @Test
    public void testIncedentsPeriodSwitch(){

        int [] timeFrameOptions = {24,3,7};

        for(int timeFrameOption : timeFrameOptions){

            mainPage.switchTimeFramePeriod(timeFrameOption);
            int resultsCount =mainPage.getResultsCount();
            int incedentsCardsCount = mainPage.getIncedentsCardsCount();

            System.out.println("period = "+timeFrameOption);;
            System.out.println("resultsCount = "+resultsCount);
            System.out.println("incedentsCardsCount = "+incedentsCardsCount);

            Assert.assertEquals(resultsCount, incedentsCardsCount, "Results count doesn't much Incedents cards count");
        }
    }

    @DataProvider
    public static Object[][] timeFrameOptions(){
        return new Object[][] {{24},{3},{7}};
    }

    @Test(dataProvider = "timeFrameOptions" )
    public void testIncedentsPeriodSwitchByDataProvider( int timeFrameOption){

        mainPage.switchTimeFramePeriod(timeFrameOption);
        int resultsCount =mainPage.getResultsCount();
        int incedentsCardsCount = mainPage.getIncedentsCardsCount();

        System.out.println("period = "+timeFrameOption);;
        System.out.println("resultsCount = "+resultsCount);
        System.out.println("incedentsCardsCount = "+incedentsCardsCount);

        Assert.assertEquals(resultsCount, incedentsCardsCount, "Results count doesn't much Incedents cards count");

    }

    @AfterClass
    public  void closeWindow(){
        driver.quit();
    }

}
