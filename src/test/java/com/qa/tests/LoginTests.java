package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass

    public void beforeClass() {

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

    //this test contains invalid username
    @Test
    public void testCode0001() {
        loginPage.enterUserName("exampleUsername");
        loginPage.enterPassword("Test*01");
        loginPage.pressLoginBtn();

        String actualErrTxt=loginPage.getErrorTxt();
        String expectedErrTxt="Username and password do not match any user in this service.";

        System.out.println("Actual error txt - "+ actualErrTxt);
        Assert.assertEquals(actualErrTxt,expectedErrTxt);

    }

}
