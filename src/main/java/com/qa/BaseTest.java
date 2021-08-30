package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> strings = new HashMap<String, String>();
    InputStream inputStream;
    InputStream stringsis;
    TestUtils utils;


    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest

    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {

        try {
            props = new Properties();
            String propFileName = "config.properties";
            String xmlFileName = "Strings/strings.xml";


            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);


            stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            utils = new TestUtils();
            strings = utils.parseStringXML(stringsis);

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", platformName);
            desiredCapabilities.setCapability("platformVersion", platformVersion);
            desiredCapabilities.setCapability("deviceName", deviceName);
            desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
            desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
            desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
            //desiredCapabilities.setCapability("avd", "Pixel_2");
            URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
            desiredCapabilities.setCapability("app", appUrl);

            URL url = new URL(props.getProperty("appiumURL"));
            driver = new AndroidDriver(url, desiredCapabilities);
            String sessionId = driver.getSessionId().toString();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (stringsis != null) {
                stringsis.close();
            }

        }

    }

    public void waitforVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));

    }

    public void click(MobileElement e) {
        waitforVisibility(e);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt) {
        waitforVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitforVisibility(e);
        return e.getAttribute(attribute);
    }


    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
