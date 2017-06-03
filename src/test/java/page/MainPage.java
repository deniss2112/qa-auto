package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by QA on 30.05.2017.
 */
public class MainPage extends BasePage {


    @FindBy(className=("settings"))
    @CacheLookup
    private WebElement settingsIcon;

    public boolean isMainPageLoaded(){
        boolean mainPageUrl = driver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main");
        if(mainPageUrl==true && isElementPresent(By.className("settings"))==true){
            return true;
        } else {
            return false;
        }
    }

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waiter(settingsIcon,6);
    }

}
