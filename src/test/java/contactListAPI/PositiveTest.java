package contactListAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;

public class PositiveTest {
	
	public String id=null;
	@Test(enabled=false, description="getting all contact information")
	public void gettingAllContact(){
	/*	Given: Some Pre-condition
		When : User Performs some operation
		Then: Some Post Condition*/

		//
		System.out.println("getting all contact information");
		RestAssured.
		given()
			.when()
				.get("http://3.13.86.142:3000/contacts")
			.then()
				.log()
				.body()
				.statusCode(200);
	}
	
	@Test(enabled=true, description="Add specific contact ")
	public void AddContact(){

		System.out.println("Adding contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");

		loc.put("city","Mumbai");
		loc.put("country","India");

		data.put("firstName","Navin");
		data.put("lastName","Sharma");
		data.put("email","abc@gmail.com");
		data.put("location",loc);
		data.put("employer", emp);

		id=RestAssured.given()
			.header("content-Type", "application/json")
			.body(data.toJSONString())
		.when()
			.post("http://3.13.86.142:3000/contacts")
		.then()
			.log()
			.body()
			.statusCode(200)
			.extract().path("_id");
			
		System.out.println("ID is  "+ id);
	}
	
	@Test(enabled=true,dependsOnMethods="AddContact", description="Get specific contact ")
	public void getSpecificContact(){

		System.out.println("getting contact");
		RestAssured.given()
		.when()
			.get("http://3.13.86.142:3000/contacts/"+id)
		.then()
				.log()
				.body()
				.statusCode(200);
		System.out.println("ID is  "+ id);
	}
	
	@Test(enabled=true, dependsOnMethods="getSpecificContact", description="update contact ")
	public void UpdateContact(){

		System.out.println("Updating contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");

		loc.put("city","Mumbai");
		loc.put("country","India");

		data.put("firstName","Anjali");
		data.put("lastName","Patil");
		data.put("email","anjalipatil7757@gmail.com");
		data.put("location",loc);
		data.put("employer", emp);

		RestAssured.given()
			.header("content-Type", "application/json")
			.body(data.toJSONString())
		.when()
			.put("http://3.13.86.142:3000/contacts/"+id)
		.then()
			.log()
			.body()
			.statusCode(204);
	}
	
	@Test(enabled=true,dependsOnMethods="UpdateContact",description="Get Specific Contact")
	public void deleteContact() {
		System.out.println("Deleting Contact");
		RestAssured.given()
					.when()
						.delete("http://3.13.86.142:3000/contacts/"+id)
					.then()
						.log()			//Print
						.body()
						.statusCode(204);   // Assertion
	}
}
