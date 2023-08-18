package FrameworkSimpleAndroid.pages;

import FrameworkSimpleAndroid.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductPage extends BaseTest {


    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Products\"]")
    private WebElement productPageTitle;




    public String getTitle(){

        return getText(productPageTitle);
    }
}
