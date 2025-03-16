package ru.project;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Random;
import static io.restassured.RestAssured.given;

public class UserMoves {

    private final String email = "mymail" + new Random().nextInt(10000) + "@fakemails.com";
    private final String password = "987987" + new Random().nextInt(10000);
    private final String name = "Vasya" + new Random().nextInt(10000);

    public static RequestSpecification baseUri(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://stellarburgers.nomoreparties.site/api")
                .addFilter(new AllureRestAssured())
                .build();
    }

    public String getRandomName() {
        return this.name;
    }

    public  String getRandomEmail() {
        return this.email;
    }

    public  String getRandomPassword() {
        return this.password;
    }

    public static final String USER = "/auth/user";
    @Step("Удаление пользователя.")
    public static void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .spec(baseUri())
                .when()
                .delete(USER)
                .then();
    }
}