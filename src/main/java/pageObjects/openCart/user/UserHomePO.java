package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }
}
