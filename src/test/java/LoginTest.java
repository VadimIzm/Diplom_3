import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.project.*;
import ru.project.pageobject.Login;
import ru.project.pageobject.Main;
import ru.project.pageobject.PasswordRecovery;
import ru.project.pageobject.Registration;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private Registration objRegistration;
    private Login objLogin;
    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    String accessToken;
    UserApi userApi;
    NewUser user, newUser;

    @Before
    public void before() {
        userApi = new UserApi();
        driver = MakeWebDriver.createWebDriver();
        UserMoves userMoves = new UserMoves();
        email = userMoves.getRandomEmail();
        password = userMoves.getRandomPassword();
        name = userMoves.getRandomName();
        objRegistration = new Registration(driver);
        newUser = objRegistration.createEmptyUser(email, password, name);
        userApi.createUser(newUser);
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void startPageTest() {
        Main objMain = new Main(driver);
        objMain.openStartPage();
        objMain.checkAuthorization();
        objLogin = new Login(driver);
        objLogin.login(email, password);
        user = new NewUser(email, password, name);
        userApi = new UserApi();
        Response response = userApi.loginUser(user);
        userApi.checkLoginResponse(response);
        assertEquals("Ошибка", "Оформить заказ", objMain.checkOrderButton());
    }
    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void personalAccountTest() {
        Main objMain = new Main(driver);
        objMain.openStartPage();
        objMain.checkPersonalArea();
        objLogin = new Login(driver);
        objLogin.login(email, password);
        user = new NewUser(email, password, name);
        userApi = new UserApi();
        Response response = userApi.loginUser(user);
        userApi.checkLoginResponse(response);
        assertEquals("Ошибка", "Оформить заказ", objMain.checkOrderButton());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void registrationLinkTest() {
        Main objMain = new Main(driver);
        objRegistration.openRegisterPage();
        objRegistration.clickAuthLinkLogin();
        Login objLogin = new Login(driver);
        objLogin.login(email, password);
        user = new NewUser(email, password, name);
        userApi = new UserApi();
        Response response = userApi.loginUser(user);
        userApi.checkLoginResponse(response);
        assertEquals("Ошибка", "Оформить заказ", objMain.checkOrderButton());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void restorePasswordLinkTest() {
        PasswordRecovery objPasswordRecovery = new PasswordRecovery(driver);
        objPasswordRecovery.openRestorePage();
        objPasswordRecovery.clickForgotPassword();
        Login objLogin = new Login(driver);
        objLogin.login(email,password);
        Main objMain = new Main(driver);
        user = new NewUser(email, password, name);
        userApi = new UserApi();
        Response response = userApi.loginUser(user);
        userApi.checkLoginResponse(response);
        assertEquals("Ошибка", "Оформить заказ", objMain.checkOrderButton());
    }
    @After
    public void teardown() {
        accessToken = Token.receivingToken(user);
        if (accessToken != null) {
            UserMoves.deleteUser(accessToken);
        }
        driver.quit();
    }
}