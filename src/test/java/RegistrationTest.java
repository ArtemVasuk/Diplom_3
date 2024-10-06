import POM.HeadPage;
import POM.LoginPage;
import POM.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    WebDriver driver ;
    private static final String URI = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
//        для тестов через Chrome
//        driver = new ChromeDriver();

//        Для тестов через Яндекс Браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName("Регистрация с корректными данными")
    @Description("Должна отобразиться страница для входа")
    public void ShouldBeRegistrationUser(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonPersonalArea();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickButtonRegister();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.sendName(RandomStringUtils.randomAlphanumeric(8));
        objRegistrationPage.sendEmail(RandomStringUtils.randomAlphabetic(8)+"@mail.ru");
        objRegistrationPage.sendPassword(RandomStringUtils.randomAlphabetic(6));
        objRegistrationPage.clickButtonRegistration();
        boolean isVisibleLoginPage = objRegistrationPage.visibleLoginPage();
        Assert.assertTrue(isVisibleLoginPage);
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Description("Должно отобразиться сообщение 'Некорректный пароль'")
    public void ShouldBeRegistrationNotCorrectPassword(){
        driver.get(URI);
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonPersonalArea();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickButtonRegister();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.sendName(RandomStringUtils.randomAlphanumeric(8));
        objRegistrationPage.sendEmail(RandomStringUtils.randomAlphabetic(8)+"@mail.ru");
        objRegistrationPage.sendPassword(RandomStringUtils.randomAlphabetic(5));
        objRegistrationPage.clickButtonRegistration();
        boolean isVisibleMessage = objRegistrationPage.visibleMessageNotCorrectPassword();
        Assert.assertTrue(isVisibleMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
