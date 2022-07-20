package com.ti.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ContactPage extends MainPage{
    private SoftAssert softAssert;
    File imgFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\imgPrueba.jpg");
    String imgPath = imgFile.getAbsolutePath();
    Robot robot;

    @FindBy(css = "#contact-link")
    private WebElement btnContact;
    @FindBy(css = "#id_contact")
    private WebElement subject;
    @FindBy(css = "#email")
    private WebElement txtEmail;
    @FindBy(css = "#id_order")
    private WebElement txtIdOrder;
    @FindBy(css = "#message")
    private WebElement txtMessage;
    @FindBy(css = "#submitMessage")
    private WebElement btnSendMessage;
    @FindBy(css = "#center_column")
    private WebElement throwedAlert;
    @FindBy(css = "#fileUpload")
    private WebElement btnAddFile;

    public ContactPage goContactForm() {
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        builder.moveToElement(btnContact).click().build().perform();
//        btnContact.click();
        return this;
    }
    public ContactPage sendMessage(){
        btnSendMessage.click();
        return this;
    }

    public ContactPage selectSubject(String Subject){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Select dprSubject = new Select(subject);
        dprSubject.selectByVisibleText(Subject);
        return this;
    }
    public ContactPage withEmail(String email){
        txtEmail.clear();
        txtEmail.sendKeys(email);
        return this;
    }
    public ContactPage withIdOrder(String idOrder){
        txtIdOrder.clear();
        txtIdOrder.sendKeys(idOrder);
        return this;
    }
    public ContactPage withMessage(String message){
        txtMessage.clear();
        txtMessage.sendKeys(message);
        return this;
    }

    public ContactPage verifySending(){
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        softAssert.assertTrue(throwedAlert.getText().contains("Your message has been successfully sent to our team."), "Message not sent. Error: The message was expected to be sent.");
        softAssert.assertAll();
        return this;
    }
    public ContactPage verifyNoSending(){
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        softAssert.assertFalse(throwedAlert.getText().contains("Your message has been successfully sent to our team."), "Message sent. Error: The message was expected not to be sent.");
        softAssert.assertAll();
        return this;
    }

    public ContactPage addFile(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        js =(JavascriptExecutor)driver;
        js.executeScript("var element = document.getElementById('center_column');\n" +
                "element.scrollIntoView()");
        btnAddFile.clear();
        btnAddFile.sendKeys(imgPath);
        return this;
    }

}
