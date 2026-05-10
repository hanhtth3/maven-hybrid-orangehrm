package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.RegisterPageUI;

public class RegisterPO extends BasePage {
    private WebDriver driver;
    public RegisterPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToFirstName(String firstName) {
        waitElementVisible(driver, RegisterPageUI.FIRST_NAME_TXT);
        sendKeyToElement(driver,RegisterPageUI.FIRST_NAME_TXT,firstName);
    }

    public void enterToLastName(String lastName) {
        waitElementVisible(driver, RegisterPageUI.LAST_NAME_TXT);
        sendKeyToElement(driver,RegisterPageUI.LAST_NAME_TXT,lastName);
    }

    public void enterToEmail(String emailAdsress) {
        waitElementVisible(driver, RegisterPageUI.EMAIL_TXT);
        sendKeyToElement(driver,RegisterPageUI.EMAIL_TXT,emailAdsress);
    }

    public void enterToPassword(String pasword) {
        waitElementVisible(driver, RegisterPageUI.PASSWORD_TXT);
        sendKeyToElement(driver,RegisterPageUI.PASSWORD_TXT,pasword);
    }

    public void enterToConfirmPassword(String confirmPassword) {
        waitElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TXT);
        sendKeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TXT,confirmPassword);
    }

    public void clickToRegisterButton() {
        waitElementClickable(driver, RegisterPageUI.REGISTER_BTN);
        clickToElement(driver,RegisterPageUI.REGISTER_BTN);
    }

    public MyAccountPO clickToContinueAlert() {
        acceptToAlert(driver);
        return PageGenerator.getPage(MyAccountPO.class,driver);
    }
}
