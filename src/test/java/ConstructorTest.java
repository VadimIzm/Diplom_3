import ru.project.pageobject.Main;
import org.openqa.selenium.WebDriver;
import org.junit.Before;
import ru.project.MakeWebDriver;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;

import static org.junit.Assert.assertTrue;
public class ConstructorTest {
    private Main objMain;
    private WebDriver driver;

    @Before
    public void before() {
        driver = MakeWebDriver.createWebDriver();
        objMain = new Main(driver);
        objMain.openStartPage();
    }

    @Test
    @DisplayName("Открыть вкладку «Соусы»")
    public void checkSauce() {
        assertTrue("Ошибка", objMain.checkSauce());
    }

    @Test
    @DisplayName("Открыть вкладку «Булки»")
    public void checkBuns() {
        assertTrue("Ошибка", objMain.checkBuns());
    }

    @Test
    @DisplayName("Открыть вкладку «Начинки»")
    public void checkFillings() {
        assertTrue("Ошибка", objMain.checkFillings());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}