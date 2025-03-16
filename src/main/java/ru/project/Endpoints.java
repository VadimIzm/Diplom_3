package ru.project;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Endpoints {

    public static RequestSpecification baseUri(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://stellarburgers.nomoreparties.site/api")
                .addFilter(new AllureRestAssured())
                .build();
    }
    public static String authRegister() {
        return "/auth/register";
    }
    public static String loginUser() {
        return "/auth/login";
    }
}