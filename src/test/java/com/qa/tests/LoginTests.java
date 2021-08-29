package com.qa.tests;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.annotations.*;

public class LoginTests {
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass

    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
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

    }

}
