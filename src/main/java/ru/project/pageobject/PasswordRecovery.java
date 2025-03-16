package ru.project.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecovery {

    private final WebDriver driver;
    public PasswordRecovery(WebDriver driver) {
        this.driver = driver;
    }
    private final static String PasswordRecoveryPage = "https://stellarburgers.nomoreparties.site/login";
    private final By restorePasswordButton = By.xpath(".//a[text()='Восстановить пароль']"); //кнопка "Восстановить пароль"
    private final By loginButton = By.xpath(".//a[@class='Auth_link__1fOlj']"); //кнопка "Войти" на странице "Восстановление пароля"
    @Step("Открыть страницу <Восстановление пароля>")
    public void openRestorePage (){
        driver.get(PasswordRecoveryPage);
    }

    @Step("Выполнить вход по кнопке <Войти> в форме восстановления пароля")
    public void clickForgotPassword (){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(restorePasswordButton));
        driver.findElement(restorePasswordButton).click();
        driver.findElement(loginButton).click();
    }
}