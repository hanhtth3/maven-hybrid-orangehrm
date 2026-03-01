package pageObjects;

import org.openqa.selenium.WebDriver;
import pageFactory.AddEmployeePageObject;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashBoardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }
    public static AddEmployeePageObject getAddEmloyeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }
    public static EmployeeListPageObject getEmployeeListPage(WebDriver driver){
        return new EmployeeListPageObject(driver);
    }
    public static PersonalDetailPageObject getPersonalDetailPage(WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static ContactDetailPageObject getContactDetailPage(WebDriver driver){
        return new ContactDetailPageObject(driver);
    }
}
