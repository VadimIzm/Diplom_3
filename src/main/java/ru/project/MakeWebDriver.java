package ru.project;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MakeWebDriver {

    public static ChromeDriver createWebDriver() {

        String browserName = getProperty("browser");
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
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/Vadim/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        return new ChromeDriver(options);
    }

    //извлечение из конфига
    private static Properties properties = new Properties();
    static {
        try (FileInputStream file = new FileInputStream("C:/Users/Vadim/Diplom_Vadim_Izimariev_13a/Diplom_3/src/main/resources/config.properties")) {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    //извлечение из конфига
}