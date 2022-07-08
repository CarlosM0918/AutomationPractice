package com.ti.pages;

import com.ti.base.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class MainPage {
    WebDriver driver = DriverFactory.getInstance().getDriver();
    private SoftAssert softAssert;
    public JavascriptExecutor js;

    @FindBy(css = "#search_query_top")
    private WebElement txtSearchBar;
    @FindBy(css = ".button-search")
    private WebElement btnSearch;
    @FindBy(css = "a[title='My Store']")
    private WebElement btnBrand;
    @FindBy(linkText = "T-shirts")
    private WebElement tabTshirts;
    @FindBy(css = "button[name='Submit']")
    private WebElement btnAddCart;
    @FindBy(css = ".cross")
    private WebElement btnClose;

    @FindBy(css = "h5[itemprop='name']")
    private List<WebElement> searchResultList;

    public MainPage(){
		PageFactory.initElements(driver, this);
    }


    public MainPage search(){
        btnSearch.click();
        return this;
    }
    public MainPage categoryTShirt(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(tabTshirts));
        tabTshirts.click();
        return this;
    }
    public MainPage addCart(){
        btnAddCart.click();
        return this;
    }
    public MainPage closeModal(){
        btnClose.click();
        return this;
    }

    public  MainPage home(){
        btnBrand.click();
        return this;
    }

    public MainPage searchProduct(String product){
        txtSearchBar.clear();
        txtSearchBar.sendKeys(product);
        return this;
    }

    public MainPage verifyProductsListed(String obj){
        softAssert = new SoftAssert();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfAllElements(searchResultList));
        for (WebElement product:searchResultList) {
            System.out.println(product.getText());
            softAssert.assertTrue(product.getText().contains(obj), "The product '"+product.getText()+"' isn't contained the keyword");
        }
        softAssert.assertAll();
        return this;
    }

    public MainPage selectProduct(){
        WebElement firstProduct = searchResultList.get(0);
        firstProduct.click();
        return this;
    }
}
