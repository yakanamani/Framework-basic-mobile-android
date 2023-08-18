package FrameworkSimpleAndroid.pages;

import FrameworkSimpleAndroid.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BaseTest {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")
    private WebElement openMenuDrawer;

    public SettingsPage openDrawerMenu() {
        click(openMenuDrawer);
        return new SettingsPage();
    }
}
