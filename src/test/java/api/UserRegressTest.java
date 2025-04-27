package api;

import api.Model.*;
import api.Specification.Specifications;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Epic("Reqres Tests")
@Owner("RoyMoskitov")
@Tag("api")
public class UserRegressTest {
    public static final String BASE_URL = "https://reqres.in/";

    @BeforeAll
    public static void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void testIdsAreUnique() {
        Specifications.setSpecs(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .extract()
                .jsonPath()
                .getList("data", UserData.class);

        Map<Integer, Boolean> idExists = new HashMap<>();
        users.forEach(x -> {
            assertFalse(idExists.containsKey(x.getId()));
            idExists.put(x.getId(), true);
        });
    }

    @Test
    public void testCorrectUserCreation() {
        String expectedName = "Artem Kim";
        String expectedJob = "builder";
        Specifications.setSpecs(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(201));
        UserCreationRequest userCreationRequest = new UserCreationRequest(expectedName, expectedJob);

        UserCreationResponse userCreationResponse = given()
                .body(userCreationRequest)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .as(UserCreationResponse.class);

        assertEquals(expectedJob, userCreationResponse.getJob());
        assertEquals(expectedName, userCreationResponse.getName());
    }

    @Test
    public void testUnsuccessfulUserRegistration() {
        String expectedOutput = "Missing password";
        Specifications.setSpecs(Specifications.requestSpec(BASE_URL, "x-api-key", "reqres-free-v1"),
                Specifications.responseSpec(400));
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest("user@gmail.com", "");

        String response = given()
                .body(userRegistrationRequest)
                .when()
                .post("api/register")
                .then()
                .extract()
                .jsonPath()
                .getString("error");

        assertEquals(expectedOutput, response);
    }

    @Test
    public void testSuccessfulUserDeletion() {
        Specifications.setSpecs(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(204));

        given().when().delete("api/users/{id}", 2).then();
    }

    @Test
    public void testSequentialData() {
        Specifications.setSpecs(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));
        List<UnknownApiResponse> users = given()
                .when()
                .get("/api/unknown")
                .then()
                .extract()
                .jsonPath()
                .getList("data", UnknownApiResponse.class);

        final int[] min = {Integer.MIN_VALUE};
        users.forEach(x -> {
            assertTrue(x.getYear() > min[0]);
            min[0] = x.getYear();
        });
    }

}
