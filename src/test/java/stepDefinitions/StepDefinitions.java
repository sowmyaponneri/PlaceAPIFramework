package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.junit.Assert.*;

import pojo.AddPlaceGoogleMap;
import pojo.location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification req;
	ResponseSpecification resultspc;
	Response resp;
	TestDataBuild testData=new TestDataBuild();
	static String placeId;

	@Given("Add Place Payload with {string},{string},{string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req=given().spec(requestSpecification())
		.body(testData.addPlacePayload(name,language,address));

	}



	/*@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		req=given().log().all().spec(requestSpecification())
		.body(testData.addPlacePayload());
		
	}*/
	
	

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		
		resultspc=new ResponseSpecBuilder().expectStatusCode(200).
				expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("POST"))
			 resp=req.when().post(resourceAPI.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 resp=req.when().get(resourceAPI.getResource());
		 else if(method.equalsIgnoreCase("DELETE"))
			 resp=req.when().delete(resourceAPI.getResource());
				
	}
	
	@When("user calls {string} with POST http request")
	public void user_calls_with_post_http_request(String resource) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI=APIResources.valueOf(resource);
		
		resultspc=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 /*resp=req.when().post("maps/api/place/add/json")
				.then().spec(resultspc)
				.extract().response();*/
		resp=req.when().post(resourceAPI.getResource())
				.then().spec(resultspc)
				.extract().response();
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(resp.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		String resp1=getJsonResponseData(resp,keyValue);
		assertEquals(resp1,expectedValue);
	}
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    //get place id from addplaceapi response
		placeId=getJsonResponseData(resp, "place_id");
		//create request spec
		req=given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonResponseData(resp, "name");
		assertEquals(actualName,expectedName);
		
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req=given().spec(requestSpecification()).body(testData.deletePlacePayload(placeId));
	}








}
