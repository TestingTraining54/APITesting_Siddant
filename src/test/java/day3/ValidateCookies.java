package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
public class ValidateCookies {
/*
 * Client -- Server
 * Response+token - first request
 * 
 */
	
	@Test
	public void getCookieInfo() {
		Response res=
				given()
				.when()
					.get("https://google.com");
		
		String AECCookieValue=res.getCookie("AEC");
		System.out.println(AECCookieValue);
		
		Map<String, String> allCookies = res.getCookies();
		for(String cookieKey:allCookies.keySet()) {
			System.out.println("Cookie: " + cookieKey + " Cookie Value: " + res.getCookie(cookieKey));
		}
		
	}
}
