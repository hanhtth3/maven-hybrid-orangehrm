package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class DependentsPageObject extends BasePage {
    private WebDriver driver;

    public DependentsPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
