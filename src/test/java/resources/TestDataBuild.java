package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceGoogleMap;
import pojo.location;

public class TestDataBuild {

	public AddPlaceGoogleMap addPlacePayload(String name,String language,String address)
	{
		AddPlaceGoogleMap ap=new AddPlaceGoogleMap();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		location l=new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
