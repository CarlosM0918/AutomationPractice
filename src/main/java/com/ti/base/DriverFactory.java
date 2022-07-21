package com.ti.base;

import com.browserstack.local.Local;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DriverFactory {
    private String getOS = System.getProperty("os.name").toLowerCase();
    private String driverProperty = "webdriver.chrome.driver";
    private String driverPath = System.getProperty("user.dir") + "/src/main/resources/";
    private String driverName = (getOS.contains("mac"))?"chromedriver" : "chromedriver.exe";
    private String braveLocation = "C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe";
    private Local l;


    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    public void setDriver(BrowserType browserType, String config_file, String environment) throws Exception {
        System.out.println(getOS);
        System.out.println(config_file);
        System.out.println(environment);
        if(getOS.contains("mac") && browserType.equals(BrowserType.SAFARI)){
            driver.set(new SafariDriver());
        }else if(browserType.equals(BrowserType.BRAVE)){
            System.setProperty(driverProperty, driverPath+driverName);
            driver.set(new ChromeDriver(new ChromeOptions().setBinary(braveLocation)));
        }
        else if(browserType.equals(BrowserType.BROWSERSTACK)){
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/main/resources/conf/" + config_file));
            JSONObject envs = (JSONObject) config.get("environments");

            DesiredCapabilities capabilities = new DesiredCapabilities();

            Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue());
            }

            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue());
                } else if (pair.getKey().toString().equalsIgnoreCase("bstack:options")) {
                    HashMap bstackOptionsMap = (HashMap) pair.getValue();
                    bstackOptionsMap.putAll((HashMap) capabilities.getCapability("bstack:options"));
                    capabilities.setCapability(pair.getKey().toString(), bstackOptionsMap);
                }
            }

            String username = System.getenv("BROWSERSTACK_USERNAME");
            if (username == null) {
                username = (String) config.get("user");
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if (accessKey == null) {
                accessKey = (String) config.get("key");
            }

            if (capabilities.getCapability("bstack:options") != null) {
                HashMap bstackOptionsMap = (HashMap) capabilities.getCapability("bstack:options");
                if ((bstackOptionsMap.get("local") != null && bstackOptionsMap.get("local").toString().equalsIgnoreCase("true")) || (capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local").toString().equalsIgnoreCase("true"))) {
                    l = new Local();
                    Map<String, String> options = new HashMap<String, String>();
                    options.put("key", accessKey);
                    l.start(options);
                }
            }

            driver.set(new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities));
        }
        else{
            WebDriverManager.getInstance(DriverManagerType.valueOf(browserType.toString())).setup();
            switch (browserType){
                case CHROME:
                    driver.set(new ChromeDriver());
                    break;
                case EDGE:
                    driver.set(new EdgeDriver());
                    break;
                case FIREFOX:
                    driver.set(new FirefoxDriver());
                    break;
                case IEXPLORER:
                    if(getOS.contains("win")){
                        driver.set(new InternetExplorerDriver());
                    }
                    break;
                default:
                    System.out.println("No browser selected");
            }

            driver.get().manage().window().maximize();

        }
    }

    public void removeDriver() throws Exception {
        if(driver.get() != null){
            try {
                driver.get().quit();
                driver.remove();
            }catch (Exception e){
                System.err.println("Unable to quit");
            }
        }
        if (l != null) {
            l.stop();
        }
    }
}
