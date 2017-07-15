package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by QA on 15.07.2017.
 */
public class TermsOfServicePage extends  BasePage {

    @FindBy(id = "main-content")
    private WebElement contentOfTermsOfServicePage;

    public boolean isTermsOfServiceLoaded() {
        boolean termsOfServicePageUrl = driver.getCurrentUrl().contains("http://www.shotspotter.com/apps/tos");
        boolean termsOfServicePageTitle = driver.getTitle().contains("Apps-TOS");
        if (termsOfServicePageTitle && termsOfServicePageUrl){
            return true;
        } else return false;
    }

    public TermsOfServicePage(WebDriver driver) {
        super(driver);
        waitForElementIsDisplay(contentOfTermsOfServicePage);
    }

}
