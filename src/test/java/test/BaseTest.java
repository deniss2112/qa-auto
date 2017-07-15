package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver;


    /**
     * Common method that performed before each test method, to open browser window,
     * setup it by window size and go to site URL
     */
    // Run this method before the first test method in the current class is invoked
    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // open window in full screen
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
    }

    /**
     * Common method that performed after each test method, to close browser window
     */
    // Run this method after all the test methods in the current class have been run
    @AfterMethod
    public  void closeWindow(){
        driver.quit();
    }
}
