package client;

import API_CreateUser.CreateUserRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient {
    public static final String CREATE_URI="https://stellarburgers.nomoreparties.site/api/auth/register";
    public static final String USER_DATA_URI ="https://stellarburgers.nomoreparties.site/api/auth/user";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(CreateUserRequest createUserRequest){
        return given()
                .contentType(ContentType.JSON)
                .body(createUserRequest)
                .when()
                .post(CREATE_URI)
                .then();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken){
        given()
                .header("authorization",accessToken)
                .contentType(ContentType.JSON)
                .when()
                .delete(USER_DATA_URI);
    }
}
