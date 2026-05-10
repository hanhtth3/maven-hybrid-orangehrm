package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.techpanda.MyAccountPageUI;

public class MyAccountPO extends BasePage {
    private WebDriver driver;
    public MyAccountPO(WebDriver driver){
        this.driver = driver;
    }

    public String getSuccessMsg() {
        waitElementVisible(driver, MyAccountPageUI.SUCCESS_MGS);
        return getElementText(driver,MyAccountPageUI.SUCCESS_MGS)
    }

    public String getMyAccountPageTitle() {
        waitElementVisible(driver,MyAccountPageUI.MY_DASHBOAD_TITLE);
        return getElementText(driver,MyAccountPageUI.MY_DASHBOAD_TITLE);
    }
}

