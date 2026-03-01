package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.PersonalDetailPageUI;

public class ContactDetailPageObject extends BasePage {
    private WebDriver driver;
    public ContactDetailPageObject(WebDriver driver){
        this.driver = driver;
    }

}
