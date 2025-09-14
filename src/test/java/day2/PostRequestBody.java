package day2;

import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
public class PostRequestBody {

	/*
	 1.	HashMap
     2. Using org.json
	 3.Using POJO(Plain Old Java Object) class
	 4.Using external json file
	 * 
	 * {
"id": "1",
"name": "John",
"location": "india",
"phone": "1234567890",
"courses": [
"Java",
"Selenium"
]
}
	 */
	
	@Test
	public void postBody1() {
		
		HashMap<String,Object> hm = new HashMap<>();
		hm.put("name", "Jasmin");
		hm.put("location", "delhi");
		hm.put("phone", "2343432432");
		String[] coursesInfo = {"C#","python"};
		hm.put("courses", coursesInfo);
		hm.put("id",5);
		given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.body("phone",equalTo("2343432432"))
			.body("name", equalTo("Jasmin"))
			.body("location", equalTo("delhi"))
			.body("courses[0]", equalTo("C#"))
			.body("courses[1]", equalTo("python"))
			.log().all();
	}
	
	//@Test
	public void postBody2() {
		JSONObject data = new JSONObject();
		data.put("name","kishore");
		data.put("location","hyderabad");
		data.put("phone", "56523128538");
		data.put("id",6);
		String[] coursesInfo = {"c","java"};
		data.put("courses", coursesInfo);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.body("phone",equalTo("56523128538"))
			.and()
			.body("name", equalTo("kishore"))
			.and()
			.body("location", equalTo("hyderabad"))
			.and()
			.body("courses[0]", equalTo("c"))
			.and()
			.body("courses[1]", equalTo("java"))
			.log().all();
	}
	
	//@Test
	public void postBody3() {
		
		Student st = new Student();
		st.setId(7);
		String[] coursesInfo = {"ai","python"};
		st.setCourses(coursesInfo);
		st.setName("ryam");
		st.setLocation("pune");
		st.setPhone("7637192989");
		given()
			.contentType("application/json")
			.body(st)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.body("phone",equalTo("7637192989"))
			.body("name", equalTo("ryam"))
			.body("location", equalTo("pune"))
			.body("courses[0]", equalTo("ai"))
			.body("courses[1]", equalTo("python"))
			.log().all();
	}
	
	@Test
	public void postBody4() throws FileNotFoundException  {
		FileInputStream fis;
		fis = new FileInputStream("./src/test/resources/day2/data.json");
		
		
	given()
		//.contentType("application/json")
		.contentType(ContentType.JSON)
		.body(fis)
	.when()
		.post("http://localhost:3000/students")
	.then()
		.log().all();
	}
}
