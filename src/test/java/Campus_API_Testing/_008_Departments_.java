package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class _008_Departments_ extends _000_Login_{
    String departmentID;
    String departmentName;
    String code;
    String schoolID = "646cbb07acf2ee0d37c6d984";

    @Test
    public void createDepartment() {
        departmentName = faker.name().name();
        code = faker.code().ean8();

        Map<String, Object> departmentBody = new HashMap<>();
        departmentBody.put("name", departmentName);
        departmentBody.put("code", code);
        departmentBody.put("active", true);
        departmentBody.put("school", schoolID);

        departmentID =
                given()
                        .spec(reqSpec)
                        .body(departmentBody)

                        .when()
                        .post("/school-service/api/department")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;

    }

    @Test(dependsOnMethods = "createDepartment")
    public void createDepartment_Negative() {

        Map<String, Object> departmentBody = new HashMap<>();
        departmentBody.put("name", departmentName);
        departmentBody.put("code", code);
        departmentBody.put("active", true);
        departmentBody.put("school", schoolID);


        given()
                .spec(reqSpec)
                .body(departmentBody)

                .when()
                .post("/school-service/api/department")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createDepartment_Negative")
    public void updateDepartment() {
        Map<String, Object> newDepartmentBody = new HashMap<>();
        newDepartmentBody.put("id", departmentID);
        newDepartmentBody.put("name", "UPDATE DONE");
        newDepartmentBody.put("code", "UPDATE DONE");
        newDepartmentBody.put("active", true);
        newDepartmentBody.put("school", schoolID);


        given()
                .spec(reqSpec)
                .body(newDepartmentBody)

                .when()
                .put("/school-service/api/department")

                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo("UPDATE DONE"))
        ;
    }

    @Test(dependsOnMethods = "updateDepartment")
    public void deleteDepartment() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/department/" + departmentID)

                .then()
                .statusCode(204)
        ;
    }

    @Test(dependsOnMethods = "deleteDepartment")
    public void deleteDepartment_Negative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/department/" + departmentID)

                .then()
                .statusCode(400)
        ;
    }

}
