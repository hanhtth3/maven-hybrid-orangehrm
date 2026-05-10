package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPO extends BasePage {
    private WebDriver driver;
    public LoginPO (WebDriver driver){
        this.driver = driver;
    }

    public RegisterPO clickCreatAnAccountLink() {
    }
}

