package com.ti.testcases;

import com.ti.base.BrowserType;
import com.ti.base.DriverFactory;
import com.ti.pages.ContactPage;
import com.ti.pages.ShopPage;
import com.ti.pages.MainPage;
import org.bouncycastle.util.test.TestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;

public class BaseTestClass {
    String baseURL = "http://automationpractice.com/";
    Map<String, String> inputData = new HashMap<>();
    MainPage mainPage;
    ShopPage shopPage;
    ContactPage contactPage;

    @BeforeTest
    @Parameters("browser")
     void setup(String browser) throws InterruptedException {
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser.toUpperCase()));
        DriverFactory.getInstance().getDriver().navigate().to(baseURL);
        DriverFactory.getInstance().getDriver().manage().deleteAllCookies();
//        Thread.sleep(10000);

        inputData.put("product", "Dress");
        inputData.put("subjectText", "Customer service");
        inputData.put("orderId", "12365");
        inputData.put("message", "Commentary");
        inputData.put("email", "test@test.com");

        inputData.put("invalidEmail", "testtest.com");
        inputData.put("blankSpace", " ");
        inputData.put("specialCharacters", "!#$%&/()");

        mainPage = new MainPage();
        shopPage = new ShopPage();
        contactPage = new ContactPage();
    }

    @AfterTest
    void turnDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
