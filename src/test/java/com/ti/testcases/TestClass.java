package com.ti.testcases;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

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
    void test006_ContactFormWithValidData() {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifySending();
        extent.createTest("test006_ContactFormWithValidData").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test007_ContactFormWithEmptyInputs(){
        contactPage.goContactForm();
        contactPage.sendMessage().verifyNoSending();
        extent.createTest("test007_ContactFormWithEmptyInputs").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test008_ContactFormWithNoSubject() {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifyNoSending();
        extent.createTest("test008_ContactFormWithNoSubject").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test009_ContactFormWithInvalidEmail() {
        contactPage.goContactForm();
        contactPage.addFile();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("invalidEmail")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifyNoSending();
        extent.createTest("test009_ContactFormWithInvalidEmail").getStatus();
    }
    @Test(enabled = true, groups = {"Smoke"})
    void test010_ContactFormWithoutFile() {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("orderId"))
                .withMessage(inputData.get("message")).sendMessage().verifySending();
        extent.createTest("test010_ContactFormWithoutFile").getStatus();
    }

    @Test(enabled = true, groups = {"Bug"})
    void test011_ContactFormWithBlankSpaces()  {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("blankSpace"))
                .withMessage(inputData.get("blankSpace")).sendMessage().verifyNoSending();
        extent.createTest("test011_ContactFormWithBlankSpaces").fail(String.valueOf(Status.FAIL));
    }
    @Test(enabled = true, groups = {"Bug"})
    void test012_ContactFormWithSpecialCharacters()  {
        contactPage.goContactForm();
        contactPage.selectSubject(inputData.get("subjectText")).withEmail(inputData.get("email")).withIdOrder(inputData.get("specialCharacters"))
                .withMessage(inputData.get("specialCharacters")).sendMessage().verifyNoSending();
        extent.createTest("test012_ContactFormWithSpecialCharacters").fail(String.valueOf(Status.FAIL));
    }
}
