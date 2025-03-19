package ru.project.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    private final WebDriver driver;
    public Login(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input"); //поле Email
    private final By passwordField = By.xpath("//input[@type='password']"); //поле Пароль
    private final By loginButton = By.xpath(".//button [text()='Войти']"); //кнопка "Войти"

    @Step("Пройти авторизацию")
    public void login (String email, String password){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ignored){
        }
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();

    }
    @Step("Проверка кнопки <Войти>")
    public String checkLoginButton () {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return driver.findElement(loginButton).getText();
    }
}