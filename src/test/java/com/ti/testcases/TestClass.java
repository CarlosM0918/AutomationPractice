package com.ti.testcases;

import com.ti.pages.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.security.cert.TrustAnchor;

public class TestClass extends BaseTestClass {
    @Test(priority = 1, enabled = false)
    void test001_verifyHomeNavigation() {
        mainPage.searchProduct(inputData.get("product")).search();
        mainPage.verifyProductsListed(inputData.get("product"));
    }

    @Test(enabled = false)
    void test002_productsCanBeAdded() {
//        mainPage.home();
        shopPage.categoryTShirt().selectProduct().addCart();
        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
    }

    @Test(enabled = false )
    void test003_CartReflectProductsAdded(){
        shopPage.categoryTShirt().selectProduct().addCart();
        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        shopPage.verifyProductsInCart();
    }

    @Test(enabled = false)
    void test004_RemoveFromCartHeader() throws InterruptedException {
        shopPage.categoryTShirt().selectProduct().addCart();
        shopPage.mouseHoverCart().removeProduct();
    }

    @Test(enabled = true)
    void test005_CheckoutForm() {
        shopPage.categoryTShirt().selectProduct().addCart();
//        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        shopPage.mouseHoverCart().checkout();
    }

    @Test(enabled = false)
    void test006_CantactFormWithValidData() throws AWTException {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifySending();
    }
    @Test(enabled = false)
    void test007_CantactFormWithEmptyInputs(){
        contactPage.goContactForm();
        contactPage.sendMessage().verifyNoSending();
    }
    @Test(enabled = false)
    void test008_CantactFormWithNoSubject() throws AWTException {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifyNoSending();
    }
    @Test(enabled = false)
    void test009_CantactFormWithInvalidEmail() throws AWTException {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("invalidEmail")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifyNoSending();
    }
    @Test(enabled = false)
    void test010_CantactFormWithoutFile() {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifySending();

    }

    @Test(enabled = false)
    void test011_CantactFormWithBlankSpaces()  {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("blankSpace"))
                .withMessage(inputData.get("blankSpace")).sendMessage().verifyNoSending();

    }
    @Test(enabled = false)
    void test012_CantactFormWithSpecialCharacters()  {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("specialCharacters"))
                .withMessage(inputData.get("specialCharacters")).sendMessage().verifyNoSending();

    }
}
