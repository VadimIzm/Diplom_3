import io.restassured.response.Response;
import ru.project.NewUser;
import ru.project.Token;
import ru.project.pageobject.Login;
import ru.project.pageobject.Registration;
import org.openqa.selenium.WebDriver;
import org.junit.Before;
import ru.project.MakeWebDriver;
import ru.project.UserMoves;
import ru.project.UserApi;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import static org.junit.Assert.assertEquals;
import org.junit.After;

public class RegistrationTest {
    UserApi userApi;
    NewUser user;
    String accessToken;
    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    Registration registration;

    @Before
    public void before() {
        driver = MakeWebDriver.createWebDriver();
        UserMoves userMoves = new UserMoves();
        name = userMoves.getRandomName();
        email = userMoves.getRandomEmail();
        password = userMoves.getRandomPassword();
        registration = new Registration(driver);
        registration.openRegisterPage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkRegistrationTest() {
        user = new NewUser(email, password, name);
        userApi = new UserApi();
        registration.createUser(email, password, name); //формируем набор данных, вводим их в поля и жмем кнопку "Зарегестрироваться"
        Response response = userApi.loginUser(user);
        userApi.checkLoginResponse(response);
        Login login = new Login(driver);
        assertEquals("ExitFailed", "Войти", login.checkLoginButton());
        accessToken = Token.receivingToken(user);
    }

    @Test
    @DisplayName("Вывод ошибки при вводе некорректного пароля")
    public void checkErrorMassageTest() {
        registration.createUser(email, "12345", name);
        String actualErrorMessage = registration.getTextErrorMessage();
        assertEquals("Текст сообщения об ошибке не соответствует ожидаемому", "Некорректный пароль", actualErrorMessage);
    }

    @After
    public void teardown() {
        if (accessToken != null) {
            UserMoves.deleteUser(accessToken);
        }
        driver.quit();
    }
}