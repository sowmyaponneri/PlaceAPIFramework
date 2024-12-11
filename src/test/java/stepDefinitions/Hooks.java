package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//execute this code when place_id is null
		//write a code that will give place_id
		
		StepDefinitions m=new StepDefinitions();
		if(StepDefinitions.placeId==null)
		{
		m.add_place_payload_with("Sowmya", "Tamil", "E Ramsey Dr");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Sowmya", "getPlaceAPI");
		}
	}

}
