package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _012_Nationalities extends _000_Login_{
    String nationalityID;
    String nationalityName;

    @Test
    public void createNationality() {
        nationalityName = faker.name().name();

        Map<String, Object> nationalityBody = new HashMap<>();
        nationalityBody.put("name", nationalityName);

        nationalityID =
                given()
                        .spec(reqSpec)
                        .body(nationalityBody)

                        .when()
                        .post("/school-service/api/nationality")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;
    }

    @Test(dependsOnMethods = "createNationality")
    public void createNationality_Negative() {

        Map<String, Object> nationalityBody = new HashMap<>();
        nationalityBody.put("name", nationalityName);

        given()
                .spec(reqSpec)
                .body(nationalityBody)

                .when()
                .post("/school-service/api/nationality")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createNationality_Negative")
    public void updateNationality() {
        Map<String, Object> newNationalitybody = new HashMap<>();
        newNationalitybody.put("id", nationalityID);
        newNationalitybody.put("name", "UPDATE DONE");

        given()
                .spec(reqSpec)
                .body(newNationalitybody)

                .when()
                .put("/school-service/api/nationality")

                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("UPDATE DONE"))
        ;
    }

    @Test(dependsOnMethods = "updateNationality")
    public void deleteNationality() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/nationality/" + nationalityID)

                .then()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deleteNationality")
    public void deleteNationality_Negative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/nationality/" + nationalityID)

                .then()
                .statusCode(400)
        ;
    }
}
