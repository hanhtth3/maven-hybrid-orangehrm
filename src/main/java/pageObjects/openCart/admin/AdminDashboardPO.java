package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminDashBoardPageUI;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminCustomerPO openCustomerPage() {
        waitElementClickable(driver, AdminDashBoardPageUI.CUSTOMER_MENU);
        clickToElement(driver, AdminDashBoardPageUI.CUSTOMER_MENU);
        sleepInSecond(2);
        waitElementClickable(driver, AdminDashBoardPageUI.CUSTOMER_LINK);
        clickToElement(driver, AdminDashBoardPageUI.CUSTOMER_LINK);
        return PageGenerator.getPage(AdminCustomerPO.class,driver);
    }

}
