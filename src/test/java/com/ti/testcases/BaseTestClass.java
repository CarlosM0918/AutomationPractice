package com.ti.testcases;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ti.base.BrowserType;
import com.ti.base.DriverFactory;
import com.ti.pages.ContactPage;
import com.ti.pages.ShopPage;
import com.ti.pages.MainPage;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class BaseTestClass {
    String baseURL = "http://automationpractice.com/";
    Map<String, String> inputData = new HashMap<>();
    MainPage mainPage;
    ShopPage shopPage;
    ContactPage contactPage;
    ExtentReports extent;
    ExtentSparkReporter tiSpark;

    @BeforeTest(groups = {"Bug", "Smoke"})
    @Parameters({"browser", "config", "environment" })
     void setup(String browser, @Optional("IamOptional") String config_file, @Optional("IamOptional") String environment) throws Exception {
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser.toUpperCase()), config_file, environment);
        DriverFactory.getInstance().getDriver().manage().deleteAllCookies();
        DriverFactory.getInstance().getDriver().navigate().to(baseURL);

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
        extent = new ExtentReports();
        tiSpark = new ExtentSparkReporter("TIReport.html");
        extent.attachReporter(tiSpark);
    }

    @AfterTest(groups = {"Smoke", "Bug"})
    void turnDown() throws Exception {
        DriverFactory.getInstance().removeDriver();
        extent.flush();
    }
}
