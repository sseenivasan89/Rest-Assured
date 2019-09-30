package TestMethods;

import static io.restassured.RestAssured.given;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

public class PostMethod {

	@Test
	public void postMethod() {
	String jsonAsString;
	Response ResponseObject;
	//Build JSON Post Body
	JSONObject objects = new JSONObject();
	objects.put("FirstName", "Saravanan");
	objects.put("LastName", "Seenivasan");
	
	//Build JSON Array List
	JSONArray list = new JSONArray();
	list.add("QA");
	list.add("Senior QA");
	objects.put("JobHistory", list);
	
	//Append the child objects to Main Objects
	JSONObject completeRequestObject = new JSONObject();
	completeRequestObject.put("UserDetails", objects);
	
	ResponseObject = null;
	ResponseObject = given()
			.contentType("application/json")
			.and().body(completeRequestObject.toString())
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
