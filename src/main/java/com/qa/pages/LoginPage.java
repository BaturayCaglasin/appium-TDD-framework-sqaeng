package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    @AndroidFindBy(accessibility = "test_Username")
    private MobileElement usernameTxtFld;
    @AndroidFindBy(accessibility = "test_Password")
    private MobileElement passwordTxtFld;
    @AndroidFindBy(accessibility = "test_LOGIN")
    private MobileElement loginBtn;
    @AndroidFindBy(xpath = "")
    private MobileElement errTxt;

    public LoginPage enterUserName(String username) {
        sendKeys(usernameTxtFld, username);
        return this;

    }
    public LoginPage enterPassword(String password) {
        sendKeys(passwordTxtFld, password);
        return this;
    }

    public ProductsPage pressLoginBtn() {
        click(loginBtn);
        return new ProductsPage();
    }

    public String getErrorTxt() {
        return getAttribute(errTxt, "text");
    }


}
