package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.EditNavigation.EditNavigatorPageObject;

public class QualificationsPageObject extends EditNavigatorPageObject {
    private WebDriver driver;
    public QualificationsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}