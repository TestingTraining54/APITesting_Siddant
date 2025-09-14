package day1;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
/*
 * given() - prereq
 * set cookies,set contenttype,set header,set the auth,set param
 * when() - http methods - GET/PUT/POST/Delete
 * then() - validation - status code,body,cookies,header
 * 
 * 
 */
public class HTTPMethods {
int id;
	@Test(priority=1)
	public void getUsers() {
		given()
			.header("x-api-key","reqres-free-v1")
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority=2)
	public void createUser() {
		HashMap<String,Object> data = new HashMap<>();
		data.put("name","bhagya");
		data.put("job", "IT Professional");
		Response res=given()
			.contentType("application/json")
			.body(data)
			.header("x-api-key","reqres-free-v1")
			//.header("Content-Type","application/json")
		.when()
			.post("https://reqres.in/api/users");
		
		JsonPath js = res.jsonPath();
		id=js.getInt("id");
		System.out.println(id);
		
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority=3)
	public void updateUser() {
		HashMap<String,Object> data = new HashMap<>();
		data.put("name","siddhant");
		data.put("job", "Student");
		given()
			.contentType("application/json")
			.body(data)
			.header("x-api-key","reqres-free-v1")
			//.header("Content-Type","application/json")
		.when()
			.put("https://reqres.in/api/users/"+id)		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	public void deleteUser() {
		given()
			.header("x-api-key","reqres-free-v1")
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
	
	
	
	
}
