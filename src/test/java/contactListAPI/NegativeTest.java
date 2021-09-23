package contactListAPI;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
	
public class NegativeTest {
	public String id=null;
	@Test(enabled=true, description="getting contact Which does not Exist")
	public void gettingAllContact(){
	/*	Given: Some Pre-condition
		When : User Performs some operation
		Then: Some Post Condition*/

		//
		System.out.println("getting contact Which does not Exist");
		RestAssured.
		given()
			.when()
				.get("http://3.13.86.142:3000/contacts/5")
			.then()
				.log()
				.body()
				.statusCode(404);
	}
	@Test(enabled=true, description="Add contact with missing email")
	public void AddContactWithMissingEmailID(){

		
		System.out.println("Add contact with missing email");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");

		loc.put("city","Mumbai");
		loc.put("country","India");

		data.put("firstName","Navin");
		data.put("lastName","Sharma");
		//data.put("email","abc@gmail.com");
		data.put("location",loc);
		data.put("employer", emp);

		String err=RestAssured.given()
			.header("content-Type", "application/json")
			.body(data.toJSONString())
		.when()
			.post("http://3.13.86.142:3000/contacts")
		.then()
			.log()
			.body()
			.statusCode(400)
			.extract().path("err");
		Assert.assertEquals(err,"Contacts validation failed: email: Email is required");
			
		//System.out.println("ID is  "+ id);
	}
	
	@Test(enabled=true, description="Add contact with more character")
	public void AddContactWithMoreCharacter(){

		
		System.out.println("Add contact with more character");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");

		loc.put("city","Mumbai");
		loc.put("country","India");

		data.put("firstName","NavinNavinNavinNavinNavinNavinNavin");
		data.put("lastName","Sharma");
		data.put("email","abc@gmail.com");
		data.put("location",loc);
		data.put("employer", emp);

		String err=RestAssured.given()
			.header("content-Type", "application/json")
			.body(data.toJSONString())
		.when()
			.post("http://3.13.86.142:3000/contacts")
		.then()
			.log()
			.body()
			.statusCode(400)
			.extract().path("err");
		Assert.assertEquals(err.contains("is longer than the maximum allowed length"), true);
		
	}
	
	@Test(enabled=true, description="Add invalid character")
	public void InvalidCharacter(){

		
		System.out.println("Add contact with more character");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");

		loc.put("city","Mumbai");
		loc.put("country","India");

		data.put("firstName",676767);
		data.put("lastName","Sharma");
		data.put("email","abc@gmail.com");
		data.put("location",loc);
		data.put("employer", emp);

		String err=RestAssured.given()
			.header("content-Type", "application/json")
			.body(data.toJSONString())
		.when()
			.post("http://3.13.86.142:3000/contacts")
		.then()
			.log()
			.body()
			.statusCode(400)
			.extract().path("err");
		Assert.assertEquals(err.contains("Validator failed for path"),true);
		
	}

	@Test(enabled=true, description="Add improper format")
	public void ImproperFormat(){

		
		System.out.println("Add contact improper format");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");

		loc.put("city","Mumbai");
		loc.put("country","India");

		data.put("firstName","Anjali");
		data.put("lastName","Sharma");
		data.put("email","abcgmail.com");
		data.put("location",loc);
		data.put("employer", emp);

		String err=RestAssured.given()
			.header("content-Type", "application/json")
			.body(data.toJSONString())
		.when()
			.post("http://3.13.86.142:3000/contacts")
		.then()
			.log()
			.body()
			.statusCode(400)
			.extract().path("err");
		System.out.println(err);
		Assert.assertEquals(err.contains("Contacts validation failed: email:"),true);
		
	}
	
	
}
