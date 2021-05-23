package TestMethods;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class BasicAuth {

	@Test
	public void getMethod() {
		String jsonAsString;
		Response ResponseObject;
		ResponseObject = null;
		ResponseObject = given()
				.auth()
				.basic("postman", "password")
				.contentType("application/json").and()
				.when().get("https://postman-echo.com/basic-auth/")
				.then().and()
				.log().body()
				.statusCode(200).extract()
				.response();
		//Converting response as String
		jsonAsString = ResponseObject.asString();
		System.out.println(jsonAsString);
	}
}
