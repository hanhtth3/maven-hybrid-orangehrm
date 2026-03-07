package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.EditNavigatorPageUI.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AddEmployeePageObject clickToAddEmployeeButton() {
    waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
    clickToElement(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
    waitListElementInvisible(driver, EmployeeListPageUI.SPINNER_ICON);
    return PageGenerator.getPage(AddEmployeePageObject.class,driver);
    }
}
