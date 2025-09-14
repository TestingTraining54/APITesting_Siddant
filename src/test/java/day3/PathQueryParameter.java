package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class PathQueryParameter {
/*
 * https://reqres.in/api/users?page=2
 * 
 */
	
	@Test
	public void getUser() {
		given()
			.pathParam("path1","api")
			.pathParam("path2", "users")
			.queryParam("page", 2)
			.header("x-api-key","reqres-free-v1")
		.when()
			.get("https://reqres.in/{path1}/{path2}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
