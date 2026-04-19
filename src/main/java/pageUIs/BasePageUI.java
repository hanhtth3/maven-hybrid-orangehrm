package pageUIs;

public class BasePageUI {
    //OrangeHRM
    public static final String SPINNER_ICON = "Css=div.oxd-loading-spinner";
    public static final String TEXTBOX_BY_LABEL = "Xpath=//label[text()=\"%s\"]parent::div/folowing-sibling::div//input";
    public static final String TEXTBOX_BY_NAME = "Xpath=//input[@name='%s']";
    public static final String BUTTON_BY_TEXT = "Xpath=//button[contains(string(),'%s')]";
    public static final String BUTTON_BY_TEXT_IN_MAIN_TITLE = "Xpath=//h6[text(),'%s')]/folowing-sibling::form///button[contains(string(),'%s')]";
    public static final String MODULE_BY_TEXT_IN_MENU_ITEM = "Xpath=//span[text()='%s']/parent::a[contains(@class,'oxd-main-menu-item')]";
    public static final String PARENT_DROPDOWN_BY_LABEL = "Xpath=//label[text()='%s']/parent::div/folowing-sibling::div//i";
    public static final String CHILD_DROPDOWN_BY_LABEL = "Xpath=//label[text()='%s']/parent::div/folowing-sibling::div//div[@class='oxd-select-option']/span";
    public static final String TOAST_MESSAGE_BY_TEXT = "Xpath=//p[contains(@class,'odx-text-toast-message') and text()='%s')";
    public static final String RADIO_BUTTON_BY_LABEL = "Xpath=//label[text()='%s']/input";
    public static final String CHECKBOX_BUTTON_BY_LABEL = "Xpath=//p[text()='%s']/folowing-sibling::div/input"
    //Opencart
    public static final String USER_MY_ACCOUNT_HEADER = "Xpath=//nav[@id='top'//span[text()='My Account']";
    public static final String USER_LOGOUT_LINK_ITEM = "Xpath=//a[@class='dropdown-item' and text()='Logout']";
    public static final String ADMIN_LOGOUT_LINK_ITEM = "Xpath=//li[@id='nav-logout']//span[text()='Logout']";
    public static final String USER_HOME_LOGO = "Css=div#logo>a";
    //JQuery
    public static final String UP_LOAD_FILE_TYPE = "Css=input[type='file']";


}
