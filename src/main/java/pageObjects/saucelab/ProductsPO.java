package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductsPageUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductsPO extends BasePage {
    private WebDriver driver;
    public ProductsPO (WebDriver driver){
        this.driver = driver;
    }

    public void sortBy(String sortCriteria) {
        waitElementClickable(driver, ProductsPageUI.SORT_DROPDOWN);
        selectItemInDropdown(driver, ProductsPageUI.SORT_DROPDOWN, sortCriteria);
    }

    public String getSelectedSortCriteria() {
        waitElementVisible(driver, ProductsPageUI.SORT_DROPDOWN);
        return getSelectItemInDropdown(driver, ProductsPageUI.SORT_DROPDOWN);
    }

    public boolean isProductNameSortByAsscending() {
        //Lay ra het tat ca element chua Product Name
        List<WebElement> productName = getListElement(driver, ProductsPageUI.PRODUCT_NAME_TEXT);

        //Khai bao mang danh sach A
        ArrayList<String> productList = new ArrayList<String>();

        //Dung vong lap lay Product Name text luu vao danh sach A
        for (WebElement product : productName) {
            System.out.println(product.getText());
            productList.add(product.getText());
        }
        //Khai bao mang danh sach B lay du lieu tu A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product : productList) {
            sortedList.add(product);
        }
        //Sort Ascending
        Collections.sort(sortedList);
        return productList.equals(sortedList);
    }

    public boolean isProductNameSortByDescending() {
        //Lay ra het tat ca element chua Product Name
        List<WebElement> productName = getListElement(driver, ProductsPageUI.PRODUCT_NAME_TEXT);

        //Khai bao mang danh sach A
        ArrayList<String> productList = new ArrayList<String>();

        //Dung vong lap lay Product Name text luu vao danh sach A
        for (WebElement product : productName) {
            System.out.println(product.getText());
            productList.add(product.getText());
        }
        //Khai bao mang danh sach B lay du lieu tu A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product : productList) {
            sortedList.add(product);
        }
        //Sort Ascending
        Collections.sort(sortedList);
        //Sort lai B thanh Descending
        Collections.reverse(sortedList);
        return productList.equals(sortedList);
    }

    public boolean isProducPriceSortByAsscending() {
        //Lay ra het tat ca element chua Product Price
        List<WebElement> productPrice = getListElement(driver, ProductsPageUI.PRODUCT_PRICE_TEXT);

        //Khai bao mang danh sach A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Dung vong lap lay Product Name text luu vao danh sach A
        for (WebElement product : productPrice) {
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }
        //Khai bao mang danh sach B lay du lieu tu A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product : productList) {
            sortedList.add(product);
        }
        //Sort Ascending
        Collections.sort(sortedList);
        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortByDescending() {
        //Lay ra het tat ca element chua Product Price
        List<WebElement> productPrice = getListElement(driver, ProductsPageUI.PRODUCT_PRICE_TEXT);

        //Khai bao mang danh sach A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Dung vong lap lay Product Name text luu vao danh sach A
        for (WebElement product : productPrice) {
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }
        //Khai bao mang danh sach B lay du lieu tu A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product : productList) {
            sortedList.add(product);
        }
        //Sort Ascending
        Collections.sort(sortedList);
        //Sort lai B thanh Descending
        Collections.reverse(sortedList);
        return productList.equals(sortedList);
    }
}
