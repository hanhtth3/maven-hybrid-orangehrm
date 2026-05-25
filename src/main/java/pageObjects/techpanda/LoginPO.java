package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.LoginPageUI;
import pageUIs.techpanda.MyAccountPageUI;

public class LoginPO extends BasePage {
    private WebDriver driver;
    public LoginPO (WebDriver driver){
        this.driver = driver;
    }

   public RegisterPO clickCreatAnAccountLink() {
        clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BTN);
       return PageGenerator.getPage(RegisterPO.class,driver);
   }
}

