package TestMethods;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITokenAuth {
	@Test
	public void getMethod() {
		String jsonAsString;
		Response ResponseObject;
		ResponseObject = null;
		ResponseObject = given()
				.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
				.queryParam("q", "Chennai")
				.queryParam("cnt", "2")
				.pathParam("version", "2.5")
				.contentType("application/json").and()
				.log().all()
				.when().get("https://api.openweathermap.org/data/{version}/forecast/daily")
				.then().and()
				.log().everything()
				.log().body()
				.statusCode(200)
				.extract().response();
		//Converting response as String
		jsonAsString = ResponseObject.asString();
		System.out.println(jsonAsString);
		System.out.println("***********");
		System.out.println(ResponseObject.getHeaders());
		System.out.println("***********");
		System.out.println(ResponseObject.getHeaders().getValue("X-Cache-Key"));
		System.out.println("***********");
		System.out.println(ResponseObject.getTime());
		System.out.println("***********");
		System.out.println(ResponseObject.getStatusLine());
		System.out.println("***********");
		System.out.println(ResponseObject.getStatusCode());
		System.out.println("***********");
		System.out.println(ResponseObject.getSessionId());
		System.out.println("***********");
		System.out.println(RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath);
	}
}
