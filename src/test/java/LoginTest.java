import API_CreateUser.CreateUserRequest;
import POM.ForgetPasswordPage;
import POM.HeadPage;
import POM.LoginPage;
import POM.RegistrationPage;
import client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    UserClient userClient = new UserClient();
    WebDriver driver;
    private static final String URI = "https://stellarburgers.nomoreparties.site/";
    private String email;
    private String password;
    private String accessToken;

    @Before
    public void setUp() {
//        для тестов через Chrome
//        driver = new ChromeDriver();

//        Для тестов через Яндекс Браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        driver = new ChromeDriver();

        CreateUserRequest createUserRequest = new CreateUserRequest(RandomStringUtils.randomAlphabetic(6)+"@mail.ru",RandomStringUtils.randomAlphabetic(6),RandomStringUtils.randomAlphabetic(6));
        ValidatableResponse response = userClient.createUser(createUserRequest);
        email=createUserRequest.getEmail();
        password=createUserRequest.getPassword();
        accessToken=response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в аккаунт в середине сайта")
    public void logInButtonLogin() {
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        boolean isDisplayedHeadPage = objLoginPage.headPageIsDisplayed();
        Assert.assertTrue(isDisplayedHeadPage);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("После успешной авторизации должен произойти редирект на главную страницу")
    public void logInButtonPersonalArea(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonPersonalArea();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        boolean isDisplayedHeadPage = objLoginPage.headPageIsDisplayed();
        Assert.assertTrue(isDisplayedHeadPage);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' на странице регистрации")
    @Description("После успешной авторизации должен произойти редирект на главную страницу")
    public void logInButtonLoginRegistrationPage(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickButtonRegister();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.clickButtonLogin();
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        boolean isDisplayedHeadPage = objLoginPage.headPageIsDisplayed();
        Assert.assertTrue(isDisplayedHeadPage);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' на странице 'Восстановление пароля'")
    @Description("После успешной авторизации должен произойти редирект на главную страницу")
    public void loginFromForgotPasswordPage(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickButtonForgotPassword();
        ForgetPasswordPage objForgotPage = new ForgetPasswordPage(driver);
        objForgotPage.clickButtonLogin();
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        boolean isDisplayedHeadPage = objLoginPage.headPageIsDisplayed();
        Assert.assertTrue(isDisplayedHeadPage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        if (accessToken != null){
            userClient.deleteUser(accessToken);
        }
    }
}
