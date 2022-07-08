package com.ti.testcases;

import com.ti.pages.ShopPage;
import org.testng.annotations.Test;

public class TestClass extends BaseTestClass {
    @Test(priority = 1, enabled = false)
    void verifyHomeNavigation() {
        mainPage.searchProduct(inputData.get("product")).search();
        mainPage.verifyProductsListed(inputData.get("product"));
    }

    @Test
    void productsCanBeAdded() throws InterruptedException {
//        mainPage.home();
        shopPage.categoryTShirt()/*.selectProduct().addCart()*/;
//        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        Thread.sleep(3000);
    }

}
