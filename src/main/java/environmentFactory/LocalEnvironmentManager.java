package action.environmentFactory;

import action.browserFactory.*;
import browserFactory.BrowserFactory.*;
import org.openqa.selenium.WebDriver;

public class LocalEnvironmentManager implements action.BrowserFactory.EnvironmentFactory.EnvironmentFactory {
    private WebDriver driver;
    private String browserName;

    public LocalEnvironmentManager(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxBrowserManager().getDriver();
                break;
            case CHROME:
                driver = new ChromeBrowserManager().getDriver();
                break;
            case EDGE:
                driver = new IEBrowserManager().getDriver();
                break;
            case HEAD_CHROME:
                driver = new ChromeHeadlessBrowserManager().getDriver();
                break;
            case HEAD_FIREFOX:
                driver = new FirefoxHeadlessBrowserManager().getDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }
        return driver;
    }
}