package ru.project.pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Account {

    private final WebDriver driver;
    public Account(WebDriver driver) {
        this.driver = driver;
    }
    private final By exitButton = By.xpath(".//button [text()='Выход']"); //кнопка "Выход"
    private final By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/' ]");  //логотип "Stellar burgers"
    private final By constructorButton = By.xpath("//a[@href='/' and @class='AppHeader_header__link__3D_hX']"); //кнопка "Конструктор"

    @Step("Выход из личного кабинета по кнопке <Выход>")
    public void clickExitButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Перейти на стартовую страницу по логотипу <Stellar burgers>")
    public void clickLogoButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(logoButton));
        driver.findElement(logoButton).click();
    }

    @Step("Перейти на стартовую страницу в раздел <Конструктор> по кнопке <Конструктор>")
    public void clickConstructorButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Проверка кнопки <Выход>")
    public String checkLogInPersonalAccount() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        return driver.findElement(exitButton).getText();
    }
}