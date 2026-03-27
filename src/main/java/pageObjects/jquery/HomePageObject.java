package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String pageNumber) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER,pageNumber);
        clickToElement(driver,HomePageUI.DYNAMIC_PAGE_BY_NUMBER,pageNumber);
    }

    public boolean isPageActiveByNumber(String pageNumber) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER,pageNumber);
        return isElementDisplay(driver,HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER,pageNumber);
    }

    public void enterToHeaderTextboxByName(String headerName, String value) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME,headerName);
        sendKeyToElement(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME,value,headerName);
        sendKeyToElement(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER,headerName);
    }

    public boolean IsPageInfoDisplay(String females, String country, String males, String total) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_INFO_PAGE,females,country,males,total);
        return isElementDisplay(driver,HomePageUI.DYNAMIC_INFO_PAGE,females,country,males,total);
    }

    public void clickToActionByCountryName(String countryName, String actionName) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_ACTION_BY_COUNTRY_NAME,countryName,actionName);
        clickToElement(driver,HomePageUI.DYNAMIC_ACTION_BY_COUNTRY_NAME,countryName,actionName);
    }

    public void clickLoadDataButton() {
        waitElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver,HomePageUI.LOAD_DATA_BUTTON);
    }

    public void enterToTextboxByColumNameAndRowIndex(String columName, String rowIndex, String valueToSendkey) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_COLUM_INDEX_BY_COLUM_NAME,columName);
        int columIndex = getListElementNumber(driver,HomePageUI.DYNAMIC_COLUM_INDEX_BY_COLUM_NAME,columName)+1;

        waitElementPresence(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUM_INDEX,rowIndex,String.valueOf(columIndex));
        sendKeyToElement(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUM_INDEX,valueToSendkey,rowIndex,String.valueOf(columIndex));
    }

    public void selectToDropdownByColumNameAndRowIndex(String columName, String rowIndex, String valueToSelect) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_COLUM_INDEX_BY_COLUM_NAME,columName);
        int columIndex = getListElementNumber(driver,HomePageUI.DYNAMIC_COLUM_INDEX_BY_COLUM_NAME,columName)+1;

        waitElementClickable(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUM_INDEX,rowIndex,String.valueOf(columIndex));
        selectItemInDropdown(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUM_INDEX,valueToSelect,rowIndex,String.valueOf(columIndex));
    }


    public void checkToCheckboxByColumNameAndRowIndex(String columName, String rowIndex) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_COLUM_INDEX_BY_COLUM_NAME,columName);
        int columIndex = getListElementNumber(driver,HomePageUI.DYNAMIC_COLUM_INDEX_BY_COLUM_NAME,columName)+1;

        waitElementClickable(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUM_INDEX,rowIndex,String.valueOf(columIndex));
        checkToCheckbox(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUM_INDEX,rowIndex,String.valueOf(columIndex));
    }

    public void actionToRowByRowIndex(String rowIndex, String actionName) {
        waitElementClickable(driver,HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX,rowIndex, actionName);
        clickToElement(driver,HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX,rowIndex, actionName);
    }

    public List<String> getColumAllValueByColumName (String columName)
    {
        sleepInSecond(5);
        List<WebElement> allPage = getListElement(driver,HomePageUI.ALL_PAGE);
        List<String> columAllValue = new ArrayList<String>();

        waitElementVisible(driver,HomePageUI.DYNAMIC_INDEX_BY_COLUM_NAME,columName);
        int columIndex = getListElementNumber(driver,HomePageUI.DYNAMIC_INDEX_BY_COLUM_NAME,columName)+1;
        sleepInSecond(3);

        for (WebElement page: allPage){
            page.click();

        List<WebElement> columAllValueElement = getListElement(driver,HomePageUI.DYNAMIC_COLUM_INDEX,String.valueOf(columIndex));
            for(WebElement value: columAllValueElement){
            columAllValue.add(value.getText());}
        }
        return columAllValue;
    }
}
