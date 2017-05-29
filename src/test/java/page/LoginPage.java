package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by QA on 27.05.2017.
 */
public class LoginPage {
    private WebElement logMail;
    private WebElement logPass;
    private WebElement logButtonGo;

    public LoginPage(WebDriver driver) {
       logMail = driver.findElement(By.xpath("//input[@type='email']"));
       logPass = driver.findElement(By.xpath("//input[@type='password']"));
       logButtonGo = driver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
    }

    public WebElement getLogMail() {
        return logMail;
    }

    public WebElement getLogPass() {
        return logPass;
    }

    public WebElement getLogButtonGo() {
        return logButtonGo;
    }
}
