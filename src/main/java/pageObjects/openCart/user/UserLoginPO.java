package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class UserLoginPO extends BasePage {
    WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }
}
