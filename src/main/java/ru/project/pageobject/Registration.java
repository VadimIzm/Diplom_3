package ru.project.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registration {

    private final WebDriver driver;

    public Registration(WebDriver driver) {
        this.driver = driver;
    }
    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::div/input");//поле Имя
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input"); //поле Email
    private final By passwordField =  By.xpath(".//input[@name = 'Пароль']"); //поле Пароль
    private final By registerButton = By.xpath(".//*[text() = 'Зарегистрироваться']");//кнопка "Зарегистрироваться"
    private final By error = By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]"); //ошибка
    private final By authLinkLogin = By.xpath(".//a[text() = 'Войти']"); //кнопка "Войти"

    @Step("Открыть страницу <Регистрация>")
    public void openRegisterPage (){
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Создать пользователя")
    public void createUser(String email, String password, String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Выполнить вход по кнопке <Войти> в форме регистрации")
    public void clickAuthLinkLogin (){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(authLinkLogin));
        driver.findElement(authLinkLogin).click();
    }

    @Step("Проверить текст ошибки для некорректного пароля")
    public String getTextErrorMessage () {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(error));
        return driver.findElement(error).getText();
    }
}