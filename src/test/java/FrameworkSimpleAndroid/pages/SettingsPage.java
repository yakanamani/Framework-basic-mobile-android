package FrameworkSimpleAndroid.pages;

import FrameworkSimpleAndroid.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BaseTest {
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"store item\"])[3]/android.view.ViewGroup[1]/android.widget.ImageView")
    private WebElement loginMenuOption;

    public LoginPage chooseLoginOption(){
        click(loginMenuOption);
        return new LoginPage();
    }
}
