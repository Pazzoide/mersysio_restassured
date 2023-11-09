package Campus_API_Testing;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _011_Discounts extends _000_Login_{

    String discountID;
    String discountDesc;
    String code;

    @Test
    public void createDiscount() {
        discountDesc = faker.name().name();
        code = faker.code().ean8();

        Map<String, Object> discountBody = new HashMap<>();
        discountBody.put("description", discountDesc);
        discountBody.put("code", code);
        discountBody.put("priority", 120);

        discountID =
                given()
                        .spec(reqSpec)
                        .body(discountBody)

                        .when()
                        .post("/school-service/api/discounts")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;

    }

    @Test(dependsOnMethods = "createDiscount")
    public void createDiscount_Negative() {

        Map<String, Object> discountBody = new HashMap<>();
        discountBody.put("description", discountDesc);
        discountBody.put("code", code);
        discountBody.put("priority", 120);

        given()
                .spec(reqSpec)
                .body(discountBody)

                .when()
                .post("/school-service/api/discounts")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createDiscount_Negative")
    public void updateDiscount() {
        Map<String, Object> newDiscountBody = new HashMap<>();
        newDiscountBody.put("id", discountID);
        newDiscountBody.put("description", "UPDATE DONE");
        newDiscountBody.put("code", "UPDATE DONE");
        newDiscountBody.put("priority", 10);

        given()
                .spec(reqSpec)
                .body(newDiscountBody)

                .when()
                .put("/school-service/api/discounts")

                .then()
                .log().body()
                .statusCode(200)
                .body("description",equalTo("UPDATE DONE"))
        ;
    }

    @Test(dependsOnMethods = "updateDiscount")
    public void deleteDiscount() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/discounts/" + discountID)

                .then()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deleteDiscount")
    public void deleteDiscount_Negative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/discounts/" + discountID)

                .then()
                .statusCode(400)
        ;
    }
}
