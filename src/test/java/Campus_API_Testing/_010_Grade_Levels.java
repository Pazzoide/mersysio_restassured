package Campus_API_Testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _010_Grade_Levels extends _000_Login_{
    String GradeLevelID;

    String GradeLevelName;
    String GradeLevelShortName;
    String schoolID = "646cbb07acf2ee0d37c6d984";


    @Test
    public void Add(){
        String name=faker.name().firstName();
        String shortname=faker.name().lastName();
        Map <String,Object> data=new HashMap<>();
        data.put("name",name);
        data.put("shortName",shortname);
        data.put("order","1");

        Response path=
                given()
                        .spec(reqSpec)
                        .body(data)
                        .when()
                        .post("/school-service/api/grade-levels")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().response();
        GradeLevelID=path.path("id");
        GradeLevelName=path.path("name");
        GradeLevelShortName=path.path("shortname");

        }

        @Test(dependsOnMethods = "Add")
    public void AddNegative(){
            Map <String,String> data=new HashMap<>();
            data.put("name",GradeLevelName);
            data.put("shortName",GradeLevelName);
            data.put("order","8");


                    given()
                            .spec(reqSpec)
                            .body(data)
                            .when()
                            .post("/school-service/api/grade-levels")

                            .then()
                            .statusCode(400)
                            .body("message",containsString("already"))
                    ;

        }
        @Test(dependsOnMethods = "AddNegative")
    public void update(){
        String name=faker.name().firstName();
        String shortname=faker.name().username();


           Map <String,Object>data=new HashMap<>();
            data.put("id",GradeLevelID);
            data.put("name",name);
            data.put("shortName",shortname);


        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .put("/school-service/api/grade-levels")

                .then()
                .log().body()
                .statusCode(200)

        ;

        }

        @Test(dependsOnMethods = "update")
    public void delete(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/grade-levels/"+GradeLevelID)


                .then()
                .log().body()
                .statusCode(200)

        ;
        }

    @Test(dependsOnMethods = "delete")
    public void deleteNegative(){
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/grade-levels/"+GradeLevelID)


                .then()
                .statusCode(400)

        ;
    }
    }













