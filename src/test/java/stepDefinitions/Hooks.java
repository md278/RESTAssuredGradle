package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition stepDefinition = new StepDefinition();
        if (StepDefinition.placeID == null) {
            stepDefinition.add_place_payload_with("Shankar", "French", "Asia");
            stepDefinition.user_calls_with_http_request("addPlaceAPI", "POST");
            stepDefinition.verify_place_id_created_maps_to_using("Shankar", "getPlaceAPI");
        }
    }
}
