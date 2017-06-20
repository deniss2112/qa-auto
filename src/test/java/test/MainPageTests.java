package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;

public class MainPageTests {
    private WebDriver driver;
    public String userEmail ="denvert1@shotspotter.net";
    public String userPassword ="Test123!";

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // open window in full screen
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
    }

    @Test
    public void testIncedentsPeriodSwitch()throws InterruptedException{

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.login(userEmail,userPassword);

        mainPage.switchTimeFramePeriod(7);
        int resultsCount = mainPage.getResultsCount();
        int incedentsCardsCount = mainPage.getIncedentsCardsCount();

        Assert.assertEquals(resultsCount, incedentsCardsCount, "Results count doesn't much Incedents cards count");

    }

    @AfterMethod
    public  void closeWindow(){
        driver.quit();
    }

}
