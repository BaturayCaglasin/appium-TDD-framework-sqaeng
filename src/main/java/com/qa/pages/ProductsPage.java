package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BaseTest {
    @AndroidFindBy(accessibility = "test_LOGIN")
    private MobileElement loginBtn;
    @AndroidFindBy(xpath = "")
    private MobileElement productTitleTxt;


    public String getTitle() {
        return getAttribute(productTitleTxt, "text");
    }


}
