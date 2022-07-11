package com.ti.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class ShopPage extends MainPage {
    private SoftAssert softAssert;

    @FindBy(css = "input[value='Send Message']")
    private WebElement btnSendMessage;
    @FindBy(css = ".top_offset")
    private WebElement messageSent;
    @FindBy(css = "input[type=reset]")
    private WebElement btnClear;

    @FindBy(css = "#name")
    private WebElement txtName;
    @FindBy(css = "#email")
    private WebElement txtEmail;
    @FindBy(css = "#subject")
    private WebElement txtSubject;
    @FindBy(css = "#comment")
    private WebElement txtComment;

    public ShopPage clearForm(){
        btnClear.click();
        return this;
    }

    public ShopPage fillForm(String ... data){
        txtName.clear();
        txtName.sendKeys(data[0]); //fill name

        txtEmail.clear();
        txtEmail.sendKeys(data[1]); //fill email

        txtSubject.clear();
        txtSubject.sendKeys(data[2]); //fill subject

        txtComment.clear();
        txtComment.sendKeys(data[3]); //fill comment

        return this;
    }

}
