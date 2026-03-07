package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {
    public static BasePageFactory getInstance() {
       return new BasePageFactory();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            sleepInSecond(2);
            if (driver.getTitle().contains(expectedTitle)) {
                break;
            }
            sleepInSecond(2);
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            sleepInSecond(2);
        }
        driver.switchTo().window(windowID);
    }

    public void clickToElement( WebElement element){
       element.click();
    }

    public void sendKeyToElement(WebElement element, String keyToSend){
        element.clear();
        element.sendKeys(keyToSend);
    }

    public String getElementDomAttribute (WebElement element, String attributeName){
        return element.getDomAttribute(attributeName);
    }

    public String getElementDomProperty (WebElement element, String propertyName){
        return element.getDomProperty(propertyName);
    }

    public String getElementText( WebElement element){
        return  element.getText();
    }

    public boolean isElementDisplay (WebElement element)
    {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element)
    {
       return element.isSelected();
    }

    public boolean isElementEnabled( WebElement element)
    {
            return element.isEnabled();
    }

    public WebElement waitElementVisible (WebDriver driver,WebElement element){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitListElementVisible (WebDriver driver,List<WebElement> elements){
       return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitElementSelected (WebDriver driver,WebElement element){
       return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeSelected(element));
    }

    public WebElement waitElementClickable (WebDriver driver,WebElement element){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    public boolean waitElementInvisible (WebDriver driver,WebElement element){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible (WebDriver driver,List<WebElement> elements){
       return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    private final int SHORT_TIME = 10;
    private final int LONG_TIME = 30;
}
