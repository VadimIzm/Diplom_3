package ru.project;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakeWebDriver {

    public static ChromeDriver createWebDriver() {
        String browserName = "YANDEX";
        switch (browserName) {
            case "YANDEX":
                return createYandexDriver();
            case "CHROME":
            default:
                return createChromeDriver();
        }
    }

    public static ChromeDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    public static ChromeDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Vadim/Diplom_Vadim_Izimariev_13a/Diplom_3/src/main/resources/yandexdriver.exe");
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Vadim/Diplom_Vadim_Izimariev_13a/Diplom_3/src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/Vadim/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        return new ChromeDriver(options);
    }
}