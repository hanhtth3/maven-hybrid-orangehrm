package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(how = How.XPATH, using =  "//input[@name ='username']")
    private WebElement usernameTextbox;

    @CacheLookup
    @FindBy(name = "password")
    private WebElement passwordTextbox;

    @CacheLookup
    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    public  LoginPageObject (WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToUserNameTextbox(String username) {
        waitElementVisible(driver,usernameTextbox);
        sendKeyToElement(usernameTextbox,username);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver,passwordTextbox);
        sendKeyToElement(passwordTextbox,password);
    }

    public void clickToLoginButton() {
        waitElementClickable(driver, loginButton);
        clickToElement(loginButton);
    }
}
