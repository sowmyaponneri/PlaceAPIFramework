Feature: Validating Place APIs
@AddPlace
Scenario Outline: Verify that Place is added successfully using AddPlaceAPI

Given Add Place Payload with "<name>","<language>","<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP" 
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
  |name|language|address|
  |AZBhouse|English|World Center|
# |CZBhouse|English|World Center|

@DeletePlace
Scenario: Verify if delete place functionality is working

Given Delete Place Payload
When user calls "deletePlaceAPI" with "DELETE" http request
Then the API call is success with status code 200
And "status" in response body is "OK"


