import ru.project.NewUser;
import ru.project.Token;
import ru.project.pageobject.Login;
import ru.project.pageobject.Main;
import org.openqa.selenium.WebDriver;
import org.junit.Before;
import ru.project.UserMoves;
import ru.project.pageobject.Registration;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.project.pageobject.Account;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import ru.project.MakeWebDriver;


public class AccountTest {
    private Login objLogin;
    private Main objMain;
    private WebDriver driver;
    String accessToken;
    NewUser user;

    @Before
    public void before() {
        driver = MakeWebDriver.createWebDriver();
        UserMoves userMoves = new UserMoves();
        String email = userMoves.getRandomEmail();
        String password = userMoves.getRandomPassword();
        String name = userMoves.getRandomName();
        Registration objRegistration = new Registration(driver);
        objRegistration.openRegisterPage();
        objRegistration.createUser(email, password, name);
        objLogin = new Login(driver);
        objMain = new Main(driver);
        objLogin.login(email, password); //тут выбрасывает на главную
        objMain.checkPersonalArea();
        user = new NewUser(email, password, name);
        accessToken = Token.receivingToken(user);
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void personalAccountTest() {
        Account objAccount = new Account(driver);
        assertEquals("Entering was  Failed", "Выход", objAccount.checkLogInPersonalAccount());
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void checkConstructorTest() {
        Account objAccount = new Account(driver);
        objAccount.clickConstructorButton();
        assertEquals("ConstructorButtonFailed", "Оформить заказ", objMain.checkOrderButton());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на лого «Stellar Burgers»")
    public void checkLogoTest() {
        Account objAccount = new Account(driver);
        objAccount.clickLogoButton();
        assertEquals("LogoButtonFailed", "Оформить заказ", objMain.checkOrderButton());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void checkExitTest() {
        Account objAccount = new Account(driver);
        objAccount.clickExitButton();
        assertEquals("ExitFailed", "Войти", objLogin.checkLoginButton());
    }

    @After
    public void teardown() {
        if (accessToken != null) {
            UserMoves.deleteUser(accessToken);
        }
        driver.quit();
    }
}