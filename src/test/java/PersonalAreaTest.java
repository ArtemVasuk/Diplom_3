import API_CreateUser.CreateUserRequest;
import POM.HeadPage;
import POM.LoginPage;
import POM.PersonalAreaPage;
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

public class PersonalAreaTest {
    UserClient userClient = new UserClient();
    WebDriver driver;
    private static final String URI = "https://stellarburgers.nomoreparties.site/";
    private String email;
    private String password;
    private String accessToken;

    @Before
    public void setUp(){
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
    @DisplayName("Переход в 'Личный кабинет'")
    @Description("Должна отобразиться страница Личного кабинета")
    public void goToPersonalAccountOnHeadPage(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        objHeadPage.clickButtonPersonalArea();
        PersonalAreaPage objPersonalArea = new PersonalAreaPage(driver);
        boolean isDisplayedMessageInPersonalArea = objPersonalArea.isDisplayedMessage();
        Assert.assertTrue(isDisplayedMessageInPersonalArea);
    }

    @Test
    @DisplayName("Переход на главную страницу в раздел 'Конструктор' через кнопку 'Конструктор' в хэдере сайта")
    @Description("Должен отобразиться конструктор Главной страницы")
    public void goToConstructorInPersonalAreaPage(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        objHeadPage.clickButtonPersonalArea();
        PersonalAreaPage objPersonalArea = new PersonalAreaPage(driver);
        objPersonalArea.clickButtonConstructor();
        boolean isDisplayedConstructor = objHeadPage.isDisplayedConstructor();
        Assert.assertTrue(isDisplayedConstructor);
    }

    @Test
    @DisplayName("Переход на главную страницу через кнопку Логотипа в хэдере сайта")
    @Description("Должна отобразиться Главная страница")
    public void goToLogoHeadInPersonalAreaPage(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        objHeadPage.clickButtonPersonalArea();
        PersonalAreaPage objPersonalArea = new PersonalAreaPage(driver);
        objPersonalArea.clickButtonLogoInHeader();
        boolean isDisplayedHeadPage = objHeadPage.isDisplayedHeadPage();
        Assert.assertTrue(isDisplayedHeadPage);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Должна отобразиться страница входа")
    public void LogoutAccount(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonSingIn();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.sendFieldEmail(email);
        objLoginPage.sendFieldPassword(password);
        objLoginPage.clickButtonLogin();
        objHeadPage.clickButtonPersonalArea();
        PersonalAreaPage objPersonalArea = new PersonalAreaPage(driver);
        objPersonalArea.clickButtonLogout();
        boolean isDisplayedPageLogin = objLoginPage.isDisplayedButtonLogin();
        Assert.assertTrue(isDisplayedPageLogin);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }
}
