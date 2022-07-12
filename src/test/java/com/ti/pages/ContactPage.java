package com.ti.pages;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class ContactPage extends MainPage{
    private SoftAssert softAssert;
    String imgPath = "C:\\Users\\Azul\\Desktop\\material";
    String imgfile = "imgPrueba.jpg";
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

    public ContactPage addFile() throws AWTException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        js =(JavascriptExecutor)driver;
        js.executeScript("var element = document.getElementById('center_column');\n" +
                "element.scrollIntoView()");
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        builder.moveToElement(btnAddFile).click().build().perform();

        robot = new Robot();
//        Copiar y pegar la ruta ScreenCoord para cambiar cordenadas
        moveAndClick(343,49);
        robot.delay(200);
        selectFromClipboard(imgPath);
//        Pegar el nombre del archivo y pegar
        moveAndClick(610,538);
        selectFromClipboard(imgfile);

        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(3000);
        return this;
    }

    private void selectFromClipboard(String elementForClipboard){
        StringSelection stringSelection = new StringSelection(elementForClipboard);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(200);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(200);
    }

    private void moveAndClick(int x,  int y){
        robot.mouseMove(x, y);
        robot.delay(200);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(1000);
    }
}
