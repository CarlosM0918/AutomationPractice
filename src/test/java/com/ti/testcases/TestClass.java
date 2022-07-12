package com.ti.testcases;

import com.aventstack.extentreports.Status;
import com.ti.pages.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.security.cert.TrustAnchor;

public class TestClass extends BaseTestClass {
    @Test(priority = 1, enabled = true, groups = {"Bug"})
    void test001_verifyHomeNavigation() {
        mainPage.searchProduct(inputData.get("product")).search();
        mainPage.verifyProductsListed(inputData.get("product"));
        extent.createTest("test001_verifyHomeNavigation").fail(String.valueOf(Status.FAIL));
    }

    @Test(enabled = true, groups = {"Smoke"})
    void test002_productsCanBeAdded() {
        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        shopPage.categoryTShirt().selectProduct().addCart();
        extent.createTest("test002_productsCanBeAdded").getStatus();
    }

    @Test(enabled = true, groups = {"Smoke"} )
    void test003_CartReflectProductsAdded() {
        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        shopPage.categoryTShirt().selectProduct().addCart();
        shopPage.verifyProductsInCart();
        extent.createTest("test003_CartReflectProductsAdded").getStatus();
    }

    @Test(enabled = true, groups = {"Smoke"})
    void test004_RemoveFromCartHeader() {
        shopPage.categoryTShirt().selectProduct().addCart();
        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        shopPage.mouseHoverCart().removeProduct();
        extent.createTest("test004_RemoveFromCartHeader").getStatus();
    }

    @Test(enabled = true, groups = {"Smoke"})
    void test005_CheckoutForm() {
        shopPage.categoryTShirt().selectProduct().addCart();
        shopPage.searchProduct(inputData.get("product")).search().selectProduct().addCart();
        shopPage.mouseHoverCart().checkout();
        extent.createTest("test005_CheckoutForm").getStatus();
    }

    @Test(enabled = true, groups = {"Smoke"})
    void test006_CantactFormWithValidData() throws AWTException {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifySending();
        extent.createTest("test006_CantactFormWithValidData").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test007_CantactFormWithEmptyInputs(){
        contactPage.goContactForm();
        contactPage.sendMessage().verifyNoSending();
        extent.createTest("test007_CantactFormWithEmptyInputs").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test008_CantactFormWithNoSubject() throws AWTException {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifyNoSending();
        extent.createTest("test008_CantactFormWithNoSubject").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test009_CantactFormWithInvalidEmail() throws AWTException {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("invalidEmail")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifyNoSending();
        extent.createTest("test009_CantactFormWithInvalidEmail").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test010_CantactFormWithoutFile() {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifySending();
        extent.createTest("test010_CantactFormWithoutFile").getStatus();
    }

    @Test(enabled = true, groups = {"Bug"})
    void test011_CantactFormWithBlankSpaces()  {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("blankSpace"))
                .withMessage(inputData.get("blankSpace")).sendMessage().verifyNoSending();
        extent.createTest("test011_CantactFormWithBlankSpaces").fail(String.valueOf(Status.FAIL));
    }
    @Test(enabled = true, groups = {"Bug"})
    void test012_CantactFormWithSpecialCharacters()  {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("specialCharacters"))
                .withMessage(inputData.get("specialCharacters")).sendMessage().verifyNoSending();
        extent.createTest("test012_CantactFormWithSpecialCharacters").fail(String.valueOf(Status.FAIL));
    }
}
