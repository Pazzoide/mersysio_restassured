package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class _007_Locations_ extends _000_Login_ {
    String locationID;
    String locName;
    String shortName;
    String schoolID = "646cbb07acf2ee0d37c6d984";

    @Test
    public void createLocations() {
        locName = faker.name().name();
        shortName = faker.code().ean8();

        Map<String, Object> locBody = new HashMap<>();
        locBody.put("name", locName);
        locBody.put("shortName", shortName);
        locBody.put("active", true);
        locBody.put("capacity", 10);
        locBody.put("type", "CLASS");
        locBody.put("school", schoolID);

        locationID =
                given()
                        .spec(reqSpec)
                        .body(locBody)

                        .when()
                        .post("/school-service/api/location")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;

    }

    @Test(dependsOnMethods = "createLocations")
    public void createLocation_Negative() {

        Map<String, Object> locBody = new HashMap<>();
        locBody.put("name", locName);
        locBody.put("shortName", shortName);
        locBody.put("active", true);
        locBody.put("capacity", 10);
        locBody.put("type", "CLASS");
        locBody.put("school", schoolID);

        given()
                .spec(reqSpec)
                .body(locBody)

                .when()
                .post("/school-service/api/location")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createLocation_Negative")
    public void updateLocation() {
        Map<String, Object> newLocBody = new HashMap<>();
        newLocBody.put("id", locationID);
        newLocBody.put("name", "UPDATE DONE");
        newLocBody.put("code", "UPDATE DONE");
        newLocBody.put("active", true);
        newLocBody.put("capacity", 10);
        newLocBody.put("type", "CLASS");
        newLocBody.put("school", schoolID);


        given()
                .spec(reqSpec)
                .body(newLocBody)

                .when()
                .put("/school-service/api/location")

                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("UPDATE DONE"))
        ;
    }

    @Test(dependsOnMethods = "updateLocation")
    public void deleteLocation() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/location/" + locationID)

                .then()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deleteLocation")
    public void deleteLocation_Negative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/location/" + locationID)

                .then()
                .statusCode(400)
        ;
    }

}
