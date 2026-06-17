package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.EditNavigation.EditNavigatorPageObject;

public class EmergencyContactPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public EmergencyContactPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
