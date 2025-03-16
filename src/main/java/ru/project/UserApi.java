package ru.project;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi extends Endpoints{

    @Step("Логин пользователя через API")
    public Response loginUser(NewUser user) {
        return given()
                .spec(baseUri())
                .when()
                .and()
                .body(user)
                .post(loginUser());
    }
}