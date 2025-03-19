package ru.project.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main {
    private final WebDriver driver;
    private final static String startPage = "https://stellarburgers.nomoreparties.site/";

    public Main(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginAccountButton = By.xpath(".//*[text() = 'Войти в аккаунт']"); //кнопка "Войти в аккаунт"
    private final By personalAreaButton = By.xpath(".//*[text() = 'Личный Кабинет']"); //кнопка "Личный Кабинет"
    private final By bunsButton = By.xpath(".//span[text()='Булки']/.."); //вкладка "Булки"
    private final By sauceButton = By.xpath("//span[text()='Соусы']/.."); //вкладка "Соусы"
    private final By fillingButton = By.xpath("//span[text()='Начинки']/.."); //вкладка "Начинки"
    private final By orderButton = By.className("button_button__33qZ0"); //кнопка "Оформить заказ"
    private final By bunElement = By.xpath(".//*/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By sauceElement = By.xpath(".//*/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By fillingElement = By.xpath(".//*/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    @Step("Открыть главную страницу")
    public void openStartPage (){
        driver.get(startPage);
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public void checkAuthorization() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginAccountButton));

        Object elementLoginAccountButton = driver.findElement(loginAccountButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLoginAccountButton);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginAccountButton));

        driver.findElement(loginAccountButton).click();
    }

    @Step("Клик по кнопке «Личный кабинет» на главной")
    public void checkPersonalArea() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(personalAreaButton));

        driver.findElement(personalAreaButton).click();
    }

    @Step("Загрузка главной страницы, отображение кнопки <Оформить заказ>")
    public Object checkOrderButton () {
        WebElement textButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));

        return textButton.getText();
    }

    @Step("Открытие вкладки с булками")
    public boolean checkBuns() {
        driver.findElement(fillingButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(bunsButton)));
        driver.findElement(bunsButton).click();
        return driver.findElement(bunElement).isDisplayed();
    }
    @Step("Открытие вкладки с соусами")
    public boolean checkSauce() {
        driver.findElement(sauceButton).click();
        return driver.findElement(sauceElement).isDisplayed();
    }

    @Step("Открытие вкладки с начинками")
    public boolean checkFillings() {
        driver.findElement(fillingButton).click();
        return driver.findElement(fillingElement).isDisplayed();
    }
}