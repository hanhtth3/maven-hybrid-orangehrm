package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.user.UserHomePageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public UserLoginPO clickToMyaccunt() {
        scrollToElementOnTop(driver,UserHomePageUI.FOOTER_MY_ACCOUNT_LINK);
        waitElementClickable(driver, UserHomePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver,UserHomePageUI.FOOTER_MY_ACCOUNT_LINK);
        return PageGenerator.getPage(UserLoginPO.class,driver);
    }
}
