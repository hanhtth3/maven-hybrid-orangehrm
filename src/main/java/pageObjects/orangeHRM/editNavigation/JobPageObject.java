package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class JobPageObject extends EditNavigatorPageObject {
    private WebDriver driver;
    public JobPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void enterToDatePickerByLabel(WebDriver driver, String joinedDate, String jJoinDate) {
    }

    public String getDatepickerValueByLabel(WebDriver driver, String joinedDate) {
        return joinedDate;
    }
}
