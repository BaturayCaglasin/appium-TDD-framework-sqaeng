package com.qa.tests;

import com.google.gson.stream.JsonToken;
import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;
    InputStream details;
    JSONObject loginUsers;

    @BeforeClass

    public void beforeClass() {
        String dataFileName = "data/loginUsers.json";
        details = getClass().getClassLoader().getResourceAsStream(dataFileName);
        JSONTokener tokener = new JSONTokener(details);
        loginUsers = new JSONObject(tokener);

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("******************* starting the test as: **************************" + m.getName());
    }

    @AfterMethod
    public void afterMethod() {
    }

    //this test contains invalid username correct password
    @Test
    public void testCode0001() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrorTxt();
        String expectedErrTxt =strings.get("err_invalid_username_or_password");

        System.out.println("Actual error txt - " + actualErrTxt);
        Assert.assertEquals(actualErrTxt, expectedErrTxt);

    }

}
