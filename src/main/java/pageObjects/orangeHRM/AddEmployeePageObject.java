package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EditNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.EditNavigatorPageUI.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter to FirstName textbox with info: {0}")
    public void enterToFirtNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.FIRST_NAME_TEXTBOX,firstName);
    }
    @Step("Get info EmployeeID: {0}")
    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDomProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX,"value");
    }
    @Step("Enter to LastName textbox with info: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.LAST_NAME_TEXTBOX,lastName);
    }
    @Step("Click to Save button")
    public PersonalDetailPageObject clickSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeePageUI.SPINNER_ICON);
        return PageGenerator.getPage(PersonalDetailPageObject.class,driver);
    }
}
