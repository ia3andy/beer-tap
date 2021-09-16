package quarkus.world.tour;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class BeerTapTest {

    @Test
    public void canSeeMenu() {
        final Response response = given()
                .when().get("/beer")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract().response();
        final List<String> names = response.jsonPath().getList("name");
        assertThat("I can see the menu", names,
                hasSize(7));
    }

    @Test
    public void canSeeBestBeers() {
        final Response response = given()
                .when().get("/beer/best")
                .then()
                .statusCode(200)
                .extract().response();
        final List<String> names = response.jsonPath().getList("name");
        assertThat("I can see the favorite ones", names, contains("LONDON IPA", "Brooklyn Defender IPA", "Triple Karmeliet"));
    }
}
