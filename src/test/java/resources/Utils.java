package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification rs;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(rs==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));	
		rs=new RequestSpecBuilder().setContentType(ContentType.JSON)
				 .setBaseUri(getGlobalValue("baseUrl"))
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.addQueryParam("key", "qaclick123").build();
		 return rs;
		}
		return rs;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties pro=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\ramso\\eclipse-workspace\\APIFrameWork\\src\\test\\java\\resources\\global.properties");
		pro.load(fis);
		return pro.getProperty(key);
	}
	
	public String getJsonResponseData(Response resp, String key)
	{
		String response1=resp.asString();
		JsonPath js=new JsonPath(response1);
		return js.get(key).toString();
	}
}
