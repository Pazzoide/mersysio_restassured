package Campus_API_Testing;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _001_Position_Category_ extends _000_Login_ {
    String positionCategoryID;
    String positionCategoryName;

    @Test
    public void Adding() {
        String name = faker.name().firstName();
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        Response pathed =
                given()
                        .spec(reqSpec)
                        .body(data)
                        .when()
                        .post("/school-service/api/position-category")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().response();
        positionCategoryID = pathed.path("id");
        positionCategoryName = pathed.path("name");
    }

    @Test(dependsOnMethods = "Adding")
    public void Adding_Negative() {
        Map<String, String> data = new HashMap<>();
        data.put("name", positionCategoryName);
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/school-service/api/position-category")
                .then()
                //.log().body()
                .statusCode(400)
                .body("message", containsString("already"));
    }

    @Test(dependsOnMethods = "Adding_Negative")
    public void Update() {
        String name = faker.name().firstName();
        Map<String, String> data = new HashMap<>();
        data.put("id", positionCategoryID);
        data.put("name", name);
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .put("/school-service/api/position-category")
                .then()
                //.log().body()
                .statusCode(200)
                .body("name", equalTo(name));
    }

    @Test(dependsOnMethods = "Update")
    public void Delete() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/position-category/" + positionCategoryID)
                .then()
                //.log().body()
                .statusCode(204)
        ;
    }

    @Test(dependsOnMethods = "Delete")
    public void Delete_Negative() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/position-category/" + positionCategoryID)
                .then()
                //.log().body()
                .statusCode(400);
    }
}
