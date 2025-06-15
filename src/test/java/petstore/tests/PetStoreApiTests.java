package petstore.tests;

import data.PetDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import specifications.Specifications;

import java.util.List;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreApiTests {

    private static final Long PET_ID = 987654321L;

    @Test
    @Order(1)
    void createPetTest() {
        PetDTO pet = new PetDTO(PET_ID, "TestPet", List.of("http://example.com/photo"), "available");

        RestAssured.given()
                .spec(Specifications.petstoreSpec())
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(PET_ID.intValue()))
                .body("name", equalTo("TestPet"))
                .body("status", equalTo("available"));
    }

    @Test
    @Order(2)
    void getPetTest() {
        RestAssured.given()
                .spec(Specifications.petstoreSpec())
                .when()
                .get("/pet/" + PET_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(PET_ID.intValue()))
                .body("name", equalTo("TestPet"))
                .body("status", equalTo("available"));
    }

    @Test
    @Order(3)
    void updatePetTest() {
        PetDTO updatedPet = new PetDTO(PET_ID, "UpdatedPet", List.of("http://example.com/photo"), "sold");

        RestAssured.given()
                .spec(Specifications.petstoreSpec())
                .body(updatedPet)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("UpdatedPet"))
                .body("status", equalTo("sold"));
    }

    @Test
    @Order(4)
    void deletePetTest() {
        RestAssured.given()
                .spec(Specifications.petstoreSpec())
                .when()
                .delete("/pet/" + PET_ID)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    void getDeletedPetShouldReturn404() {
        RestAssured.given()
                .spec(Specifications.petstoreSpec())
                .when()
                .get("/pet/" + PET_ID)
                .then()
                .statusCode(404);
    }
}
