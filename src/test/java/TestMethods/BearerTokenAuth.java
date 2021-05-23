package TestMethods;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class BearerTokenAuth {

	@Test
	public void getMethod() {
		String jsonAsString;
		Response ResponseObject;
		ResponseObject = null;
		ResponseObject = given()
				.header("Authorization", "Bearer c6afc751dcf6b38abd566e00fd8339467e771981")
				.contentType("application/json").and()
				.when().get("https://api.github.com/users/sseenivasan89/repos")
				.then().and()
				.log().body()
				.statusCode(200).extract()
				.response();
		//Converting response as String
		jsonAsString = ResponseObject.asString();
		System.out.println(jsonAsString);
	}
}
