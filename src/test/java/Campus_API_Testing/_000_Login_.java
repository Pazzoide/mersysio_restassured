package Campus_API_Testing;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _000_Login_ {
    Faker faker = new Faker();
    RequestSpecification reqSpec;

    @BeforeClass
    public void Login() {
        baseURI = "https://test.mersys.io/";
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("username","*********");
        loginInfo.put("password","*************");
        loginInfo.put("rememberMe","true");
        Cookies cookies =
                given()
                        .body(loginInfo)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract().response().detailedCookies();
        reqSpec = new RequestSpecBuilder()
                .addCookies(cookies)
                .setContentType(ContentType.JSON)
                .build();
    }
    @Test(enabled = false)
    public void Login_InvalidUsername(){
        baseURI = "https://test.mersys.io/";
        String username = faker.name().username();
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("username",username);
        loginInfo.put("password","TechnoStudy123");
        loginInfo.put("rememberMe","true");
        given()
                .body(loginInfo)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                //.log().body()
                .statusCode(401)
                .body("title",containsString("Invalid"));
    }
    @Test(enabled = false)
    public void Login_InvalidPassword(){
        baseURI = "https://test.mersys.io/";
        String password = faker.idNumber().invalid();
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("username","turkeyts");
        loginInfo.put("password",password);
        loginInfo.put("rememberMe","true");
        given()
                .body(loginInfo)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                //.log().body()
                .statusCode(401)
                .body("title",containsString("Invalid"));
    }
}
