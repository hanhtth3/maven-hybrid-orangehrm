package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.admin.AdminCustomerPageUI;
import pageUIs.openCart.admin.AdminDashBoardPageUI;

public class AdminCustomerPO extends BasePage {
    WebDriver driver;

    public AdminCustomerPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCustomerPageDisplay() {
        waitElementVisible(driver, AdminCustomerPageUI.CUSTOMER_HEADER_TEXT);
        return isElementDisplay(driver,AdminCustomerPageUI.CUSTOMER_HEADER_TEXT);
    }
}
