import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 20.05.2017.
 */
public class LoginTest {

    private WebDriver driver;

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

    @BeforeClass// Run this method before the first test method in the current class is invoked
    public void setUp(){
        //Create a new instance to the Firefox driver
        driver = new FirefoxDriver();
    }

    @Test
    public void testLoninToShotspotter()throws InterruptedException{
        driver.manage().window().maximize(); // open window in full screen
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page
        /* driver.navigate().to("https://alerts.shotspotter.biz/"); - more long text to open web page */

        sleep(4000);

        // search and create element
        WebElement logMail = driver.findElement(By.xpath("//input[@type='email']"));
        logMail.clear();
        logMail.sendKeys("denvert1@shotspotter.net");
        // search and create element
        WebElement logPass = driver.findElement(By.xpath("//input[@type='password']"));
        logPass.clear();
        logPass.sendKeys("Test123!");
        // search and create element
        WebElement logButtonGo = driver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
        logButtonGo.click();

        sleep(6000);

        // search and create element
        String mainPage = driver.getCurrentUrl();
        //Verify main page URL
        Assert.assertEquals(mainPage, "https://alerts.shotspotter.biz/main");
        Assert.assertTrue(isElementPresent(By.className("settings")), "Element wasn't shown");

        closeWindow();
    }

    @Test
    public  void  testToLoginInvalidPass() throws InterruptedException{
        setUp();
        driver.manage().window().maximize(); // open window in full screen
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alerts.shotspotter.biz/");// open needed Web page

        sleep(4000);

        WebElement logMail = driver.findElement(By.xpath("//input[@type='email']"));
        logMail.sendKeys("denvert1@shotspotter.net");
        WebElement logPass = driver.findElement(By.xpath("//input[@type='password']"));
        logPass.sendKeys("Test123");
        WebElement logButtonGo = driver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
        logButtonGo.click();

        sleep(6000);

        // search text about invalid password, and return true if it is
        boolean textAboutInvalidPass = driver.getPageSource().contains("The provided credentials are not correct.");
        //Verify that text about invalid password present
        Assert.assertTrue(textAboutInvalidPass, "Element wasn't shown");
        //Verify that text about invalid password present
        //Assert.assertTrue(isElementPresent(By.xpath("//*[@class='invalid-credentials' and text()='The provided credentials are not correct.']")),"Element wasn't shown");

    }

    @AfterClass // Run this method after all the test methods in the current class have been run
    public  void closeWindow(){
        //Close all browser window
        driver.quit();
    }
}
