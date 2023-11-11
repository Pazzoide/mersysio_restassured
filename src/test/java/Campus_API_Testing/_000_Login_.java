package Campus_API_Testing;

import Utilities.ExcelManager;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _000_Login_ {
    Faker faker = new Faker();
    RequestSpecification reqSpec;

    @BeforeClass
    public void Login() {
        ArrayList<ArrayList<String>> table = ExcelManager.getData("src/test/java/ExcelFiles/LoginInfo.xlsx",
                "login", 2);

        baseURI = "https://test.mersys.io/";
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("username", table.get(0).get(0));
        loginInfo.put("password", table.get(0).get(1));
        loginInfo.put("rememberMe", "true");
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
    public void Login_Negative() {
        ArrayList<ArrayList<String>> table = ExcelManager.getData("src/test/java/ExcelFiles/LoginInfo.xlsx",
                "loginTest", 2);

        for (ArrayList<String> row : table) {
            baseURI = "https://test.mersys.io/";
            Map<String, String> loginInfo = new HashMap<>();
            loginInfo.put("username", row.get(0));
            loginInfo.put("password", row.get(1));
            loginInfo.put("rememberMe", "true");
            given()
                    .body(loginInfo)
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/auth/login")
                    .then()
                    .log().body()
                    .statusCode(401)
                    .body("title", containsString("Invalid"));
        }
    }

//    @AfterMethod
//    public void after(ITestResult iTestResult) {
//        String testStatus;
//        if (iTestResult.getStatus() == 1) {
//            testStatus = "SUCCESS";
//        } else if (iTestResult.getStatus() == 2) {
//            testStatus = "FAILURE";
//        } else {
//            testStatus = "SKIPPED";
//        }
//
//        ExcelManager.excelWriter("Test Excel Reports/ExcelOutput.xlsx", iTestResult, testStatus);
//
//    }

}
