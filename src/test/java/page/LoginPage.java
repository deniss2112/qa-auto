package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.LoginTest;

/**
 * Created by QA on 27.05.2017.
 */
public class LoginPage {
    public static WebDriver driver;

    // search and create element
    public static WebElement logMail = driver.findElement(By.xpath("//input[@type='email']"));
    public static WebElement logPass = driver.findElement(By.xpath("//input[@type='password']"));
    public static WebElement logButtonGo = driver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
}
