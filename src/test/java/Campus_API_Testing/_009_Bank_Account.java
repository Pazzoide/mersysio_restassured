package Campus_API_Testing;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class _009_Bank_Account extends _000_Login_{

    String BankAccountName;

    String BankAccountIban;

    String BankAccountID;

    String schoolID = "646cbb07acf2ee0d37c6d984";
    @Test
    public void Add(){
        String name=faker.name().firstName();
        String iban=faker.finance().iban();
        String currency="EUR";

        Map<String, Object> data=new HashMap<>();
        data.put("name",name);
        data.put("iban",iban);
        data.put("currency",currency);
        data.put("schoolId", schoolID);

        Response path=
                given()
                        .spec(reqSpec)
                        .body(data)

                        .when()
                        .post("/school-service/api/bank-accounts")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().response();
        BankAccountID=path.path("id");
        BankAccountName=path.path("name");
        BankAccountIban=path.path("iban");
        ;
    }

    @Test(dependsOnMethods = "Add")
    public void AddNegative(){
        Map<String,String> data=new HashMap<>();
        data.put("name",BankAccountName);
        data.put("iban",BankAccountIban);
        data.put("schoolId", schoolID);
        data.put("currency","EUR");


        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .post("/school-service/api/bank-accounts")

                .then()
                .statusCode(400)
                .body("message",containsString("already"))
        ;
    }
    @Test(dependsOnMethods = "AddNegative")
    public void update(){
        String iban=faker.finance().iban();
        String name=faker.name().firstName();

        Map<String,String> data=new HashMap<>();
        data.put("name",name);
        data.put("id",BankAccountID);
        data.put("iban",iban);
        data.put("schoolId", schoolID);
        data.put("currency","EUR");

        given()
                .spec(reqSpec)
                .body(data)

                .when()
                .put("/school-service/api/bank-accounts")

                .then()
                .log().body()
                .statusCode(200)
                .body("iban",equalTo(iban))
        ;
    }
    @Test(dependsOnMethods = "update")
    public void delete(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/bank-accounts/"+BankAccountID)

                .then()
                //.log().body()
                .statusCode(200)

        ;
    }
    @Test(dependsOnMethods = "delete")
    public void deleteNegative(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/bank-accounts/"+BankAccountID)

                .then()
                .log().body()
                .statusCode(400)
        ;
    }
}

