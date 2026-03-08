package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.user.UserHomePageUI;
import pageUIs.openCart.user.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPO clickToContinueButton() {
        waitElementClickable(driver, UserLoginPageUI.NEW_CUSOMER_CONTINUE_BUTTON);
        clickToElement(driver,UserLoginPageUI.NEW_CUSOMER_CONTINUE_BUTTON);
        return PageGenerator.getPage(UserRegisterPO.class,driver);
    }

    public void enterToEmailAdressTextbox(String emailAddress) {
        waitElementVisible(driver,UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver,UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX,emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver,UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,UserLoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public UserMyAccountPO clickLoginToButton() {
        waitElementClickable(driver,UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(UserMyAccountPO.class,driver);
    }
}
