package ru.project;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApi extends Endpoints{

    @Step("Отправка запроса на логин пользователя через API")
    public Response loginUser(NewUser user) {
        return given()
                .spec(baseUri())
                .when()
                .and()
                .body(user)
                .post(loginUser());
    }

    @Step("Отправка запроса на создание пользователя через API")
    public Response createUser(NewUser user){
        return given()
                .spec(baseUri())
                .and()
                .body(user)
                .when()
                .post(authRegister());
    }

    @Step("Проверка ответа при успешной регистрации/авторизации")
    public void checkLoginResponse(Response response){
        response.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }
}