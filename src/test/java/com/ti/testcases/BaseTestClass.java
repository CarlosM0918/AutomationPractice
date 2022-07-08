package com.ti.testcases;

import com.ti.base.BrowserType;
import com.ti.base.DriverFactory;
import com.ti.pages.ShopPage;
import com.ti.pages.MainPage;
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

    @BeforeTest
    @Parameters("browser")
     void setup(String browser) {
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser.toUpperCase()));
        DriverFactory.getInstance().getDriver().navigate().to(baseURL);

        inputData.put("product", "Dress");
        inputData.put("email", "test@test.com");
        inputData.put("invalidEmail", "testtest.com");
        inputData.put("subject", "Subject");
        inputData.put("comment", "Commentary");
        inputData.put("blankSpace", " ");
        inputData.put("specialCharacters", "!#$%&/()");

        mainPage = new MainPage();
        shopPage = new ShopPage();
    }

    @AfterTest
    void turnDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
