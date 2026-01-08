package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
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

    private void sleepInSecond(long timeInSecond) {
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

    private By getByXpath (String locator){
        return By.xpath(locator);
    }

    private WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }

    private List <WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
       getWebElement(driver,locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend){
        getWebElement(driver,locator).sendKeys(keyToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem){
        new Select(getWebElement(driver,locator))
                .selectByVisibleText(valueItem);
    }

    public String getSelectItemInDropdown (WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator))
                .getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple (WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).isMultiple();
    }

    public void selectItemInDropdown (WebDriver driver,String parentLocator, String childLocator, String textItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        List<WebElement> allItems = getListElement(driver,childLocator);

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(3);
                break;
            }
        }
    }

    public String getElementDomAttribute (WebDriver driver, String locator, String attributeName…){
        return getWebElement(driver,locator).getDomAttribute(attributeName);
    }

    public String getElementDomProperty (WebDriver driver, String locator, String propertyName…){
        return getWebElement(driver,locator).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator){
        return  getWebElement(driver,locator).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return  getWebElement(driver,locator).getCssValue(propertyName);
    }

    public String getHexaByGRBA (String rgbaColor){
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber (WebDriver driver, String locator){
        return getListElement(driver,locator).size();
    }

    public void checkToCheckbox (WebDriver driver, String locator){
        if (!getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public void uncheckToCheckbox (WebDriver driver, String locator){
        if (getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public boolean isElementDisplay (WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
       return getWebElement(driver,locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
            return getWebElement(driver,locator).isEnabled();
    }

    public void switchToFrame (WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver,locator));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator));
    }

    public void moveToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator));
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),
                getWebElement(driver,targetLocator));
    }

    public void sendKeyBroadToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, locator),key);
    }

    public  Object executeForBrowser(WebDriver driver,String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);

    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver,String locator ) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDomAttribute(driver,locator,"style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver,locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver,locator));
    }

    public void scrollToElementOnTop(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,locator));
    }

    public void scrollToElementOnDown(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver,locator));
    }

    public String getAttributeInDOM(WebDriver driver,String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver,locator));
    }

    public String getElementValidationMessage(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver,locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver,locator));
        return status;
    }

    public void waitElementVisible (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitListElementVisible (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfAllElements(getByXpath(locator)));
    }

    public void waitElementSelected (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public void waitElementClickable (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }
    public void waitListElementInvisible (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitListElementinvisible (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver,locator)));
    }

    public void waitListElementPresence (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public void waitListElementPresence (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    private final int SHORT_TIME = 10;
    private final int LONG_TIME = 30;
}
