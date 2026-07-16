package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import core.BasePage;
import pageUIs.BasePageUI;
import pageUIs.saucelab.LoginPageUI;

public class LoginPO extends BasePage {

private WebDriver driver;
    public LoginPO (WebDriver driver){this.driver = driver;    }

    public ProductsPO loginSauce(String userName , String password) {
        waitElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(ProductsPO.class,driver);
    }
}
