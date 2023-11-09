package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class _009_Bank_Account extends _000_Login_{

    Map<String, String> bankAccount;
    String bankAccountUserName;
    String schoolID="646cbb07acf2ee0d37c6d984";
    String bankAccountID;

    @Test
    public void createBankAccount() {

        bankAccount=new HashMap<>();

        bankAccountUserName= faker.address().firstName()+" "+faker.address().lastName();
        bankAccount.put("name", bankAccountUserName);

        bankAccount.put("iban", "Team12" + faker.number().digits(12));
        bankAccount.put("integrationCode", faker.number().digits(4));

        bankAccount.put("currency", "EUR");
        bankAccount.put("schoolId", schoolID);


        bankAccountID =

                given()

                        .spec(reqSpec)
                        .body(bankAccount)
                        //.log().body()
                        .when()
                        .post("school-service/api/bank-accounts")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().path("id");


    }
@Test(dependsOnMethods = "createBankAccount")
    public void createBankAccountNegative() {

        given()

                .spec(reqSpec)
                .body(bankAccount)
                //.log().body()
                .when()
                .post("school-service/api/bank-accounts")
                .then()
                //.log().body()
                .statusCode(400)
                .body("message", containsString("already")) ;



    }
@Test(dependsOnMethods = "createBankAccountNegative")
    public void updateBankAccount() {

        bankAccountUserName= faker.address().firstName()+" "+faker.address().lastName()+" "+faker.address().lastName();
        bankAccount.put("name", bankAccountUserName);

        bankAccount.put("iban", "DE" + faker.number().digits(16));
        bankAccount.put("integrationCode", faker.number().digits(8));

        bankAccount.put("currency", "USD");
        bankAccount.put("schoolId", schoolID);
        bankAccount.put("id",bankAccountID);

        given()

                .spec(reqSpec)
                .body(bankAccount)
                // .log().body()
                .when()
                .put("school-service/api/bank-accounts")
                .then()
                //.log().body()
                .statusCode(200)
                .body("name", equalTo(bankAccountUserName));



    }
    @Test(dependsOnMethods = "updateBankAccount")
    public void deleteBankAccount() {

        given()

                .spec(reqSpec)
                .when()
                .delete("school-service/api/bank-accounts/" + bankAccountID)
                .then()
                //.log().body()
                .statusCode(200);


    }
    @Test(dependsOnMethods = "deleteBankAccount")
    public void deleteBankAccountNegative() {

        given()

                .spec(reqSpec)
                //.log().uri()
                .when()
                .delete("school-service/api/bank-accounts/" + bankAccountID)
                .then()
                //.log().body()
                .statusCode(400)
                .body("message", containsString("must be exist"));


    }

}
