package pageObjects.orangeHRM.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

public class PersonalDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get Firstname textbox attribute value")
    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    @Step("Get Lastname textbox attribute value")
    public String getLastNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX, "value");
    }
    @Step("Enter to Firstname textbox with value: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to Lastname textbox with value: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Get EmployeeID textbox attribute value")
    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX, "value");
    }
    public void clickToProfileImage() {
        waitElementClickable(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
        clickToElement(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
    }

    public String getErrorMessageAtProfileImage() {
        waitElementVisible(driver, PersonalDetailPageUI.PROFILE_IMAGE_UPLOAD_ERROR_MESSAGE);
        return getElementText(driver, PersonalDetailPageUI.PROFILE_IMAGE_UPLOAD_ERROR_MESSAGE);
    }

    public Dimension getProfileNaturalImageSize() {
        waitElementVisible(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
        int x = Integer.parseInt(getElementDomProperty(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE, "naturalWidth"));
        int y = Integer.parseInt(getElementDomProperty(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE, "naturalHeight"));
        return new Dimension(x, y);
    }


    public void enterToDatePickerByLabel(WebDriver driver, String dateOfBirth, String eBirth) {
    }

    public String getDatepickerValueByLabel(WebDriver driver, String dateOfBirth) {
        return dateOfBirth;
    }

    public void clickToRadioButton(WebDriver driver, String disabled) {

    }
}