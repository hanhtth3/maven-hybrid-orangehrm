package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirtNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.FIRST_NAME_TEXTBOX,firstName);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDomProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX,"value");
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.LAST_NAME_TEXTBOX,lastName);
    }

    public void clickSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
    }
}
