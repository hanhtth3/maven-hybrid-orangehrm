package core;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    public static BasePage getInstance() {
        return new BasePage();
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

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private By getByLocator(String locatorType) {
        if (locatorType == null || locatorType.trim().isEmpty()) {
            throw new IllegalArgumentException("Locator type cannot be null or empty");
        }

        String[] locatorArr = locatorType.split("=", 2);
        String locatorPrefix = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

        switch (locatorPrefix.toUpperCase()) {
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Locator type cannot be null or empty:" + locatorValue);
        }
    }

    public WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public WebElement getWebElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElement(getByLocator(castParameter(locator, restValue)));
    }

    public List<WebElement> getListElement(WebDriver driver, String locatoType) {
        return driver.findElements(getByLocator(locatoType));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElements(getByLocator(castParameter(locator, restValue)));
    }

    public void clickToElement(WebDriver driver, String locator, String... restValues) {
        getWebElement(driver, castParameter(locator, restValues)).click();
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, CharSequence keyToSend) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver, String locator, CharSequence keyToSend, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).clear();
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(keyToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator))
                .selectByVisibleText(valueItem);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator))
                .getFirstSelectedOption().getText();
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem, String... restValue) {
        new Select(getWebElement(driver, castParameter(locator, restValue)))
                .selectByVisibleText(valueItem);
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInTableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(3);
                break;
            }
        }
    }

    public void selectItemInTableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem, String... restValue) {
        getWebElement(driver, castParameter(parentLocator, restValue)).click();
        sleepInSecond(1);
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

        List<WebElement> allItems = getListElement(driver, childLocator);

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(3);
                break;
            }
        }
    }

    public String getElementDomAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDomAttribute(WebDriver driver, String locator, String attributeName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomAttribute(attributeName);
    }

    public String getElementDomProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDomProperty(WebDriver driver, String locator, String propertyName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByGRBA(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locator, String... restValue) {
        return getListElement(driver, castParameter(locator, restValue)).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        if (!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckbox(WebDriver driver, String locator, String... restValue) {
        if (!getWebElement(driver, castParameter(locator, restValue)).isSelected()) {
            getWebElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplay(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplay(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).isDisplayed();
    }

    private void overrideGlobalTimeout(WebDriver driver, long timeSecond){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSecond));
    }

    public boolean isElementUndisplay(WebDriver driver,String locator, String...restValue){
        overrideGlobalTimeout(driver,SHORT_TIME);
        List<WebElement> elements = getListElement(driver,castParameter(locator,restValue));
        overrideGlobalTimeout(driver,LONG_TIME);

        if (elements.size()=0){
            return true;
        } else (elements.size()>0)&& !elements.get(0).isDisplayed(){
            return true;
        } else {
            return  false;
        }

    }
    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator));
    }

    public void moveToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator));
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),
                getWebElement(driver, targetLocator));
    }

    public void sendKeyBroadToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key);
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);

    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDomAttribute(driver, locator, "style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }

    public WebElement waitElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementVisibleNotInDOM(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIME))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restValue))));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator, String... restvalue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restvalue))));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions
                        .invisibilityOfAllElements(getListElement(driver, castParameter(locator, restValue))));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    public UserLoginPO clickToLogoutLinkAtUserSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        waitElementClickable(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(UserLoginPO.class, driver);
    }

    public AdminLoginPO clickToLogoutLinkAtAdminSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    public AdminLoginPO openAdminSite(WebDriver driver, String adminURL) {
        openPageUrl(driver, adminURL);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    public UserHomePO openUserSite(WebDriver driver, String userURL) {
        openPageUrl(driver, userURL);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public UserHomePO openHomeLogo(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_HOME_LOGO);
        clickToElement(driver, BasePageUI.USER_HOME_LOGO);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public void openUrlByNewTab(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public void openUrlByNewWindow(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
    }

    private String castParameter(String locator, String... restValue) {
        return String.format(locator, (Object[]) restValue);
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileName) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileName) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageUI.UP_LOAD_FILE_TYPE).sendKeys(fullFileName.trim());
    }

    //Orange HRM - dung rieng cho site nay
    @Step("Enter to {0} textbox by label with value {1}")
    public void enterToTextboxByLabel(WebDriver driver, String textboxLabel, String valueToSendkey) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        sendKeyToElement(driver, BasePageUI.TEXTBOX_BY_LABEL, valueToSendkey, textboxLabel);
    }

    @Step("Enter to {0} textbox by name with value {1}")
    public void enterToTextboxByName(WebDriver driver, String textboxNameAttribute, String valueToSendkey) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        sendKeyToElement(driver, BasePageUI.TEXTBOX_BY_NAME, valueToSendkey, textboxNameAttribute);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        sendKeyToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public void clickToButtonByTextInMainTitle(WebDriver driver, String buttonText, String mainTitleName) {
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
        sendKeyToElement(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
    }

    public void getTextboxValueByName(WebDriver driver, String textboxNameAttribute) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        getElementDomAttribute(driver, BasePageUI.TEXTBOX_BY_NAME, "value", textboxNameAttribute);
    }

    public void getTextboxValueByLabel(WebDriver driver, String textboxLabel) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        getElementDomAttribute(driver, BasePageUI.TEXTBOX_BY_LABEL, "value", textboxLabel);
    }

    @Step("Click to {0} module")
    public void clickToModuleByNameInMenuItem(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        clickToElement(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public boolean isModuleByTextInMenuItemDisplayed(WebDriver driver, String moduleName) {
        waitListElementVisibleNotInDOM(driver,BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM,moduleName);
        return isElementUndisplay(driver,BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM,moduleName);
    }

    public boolean isModuleByTextInMenuItemUnDisplayed(WebDriver driver, String moduleName) {
        waitElementVisible(driver,BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM,moduleName);
        return isElementDisplay(driver,BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM,moduleName);
    }

    public void selectDropdownByLabel(WebDriver driver, String labelName, String valueToSelect) {
        waitElementVisible(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInTableDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.CHILD_DROPDOWN_BY_LABEL, valueToSelect, labelName);
    }

    public boolean isToastMessageDisplayed(WebDriver driver, String toastMessage) {
        waitElementVisible(driver,BasePageUI.TOAST_MESSAGE_BY_TEXT,toastMessage);
        return isElementDisplay(driver,BasePageUI.TOAST_MESSAGE_BY_TEXT,toastMessage);
    }

    public void clickToRadioButton(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        sendKeyToElement(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.CHECKBOX_BUTTON_BY_LABEL, labelName);
        sendKeyToElement(driver, BasePageUI.CHECKBOX_BUTTON_BY_LABEL, labelName);
    }

    public String getCurrentWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }
    private final int SHORT_TIME = GlobalConstants.SHORT_TIMEOUT;
    private final int LONG_TIME = GlobalConstants.LONG_TIMEOUT;

}
