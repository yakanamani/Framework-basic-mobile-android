package FrameworkSimpleAndroid.pages;

import FrameworkSimpleAndroid.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {
    @AndroidFindBy (accessibility = "Username input field")
    private WebElement usernameTextField;
    @AndroidFindBy (accessibility = "Password input field")
    private WebElement passwordTextField;
    @AndroidFindBy (accessibility = "Login button")
    private WebElement loginBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]")
    private WebElement credentialsErrorMessage;


    public LoginPage enterUsername(String username) {
        clear(usernameTextField);
        sendKey(usernameTextField, username);
        return this;
    }
    public LoginPage enterPassword(String password) {
        clear(passwordTextField);
        sendKey(passwordTextField, password);
        return this;
    }

    public ProductPage pressLoginBtn(){
        click(loginBtn);
        return new ProductPage();
    }

    public String getErrorText(){

        return getText(credentialsErrorMessage);
    }


}
