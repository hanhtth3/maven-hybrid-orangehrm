package pageObjects.gofile;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.gofile.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoadingIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.ICON_LOADING);
    }

    public boolean isProgessBarIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.PROGESS_BAR_LOADING);
    }

    public String getSuccessLink() {
        waitElementInvisible(driver, HomePageUI.SUCCESS_CARD_LINK);
        return getElementText(driver,HomePageUI.SUCCESS_CARD_LINK);
    }

    public boolean isfileUploadedSuccess(String fileName) {
        waitListElementInvisible(driver,HomePageUI.UPLOADED_FILE_NAME,fileName);
        return isElementDisplay(driver,HomePageUI.UPLOADED_FILE_NAME,fileName);

    }
}
