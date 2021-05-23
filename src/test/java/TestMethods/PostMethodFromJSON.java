package TestMethods;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

@SuppressWarnings("unused")
public class PostMethodFromJSON {

	@Test
	public void postMethod() throws FileNotFoundException {
	String jsonAsString;
	Response ResponseObject;
	
	FileInputStream jsonFile = new FileInputStream(new File ("JSONFile\\JSONRequest.json"));
	System.out.println(jsonFile);
	
	ResponseObject = null;
	ResponseObject = given()
			.contentType("application/json")
			.and().body(jsonFile.toString())
			.when().post("https://reqres.in/api/users")
			.then().and()
			.log().body()
			.statusCode(201).extract()
			.response();
	jsonAsString = ResponseObject.asString();
	System.out.println(jsonAsString);
	
	int statusCode = ResponseObject.getStatusCode();
    Assert.assertEquals(statusCode , 201);
    
    JsonObject jsonObject = new JsonParser().parse(jsonAsString).getAsJsonObject();
	String FirstName = jsonObject.getAsJsonObject("UserDetails").get("FirstName").getAsString();
	System.out.println("FirstName response value = "+FirstName);
	Assert.assertEquals("Saravanan", FirstName);
	}	
}
