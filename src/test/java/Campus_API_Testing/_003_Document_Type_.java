package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _003_Document_Type_ extends _000_Login_{
    String documentID;
    String documentName;
    String description;
    String schoolID = "646cbb07acf2ee0d37c6d984";
    String[] attachmentStages = {"STUDENT_REGISTRATION"};

    @Test
    public void createDocumentTypes() {
        documentName = faker.name().name();
        description = faker.name().name();

        Map<String, Object> docBody = new HashMap<>();
        docBody.put("name", documentName);
        docBody.put("description", description);
        docBody.put("attachmentStages", attachmentStages);
        docBody.put("active", true);
        docBody.put("required", true);
        docBody.put("useCamera", false);
        docBody.put("schoolId", schoolID);

        documentID =
                given()
                        .spec(reqSpec)
                        .body(docBody)

                        .when()
                        .post("/school-service/api/attachments/create")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;

    }

//    @Test(dependsOnMethods = "createDocumentTypes")
//    public void createDocumentTypes_Negative() {
//        String[] attachmentStages = {"STUDENT_REGISTRATION"};
//
//        Map<String, Object> docBody = new HashMap<>();
//        docBody.put("name", documentName);
//        docBody.put("description", description);
//        docBody.put("attachmentStages", attachmentStages);
//        docBody.put("active", true);
//        docBody.put("required", true);
//        docBody.put("useCamera", false);
//        docBody.put("schoolId", "646cbb07acf2ee0d37c6d984");
//
//        given()
//                .spec(reqSpec)
//                .body(docBody)
//
//                .when()
//                .post("/school-service/api/attachments/create")
//
//                .then()
//                .log().body()
//                .statusCode(400)
//                //.body("message", containsString("already"))
//        ;
//    }

    @Test(dependsOnMethods = "createDocumentTypes")
    public void updateDocumentTypes() {
        Map<String, Object> newDocBody = new HashMap<>();
        newDocBody.put("id", documentID);
        newDocBody.put("name", "UPDATE DONE");
        newDocBody.put("description", "UPDATE DONE");
        newDocBody.put("attachmentStages", attachmentStages);
        newDocBody.put("active", true);
        newDocBody.put("required", true);
        newDocBody.put("useCamera", false);
        newDocBody.put("schoolId", schoolID);

        given()
                .spec(reqSpec)
                .body(newDocBody)

                .when()
                .put("/school-service/api/attachments")

                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo("UPDATE DONE"))
        ;
    }

    @Test(dependsOnMethods = "updateDocumentTypes")
    public void deleteDocumentTypes() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/attachments/" + documentID)

                .then()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deleteDocumentTypes")
    public void deleteDocumentTypes_Negative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/attachments/" + documentID)

                .then()
                .statusCode(400)
        ;
    }

}
