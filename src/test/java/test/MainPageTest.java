package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;
import page.TermsOfServicePage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;


public class MainPageTest {
    private WebDriver driver;
    private  MainPage mainPage;
    private TermsOfServicePage termsOfService;
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
        //driver.manage().window().maximize(); // open window in full screen
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

    @Test
    public void testValidateIncidentCardFields(){

        String expextedCity = "Denver";

        mainPage.switchTimeFramePeriod(3);

        mainPage.openIncedentsList();
        List<String> listCities  = mainPage.getSomeFromList("City");
        List<String> listStreets  = mainPage.getSomeFromList("Street");
        List<String> listTimeStamp  = mainPage.getSomeFromList("TimeStamp");

        for (String elementCity: listCities) {
            //Assert.assertEquals(elementCity, expextedCity, "City is not Denver");
        }

        for (String elementStreet: listStreets) {
            Assert.assertNotEquals(elementStreet, "", "Street is empty");
        }

        for (String elementTimeStamp: listTimeStamp) {
            Assert.assertNotEquals(elementTimeStamp, "", "TimeStamp is empty");
        }

        //statment to check that all elements in list same
        boolean isSame = Collections.frequency(listTimeStamp, "1") == listTimeStamp.size();

        Assert.assertFalse(isSame,"One of Time Stamp same to another");

    }

    @Test
    public void testTermsOfServicePageOpen(){
        String parentWindow = driver.getWindowHandle();

        mainPage.goToTermsOfServicePage();
         //Assert.assertTrue(termsOfService.isTermsOfServiceLoaded(), "Terms Of Service page isn't loaded");
        driver.switchTo().window(parentWindow);
        mainPage.closeAboutWindow();
        Assert.assertTrue(mainPage.isMainPageLoaded(), "Main page isn't loaded");

    }

    @Test
    public void testNewAllertNotification() throws IOException {
        String jsonBody = generateStringFromResource("/test/resources/notificationRequestBody.json");

        given().
                contentType("application/json").
                body(jsonBody).
                when().
                post("https:alerts.shotspotter.biz/api/incidents/v2/").
                then().
                statusCode(200).
                body(containsString("true"));

        String expextedCity = "AgentCity";

        mainPage.switchTimeFramePeriod(24);

        mainPage.openIncedentsList();
        List<String> listCities  = mainPage.getSomeFromList("City");

        for (String elementCity: listCities) {
            Assert.assertTrue(listCities.contains(expextedCity), "card with Expected City isn't found");

        }


    }
    public String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }

    @AfterClass
    public  void closeWindow(){
        driver.quit();
    }

}
