package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pageUIs.techpanda.MyAccountPageUI;

import java.util.Set;

public class MyAccountPO extends BasePage {
    private WebDriver driver;
    public MyAccountPO(WebDriver driver){
        this.driver = driver;
    }

    public String getSuccessMsg() {
        waitElementVisible(driver, MyAccountPageUI.SUCCESS_MGS);
        return getElementText(driver,MyAccountPageUI.SUCCESS_MGS);
    }

    public String getMyAccountPageTitle() {
        waitElementVisible(driver,MyAccountPageUI.MY_DASHBOAD_TITLE);
        return getElementText(driver,MyAccountPageUI.MY_DASHBOAD_TITLE);
    }

    public Set<Cookie> getPageCookies(WebDriver driver) {
        return Set.of();
    }
}

