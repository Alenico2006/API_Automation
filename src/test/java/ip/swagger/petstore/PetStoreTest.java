package ip.swagger.petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetStoreTest {

    @Test
    public void createPet() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String requestBody = "{ \"id\": 20, \"name\": \"Tambo\", \"status\": \"available\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/pet")
        .then()
                .statusCode(200);
    }

    @Test
    public void getPetById() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .pathParam("petId", 20)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(20))
                .body("name", equalTo("Tambo"))
                .body("status", equalTo("available"));
    }

    @Test
    public void placeOrder() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String requestBody = "{ \"id\": 10, \"petId\": \"100752\", \"quantity\": \"7\", \"status\": \"approved\", \"complete\": \"true\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/store/order")
        .then()
                .statusCode(200);
    }

    @Test
    public void updateUser() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String newLastName = "Haak";

        String requestBody = "{ \"id\": 10, \"username\": \"theUser\", \"firstName\": \"John\", \"lastName\": \"" + newLastName + "\", \"email\": \"jhon@email.com\", \"password\": \"12345\", \"phone\": \"12345\", \"userStatus\": \"1\" }";

        given()
                .pathParam("username", "theUser")
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .put("/user/{username}")
        .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }
}



