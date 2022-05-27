package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.BuildTestData;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    static String placeID;
    BuildTestData buildTestData = new BuildTestData();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        requestSpecification = given()
                .spec(requestSpecification())
                .body(buildTestData.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String api, String methodType) {
        APIResources apiResources = APIResources.valueOf(api); //Constructor will be called with Value of the Resource that is passed to it.
        apiResources.getResource();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if (methodType.equalsIgnoreCase("POST")) {
            response = requestSpecification
                    .when()
                    .post(apiResources.getResource());
        } else if (methodType.equalsIgnoreCase("GET")) {
            response = requestSpecification
                    .when()
                    .get(apiResources.getResource());
        }
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {
        assertEquals(response.statusCode(), expectedStatusCode.intValue());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String status, String statusCode) {
        assertEquals(getJsonPath(response, status), statusCode);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String api) throws IOException {
        placeID = getJsonPath(response, "place_id");
        requestSpecification = given()
                .spec(requestSpecification())
                .queryParam("place_id", placeID);

        user_calls_with_http_request(api, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        requestSpecification = given()
                .spec(requestSpecification())
                .body(buildTestData.deletePlacePayload(placeID));
    }
}
