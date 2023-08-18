package FrameworkSimpleAndroid.tests;

import FrameworkSimpleAndroid.base.BaseTest;
import FrameworkSimpleAndroid.pages.LoginPage;
import FrameworkSimpleAndroid.pages.MenuPage;
import FrameworkSimpleAndroid.pages.ProductPage;
import FrameworkSimpleAndroid.pages.SettingsPage;
import jdk.jshell.execution.Util;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductPage productPage;
    MenuPage menuPage;
    SettingsPage settingsPage;
    InputStream datais;
    JSONObject loginUsers;
    String credentialUsername;
    String credentialPassword;



    @BeforeClass
    public void beforeClass() throws IOException {
        try {
            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if (datais != null) {
                datais.close();
            }
        }

    }
    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        productPage = new ProductPage() ;

    }
    @AfterMethod
    public void tearDown() {

    }

    @AfterClass
    public void afterClass() throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows){
            Runtime.getRuntime().exec(String.format("cmd.exe /c allure generate --clean Test-output/allure-results -o Test-output/allure-report"));
        } else {
            Runtime.getRuntime().exec("allure generate --clean Test-output/allure-results -o Test-output/allure-report");
        }
    }
    @Test(priority = 0)
    public void invalidUsername() {
        System.out.println("Scenario : User enters invalid username");
        menuPage = new MenuPage();
        settingsPage =new SettingsPage();
        System.out.println("Opening Drawer Menu");
        menuPage.openDrawerMenu();
        System.out.println("Opening Login page");
        settingsPage.chooseLoginOption();
        System.out.println("Enter invalid credentials: invalid username");
        loginPage.enterUsername(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        System.out.println("Pressing login button");
        loginPage.pressLoginBtn();
        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = strings.get("err_invalid_username_or_password");
        System.out.println("Checking error message : " + actualErrorText);
        Assert.assertEquals(actualErrorText, expectedErrorText);
        System.out.println("Test successful");

    }

    @Test(priority = 1)
    public void invalidPassword() {
        System.out.println("Scenario : User enters invalid password");
        System.out.println("Enter invalid credentials: invalid password");
        loginPage.enterUsername(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        System.out.println("Pressing login button");
        loginPage.pressLoginBtn();
        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Provided credentials do not match any user in this service.";
        System.out.println("Checking error message : " + actualErrorText);
        Assert.assertEquals(actualErrorText, expectedErrorText);
        System.out.println("Test successful");



    }

    @Test(priority = 2)
    public void successfullLogin() {
        System.out.println("Scenario : user enters good credentials");
        System.out.println("Enter valid credentials");
        loginPage.enterUsername(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        System.out.println("Pressing login button");
        loginPage.pressLoginBtn();
        String actualHomePageTitle = productPage.getTitle();
        String expectedHomePageTitle = strings.get("product_title");
        System.out.println("Checking if home page is visible");
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
        System.out.println("Test successful");

    }

}
