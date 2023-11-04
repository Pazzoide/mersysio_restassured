package Campus_API_Testing;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class _002_Attestations_ extends _000_Login_ {
    String attestationsID;
    String attestationsName;
    @Test
    public void Adding() {
        String name = faker.file().fileName();
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        Response pathed =
                given()
                        .spec(reqSpec)
                        .body(data)
                        .when()
                        .post("/school-service/api/attestation")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().response();
        attestationsID = pathed.path("id");
        attestationsName = pathed.path("name");
    }
    @Test(dependsOnMethods = "Adding")
    public void Adding_Negative() {
        Map<String, String> data = new HashMap<>();
        data.put("name", attestationsName);
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/school-service/api/attestation")
                .then()
                //.log().body()
                .statusCode(400)
                .body("message", containsString("already"));
    }
    @Test(dependsOnMethods = "Adding_Negative")
    public void Update() {
        String name = faker.file().fileName();
        Map<String, String> data = new HashMap<>();
        data.put("id", attestationsID);
        data.put("name", name);
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .put("/school-service/api/attestation")
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
                .delete("/school-service/api/attestation/" + attestationsID)
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
                .delete("/school-service/api/attestation/" + attestationsID)
                .then()
                //.log().body()
                .statusCode(400);
    }
}
