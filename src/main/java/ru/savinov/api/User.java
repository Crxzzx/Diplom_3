package ru.savinov.api;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Data
@Builder
public class User {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String AUTHORIZATION_HANDLE = "/api/auth/";
    private static final String DELETE_HANDLE = "/api/auth/user";
    private static final Faker LOCAL_USER_RU = new Faker(new Locale("ru-RU"));
    private static final Faker LOCAL_USER_EN = new Faker(new Locale("en-GB"));
    private String name;
    private String email;
    private String password;

    public static User generateRandomUserValidData() {
        final String name = generateValidName();
        final String email = generateValidEmail();
        final String password = generateValidPassword();
        return new User(name, email, password);
    }

    public static String generateValidName() {
        return LOCAL_USER_RU.name().firstName();
    }

    public static String generateValidEmail() {
        return LOCAL_USER_EN.internet().emailAddress();
    }

    public static String generateValidPassword() {
        int minSize = 6;
        int maxSize = 10;
        return LOCAL_USER_EN.internet().password(minSize, maxSize);
    }

    public static String generateBrokePassword() {
        int minSize = 1;
        int maxSize = 5;
        return LOCAL_USER_EN.internet().password(minSize, maxSize);
    }

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(BASE_URL).build();
    }

    public String getTokenUser() {
        String token = "";
        ValidatableResponse response = given()
                .spec(getBaseSpec())
                .body(this)
                .when()
                .post(BASE_URL + AUTHORIZATION_HANDLE)
                .then();
        if (response.extract().statusCode() == 200) {
            token = response.extract().path("accessToken");
        }
        return token;
    }

    public void deleteUser() {
        String tokenUser = getTokenUser();
        given()
                .spec(getBaseSpec())
                .header("Authorization", tokenUser)
                .when()
                .delete(BASE_URL + DELETE_HANDLE)
                .then();
    }

}
