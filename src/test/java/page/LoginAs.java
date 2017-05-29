package page;

import org.openqa.selenium.WebDriver;

/**
 * Created by QA on 27.05.2017.
 */
public class LoginAs {

    private LoginPage loginPage;

    public LoginAs(WebDriver driver) {
        loginPage = new LoginPage(driver);
        clear();
    }

    private void clear(){
        loginPage.getLogMail().clear();
        loginPage.getLogPass().clear();
    }

    public void setEmail(String email) {
        loginPage.getLogMail().sendKeys(email);
    }

    public void setPassword(String password) {
        loginPage.getLogPass().sendKeys(password);
        loginPage.getLogButtonGo().click();
    }
}
