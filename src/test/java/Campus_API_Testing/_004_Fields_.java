package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _004_Fields_ extends _000_Login_{
    String fieldID;
    String fieldName;
    String code;
    String type = "STRING";
    String schoolID = "646cbb07acf2ee0d37c6d984";

    @Test
    public void createFields() {
        fieldName = faker.name().name();
        code = faker.code().ean8();

        Map<String, Object> fieldBody = new HashMap<>();
        fieldBody.put("name", fieldName);
        fieldBody.put("code", code);
        fieldBody.put("type", type);
        fieldBody.put("systemField", false);
        fieldBody.put("schoolId", schoolID);

        fieldID =
                given()
                        .spec(reqSpec)
                        .body(fieldBody)

                        .when()
                        .post("/school-service/api/entity-field")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;

    }

    @Test(dependsOnMethods = "createFields")
    public void createFields_Negative() {

        Map<String, Object> fieldBody = new HashMap<>();
        fieldBody.put("name", fieldName);
        fieldBody.put("code", code);
        fieldBody.put("type", type);
        fieldBody.put("systemField", false);
        fieldBody.put("schoolId", schoolID);

        given()
                .spec(reqSpec)
                .body(fieldBody)

                .when()
                .post("/school-service/api/entity-field")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createFields_Negative")
    public void updateFields() {
        Map<String, Object> newFieldBody = new HashMap<>();
        newFieldBody.put("id", fieldID);
        newFieldBody.put("name", "UPDATE DONE");
        newFieldBody.put("code", "UPDATE DONE");
        newFieldBody.put("type", type);
        newFieldBody.put("systemField", false);
        newFieldBody.put("schoolId", schoolID);

        given()
                .spec(reqSpec)
                .body(newFieldBody)

                .when()
                .put("/school-service/api/entity-field")

                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo("UPDATE DONE"))
        ;
    }

    @Test(dependsOnMethods = "updateFields")
    public void deleteFields() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/entity-field/" + fieldID)

                .then()
                .statusCode(204)
        ;
    }

    @Test(dependsOnMethods = "deleteFields")
    public void deleteFields_Negative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/entity-field/" + fieldID)

                .then()
                .statusCode(400)
        ;
    }
}
