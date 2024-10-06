package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgetPasswordPage {
    private WebDriver driver;
    private By buttonLogin = By.xpath(".//a[@href='/login']");//локатор кнопки Войти

    public ForgetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Войти")
    public void clickButtonLogin () {
        driver.findElement(buttonLogin).click();
    }
}
