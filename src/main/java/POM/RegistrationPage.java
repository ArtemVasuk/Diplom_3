package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private By fieldName= By.xpath(".//label[contains(text(),'Имя')]/../input"); // Локатор поля 'Имя'
    private By fieldEmail=By.xpath("//label[contains(text(),'Email')]/../input"); // Локатор поля 'Email'
    private By fieldPassword= By.xpath(".//label[contains(text(),'Пароль')]/../input"); // Локатор поля 'Пароль'
    private By buttonRegistration= By.xpath(".//button[contains(text(),'Зарегистрироваться')]"); // Локатор кнопки 'Зарегистрироваться'
    private By buttonLogin=By.xpath(".//a[@href='/login']"); // Локатор кнопки 'Войти'
    private By messageNotCorrectPassword= By.xpath(".//p[contains(text(),'Некорректный пароль')]"); // Локатор сообщения 'Некорректный пароль'
    private By buttonHeaderLogo = By.className("AppHeader_header__logo__2D0X2"); // Локатор лого
    private By pageLogin= By.xpath(".//main[@class='App_componentContainer__2JC2W']"); // Локатор главной страницы

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод Имени в поле 'Имя'")
    public void sendName(String name){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldName));
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Ввод e-mail в поле 'Email'")
    public String sendEmail(String email){
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
        return email;
    }

    @Step("Ввод пароля в поле 'Пароль'")
    public String sendPassword(String password){
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
        return password;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickButtonRegistration(){
        driver.findElement(buttonRegistration).click();
    }

    @Step("Проверка отображения страницы входа")
    public boolean visibleLoginPage(){
        return (driver.findElement(pageLogin).isDisplayed());
    }

    @Step("Проверка отображения сообщения 'Некорректный пароль'")
    public boolean visibleMessageNotCorrectPassword(){
        return (driver.findElement(messageNotCorrectPassword).isDisplayed());
    }

    @Step("Клик по лого сайта для перехода на главную страницу")
    public void clickLogoSite(){
        driver.findElement(buttonHeaderLogo).click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin(){
        driver.findElement(buttonLogin).click();
    }
}
