package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAreaPage {
    private WebDriver driver;
    private By messageInPagePersonalArea=By.xpath(".//p[contains(text(),'В этом разделе вы можете изменить свои персональные данные')]");
    private By buttonConstructor =By.xpath(".//p[contains(text(),'Конструктор')]"); // Локатор кнопки 'Конструктор'
    private By buttonHeaderLogo= By.className("AppHeader_header__logo__2D0X2"); // Локатор логотипа
    private By buttonLogout= By.xpath(".//button[contains(text(),'Выход')]"); // Локатор кнопки 'Выход'

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Отображение сообщения в Личном кабинете для проверки перехода в личный кабинет")
    public boolean isDisplayedMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(messageInPagePersonalArea));
        return driver.findElement(messageInPagePersonalArea).isDisplayed();
    }

    @Step("Клик по кнопке 'Конструктор' в хэдере сайта")
    public void clickButtonConstructor(){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(messageInPagePersonalArea));
        driver.findElement(buttonConstructor).click();
    }

    @Step("Клик по кнопке 'Логотипа' в хэдере сайта")
    public void clickButtonLogoInHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(messageInPagePersonalArea));
        driver.findElement(buttonHeaderLogo).click();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickButtonLogout(){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLogout));
        driver.findElement(buttonLogout).click();
    }
}
