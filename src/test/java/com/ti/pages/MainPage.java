package com.ti.pages;

import com.ti.base.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @FindBy(css = "div:nth-child(6) > ul:nth-child(2) > li:nth-child(3)")
    private WebElement tabTshirts;
    @FindBy(css = "button[name='Submit']")
    private WebElement btnAddCart;
    @FindBy(css = ".cross")
    private WebElement btnClose;
    @FindBy(css = "h5[itemprop='name']")
    private List<WebElement> searchResultList;
    @FindBy(css = ".products")
    private WebElement productsInCart;
    @FindBy(css = ".shopping_cart")
    private WebElement cartInHeader;
    @FindBy(css = ".ajax_cart_no_product")
    private WebElement noExistancesInCart;
    @FindBy(css = "a[title='remove this product from my cart']")
    private WebElement btnRemove;
    @FindBy(css = "#button_order_cart")
    private WebElement btnCkeckout;

    @FindBy(css = "#layer_cart")
    private WebElement modal;

    public MainPage(){
		PageFactory.initElements(driver, this);
    }


    public MainPage search(){
        btnSearch.click();
        return this;
    }
    public MainPage categoryTShirt(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(tabTshirts));
        tabTshirts.click();
        return this;
    }
    public MainPage addCart(){
        btnAddCart.click();
        btnClose.click();
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

    public MainPage mouseHoverCart() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.invisibilityOf(modal));
        Actions action = new Actions(driver);
        action.moveToElement(cartInHeader).build().perform();
        Assert.assertTrue(productsInCart.isDisplayed());
        return this;
    }
    public MainPage removeProduct(){
        Actions action = new Actions(driver);
        action.moveToElement(btnRemove).click().build().perform();
        System.out.println("removed");
        return this;
    }

    public MainPage searchProduct(String product){
        txtSearchBar.clear();
        txtSearchBar.sendKeys(product);
        return this;
    }

    public MainPage verifyProductsInCart() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertFalse(noExistancesInCart.isDisplayed(), "The cart has no products added");

        return this;
    }

    public MainPage verifyProductsListed(String obj){
        softAssert = new SoftAssert();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfAllElements(searchResultList));
        for (WebElement product:searchResultList) {
            System.out.println(product.getText());
            softAssert.assertTrue(product.getText().contains(obj), "The product '"+product.getText()+"' isn't contained the keyword.");
        }
        softAssert.assertAll();
        return this;
    }

    public MainPage selectProduct() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        js =(JavascriptExecutor)driver;
        js.executeScript("var element = document.getElementById('center_column');\n" +
                "element.scrollIntoView()");
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfAllElements(searchResultList));
        WebElement firstProduct = searchResultList.get(0);
        firstProduct.click();
        return this;
    }

    public MainPage checkout(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(btnCkeckout));
        btnCkeckout.click();
        return this;
    }
}
