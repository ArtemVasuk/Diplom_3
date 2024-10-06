package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private By fieldEmail = By.xpath(".//input[@type='text']"); // Локатор поля 'Email'
    private By fieldPassword = By.xpath(".//input[@type='password']"); // Локатор поля 'Пароль'
    private By buttonLogIn = By.xpath(".//button[contains(text(),'Войти')]"); // Локатор кнопки 'Войти'
    private By buttonRegister = By.xpath(".//a[contains(text(),'Зарегистрироваться')]"); // Локатор кнопки 'Зарегистрироваться'
    private By buttonForgotPassword = By.xpath(".//a[@href='/forgot-password']"); // Локатор кнопки "Восстановление пароля"
    private By headPage = By.xpath(".//main[@class='App_componentContainer__2JC2W']"); //Локатор главной страницы

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Зарегистрироваться")
    public void clickButtonRegister() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonRegister));
        driver.findElement(buttonRegister).click();
    }

    @Step("Заполнение поля 'Email'")
    public void sendFieldEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Заполнение поля 'Пароль'")
    public void sendFieldPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldPassword));
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogIn).click();
    }

    @Step("Отображение главной страницы после авторизации")
    public boolean headPageIsDisplayed(){
        return driver.findElement(headPage).isDisplayed();
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickButtonForgotPassword(){
        driver.findElement(buttonForgotPassword).click();
    }

    @Step("Отображения кнопки 'Войти' на странице авторизации")
    public boolean isDisplayedButtonLogin(){
        new WebDriverWait(driver,Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLogIn));
        return driver.findElement(buttonLogIn).isDisplayed();
    }
}
