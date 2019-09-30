package TestMethods;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.response.Response;

public class GetMethod {
	@Test
	public void getMethod() {
	String jsonAsString;
	Response ResponseObject;
	ResponseObject = null;
	ResponseObject = given()
			.contentType("application/json").and()
			.when().get("https://reqres.in/api/users?page=2")
			.then().and()
			.log().body()
			.statusCode(200).extract()
			.response();
	//Converting response as String
	jsonAsString = ResponseObject.asString();
	System.out.println(jsonAsString);
	
	int statusCode = ResponseObject.getStatusCode();
    Assert.assertEquals(statusCode , 200);

	//Converting to JSON Object
	JsonObject jsonObject = new JsonParser().parse(jsonAsString).getAsJsonObject();
	String total = jsonObject.get("per_page").getAsString();
	System.out.println("per_page response value = "+total);
	Assert.assertEquals("6", total);
	
	//Reading Array Object From Response
	JsonArray arrayObject = jsonObject.getAsJsonArray("data");
	for (int i = 0; i < arrayObject.size(); i++) {
		String taskStatus = arrayObject.get(i).getAsJsonObject().get("id").getAsString();
		System.out.println(taskStatus);
	}
	
}
}
