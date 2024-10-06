package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeadPage {

    private WebDriver driver;
    private By buttonConstructor = By.xpath(".//p[contains(text(),'Конструктор')]"); // Локатор кнопки "Конструктор"
    private By buttonPersonalArea = By.xpath(".//p[contains(text(),'Личный Кабинет')]"); // Локатор кнопки "Личный кабинет"
    private By buttonSingIn = By.xpath(".//button[contains(text(),'Войти в аккаунт')]"); // Локатор кнопки "Войти в аккаунт"
    private By buttonBun = By.xpath(".//div[@style]/div[1]"); // Локатор кнопки "Булки"
    private By buttonFillings = By.xpath(".//div[@style]/div[3]"); // Локатор кнопки "Начинки"
    private By buttonSause = By.xpath(".//div[@style]/div[2]"); // Локатор кнопки "Соусы"
    private By listConstructor = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");
    private By headPage = By.xpath(".//main[@class='App_componentContainer__2JC2W']"); // Локатор главной страницы

    public HeadPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickButtonPersonalArea() {
        driver.findElement(buttonPersonalArea).click();
    }

    @Step("Клик по кнопке 'Войти' в середине сайта")
    public void clickButtonSingIn() {
        driver.findElement(buttonSingIn).click();
    }

    @Step("Метод отображения списка ингредиентов конструктора")
    public boolean isDisplayedConstructor() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(listConstructor));
        return driver.findElement(listConstructor).isDisplayed();
    }

    @Step("Метод отображения главной страницы")
    public boolean isDisplayedHeadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(headPage));
        return driver.findElement(headPage).isDisplayed();
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickButtonBun() {
        driver.findElement(buttonBun).click();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickButtonFillings() {
        driver.findElement(buttonFillings).click();
    }

    @Step("Клик по кнопке 'Соусы'")
    public void clickButtonSause() {
        driver.findElement(buttonSause).click();
    }

    @Step("Клик по кнопке 'Конструктор' в хэдере сайта")
    public void clickButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }

    @Step("Получить атрибут класса 'Булок'")
    public String getAtributBuns() {
        return driver.findElement(buttonBun).getAttribute("class");
    }

    @Step("Получить атрибут класса 'Соусы'")
    public String getAtributSause() {
        return driver.findElement(buttonSause).getAttribute("class");
    }

    @Step("Получить атрибут класса 'Начинки'")
    public String getAtributFillings() {
        return driver.findElement(buttonFillings).getAttribute("class");
    }
}
