package Campus_API_Testing;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _008_Departments_ extends _000_Login_{


    String departmentsID="";

    String rndName="";
    String rndCode="";
    String schoolId="646cbb07acf2ee0d37c6d984";



    @Test
    public void createDepartments(){

        rndName= faker.country().currencyCode()+faker.number().digits(2);

        Map<String,String> newDepartments=new HashMap<>();
      newDepartments.put("name",rndName);
      newDepartments.put("code",faker.number().digits(4));
      newDepartments.put("school",schoolId);

      departmentsID=
                given()
                        .spec(reqSpec)
                        .body(newDepartments)
                       // .log().body()
                        .when()
                        .post("school-service/api/department")

                        .then()
                        //.log().body()
                       .statusCode(201)
                        .extract().path("id");

                   }
            @Test(dependsOnMethods = "createDepartments")
    public void createDepartmentsNegative(){

                Map<String,String> newDepartments=new HashMap<>();
                newDepartments.put("name",rndName);
                newDepartments.put("code",faker.number().digits(4));
                newDepartments.put("school",schoolId);


                        given()
                                .spec(reqSpec)
                                .body(newDepartments)
                                // .log().body()
                                .when()
                                .post("school-service/api/department")

                                .then()
                                //.log().body()
                                .statusCode(400)
                                .extract().path("id");
                            }

@Test(dependsOnMethods = "createDepartmentsNegative")
public void updateParameters(){

    rndName= faker.country().currencyCode()+faker.number().digits(2);

    Map<String,String> newDepartments=new HashMap<>();
    newDepartments.put("name",rndName);
    newDepartments.put("id",departmentsID);
    newDepartments.put("code",faker.number().digits(4));
    newDepartments.put("school",schoolId);

    given()
            .spec(reqSpec)
            .body(newDepartments)
            // .log().body()
            .when()
            .put("school-service/api/department")

            .then()
            //.log().body()
            .statusCode(200);

}
@Test(dependsOnMethods = "updateParameters")
    public void delete(){
    given()
            .spec(reqSpec)
            // .log().body()
            .when()
            .delete("school-service/api/department/"+departmentsID)

            .then()
            //.log().body()
            .statusCode(204);

    }
    @Test(dependsOnMethods = "delete")
    public void deleteNegative(){
        given()
                .spec(reqSpec)
                // .log().body()
                .when()
                .delete("school-service/api/department/"+departmentsID)

                .then()
                //.log().body()
                .statusCode(400);

           }




}
