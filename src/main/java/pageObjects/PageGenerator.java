package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGenerator {

    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            Constructor<T> constractor = pageClass.getConstructor(WebDriver.class);
            return constractor.newInstance(driver);
        }
        catch (Exception e) {
            throw new RuntimeException("Cannot init Page Object  class" + pageClass.getSimpleName(),e);
        }
    }
}


