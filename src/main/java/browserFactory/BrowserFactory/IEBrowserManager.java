package browserFactory.BrowserFactory;

import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IEBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        if (GlobalConstants.OS_NAME.toUpperCase().contains("WINDOWS")) {
            throw new BrowserNotSupportedException("IE is not supported on " + GlobalConstants.OS_NAME);
        }
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.destructivelyEnsureCleanSession();
        ieOptions.ignoreZoomSettings();
        ieOptions.introduceFlakinessByIgnoringSecurityDomains();
        return new InternetExplorerDriver(ieOptions);
    }
}
