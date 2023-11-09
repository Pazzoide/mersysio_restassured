package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _006_Subject_Categories_ extends _000_Login_ {
    String subCategoriID = "";

    @Test
    public void createSubjectCategori() {
        Object[] object = new Object[1];
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Sayisal");
        data.put("code", "05");
        data.put("active", true);
        data.put("translateName", new Object[1]);

        subCategoriID =
                given()
                        .spec(reqSpec)
                        .body(data)

                        .when()
                        .post("school-service/api/subject-categories")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;
    }

    @Test(dependsOnMethods = "createSubjectCategori")
    public void createSubjectCategoriNegative() {
        Object[] object = new Object[1];
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Sayisal");
        data.put("code", "05");
        data.put("active", true);
        data.put("translateName", new Object[1]);

        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .post("school-service/api/subject-categories")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createSubjectCategoriNegative")
    public void updateSubjectCategori() {
        Object[] object = new Object[1];
        Map<String, Object> data = new HashMap<>();
        data.put("id",subCategoriID);
        data.put("name", "Sozel");
        data.put("code", "04");
        data.put("active", true);
        data.put("translateName", new Object[1]);

        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .put("school-service/api/subject-categories")

                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("Sozel"))
        ;
    }

    @Test(dependsOnMethods = "updateSubjectCategori")
    public void deleteSubjectCategori() {

        given()
                .spec(reqSpec)

                .when()
                .delete("school-service/api/subject-categories/"+subCategoriID)

                .then()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deleteSubjectCategori")
    public void deleteSubjectCategoriNegative() {

        given()
                .spec(reqSpec)

                .when()
                .delete("school-service/api/subject-categories/"+subCategoriID)

                .then()
                .log().body()
                .statusCode(400)
                .body("message",containsString("not  found"))
        ;
    }


}
