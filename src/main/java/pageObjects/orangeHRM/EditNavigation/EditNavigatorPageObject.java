package pageObjects.orangeHRM.EditNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.EditNavigatorPageUI.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
    WebDriver driver;

    //Cach 1
    public EditNavigatorPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public PersonalDetailPageObject openPersonnalDetailPage()
    {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAILS_LINK);
        clickToElement(driver,EditNavigatorPageUI.PERSONAL_DETAILS_LINK);
        return PageGenerator.getPage(PersonalDetailPageObject.class,driver);
    }

    public DependentsPageObject openDependentsPage()
    {
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        clickToElement(driver,EditNavigatorPageUI.DEPENDENT_LINK);
        return PageGenerator.getPage(DependentsPageObject.class,driver);
    }

    public ContactDetailPageObject openContactDetailPage()
    {
        waitElementClickable(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver,EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGenerator.getPage(ContactDetailPageObject.class,driver);
    }

    public JobPageObject openJobPage()
    {
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver,EditNavigatorPageUI.JOB_LINK);
        return PageGenerator.getPage(JobPageObject.class,driver);
    }

    //Cach 2
    public EditNavigatorPageObject openEditNavigatorByPageName(String pageName)
    {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME,pageName);
        clickToElement(driver,EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME,pageName);
        System.out.println("Page name:" +pageName);

        switch (pageName){
            case "Personal Details":
                return PageGenerator.getPage(PersonalDetailPageObject.class,driver);
            case "Dependents":
                return PageGenerator.getPage(DependentsPageObject.class,driver);
            case "Contact Details":
                return PageGenerator.getPage(ContactDetailPageObject.class,driver);
            case "Job":
                return PageGenerator.getPage(JobPageObject.class,driver);
            default:
                throw new IllegalArgumentException("Page name is not valid:"+pageName);
        }
    }

    //Cach 3
    public void  openEditNavigatorByName(String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        System.out.println("Page name:" + pageName);
    }
}
