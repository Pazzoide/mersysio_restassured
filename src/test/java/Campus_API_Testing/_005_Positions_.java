package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class _005_Positions_ extends _000_Login_{
    String positionID="";

    @Test
    public void createPosition(){
        Object[] objects = new Object[1];
        Map<String,Object> data = new HashMap<>();
        data.put("name","Computer Teacher");
        data.put("shortName","Comp_T");
        data.put("translateName",new Object[1]);
        data.put("tenantId","646cb816433c0f46e7d44cb0");
        data.put("active",true);

        positionID=
        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .post("school-service/api/employee-position")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id")
                ;
    }

    @Test(dependsOnMethods = "createPosition")
    public void createPositionNegative(){
        Object[] objects = new Object[1];
        Map<String,Object> data = new HashMap<>();
        data.put("name","Computer Teacher");
        data.put("shortName","Comp_T");
        data.put("translateName",new Object[1]);
        data.put("tenantId","646cb816433c0f46e7d44cb0");
        data.put("active",true);

                given()
                        .spec(reqSpec)
                        .body(data)

                        .when()
                        .post("school-service/api/employee-position")

                        .then()
                        .log().body()
                        .statusCode(400)
                        .body("message",containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createPositionNegative")
    public void updatePosition(){
        Object[] objects = new Object[1];
        Map<String,Object> data = new HashMap<>();
        data.put("id",positionID);
        data.put("name","Mentoring Teacher");
        data.put("shortName","Mntr_T");
        data.put("translateName",new Object[1]);
        data.put("tenantId","646cb816433c0f46e7d44cb0");
        data.put("active",true);

        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .put("school-service/api/employee-position")

                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo("Mentoring Teacher"))
        ;
    }

    @Test(dependsOnMethods = "updatePosition")
    public void deletePosition(){

        given()
                .spec(reqSpec)

                .when()
                .delete("school-service/api/employee-position/"+positionID)

                .then()
                .log().all()
                .statusCode(204)
        ;
    }

}
