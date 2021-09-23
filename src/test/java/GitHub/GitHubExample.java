package GitHub;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
public class GitHubExample {
  @Test(enabled=false)
  public void getAllRepository(){
	  
		RestAssured.given()
			.auth()
			.oauth2("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")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.log()
			.body()
			.statusCode(200);
	}
  @Test(enabled=true)
  public void createRepository(){
	  JSONObject para=new JSONObject();
	  para.put("name", "RestAssuredToolCreatedMe5")	  ;
	  para.put("discription", "Sample for Post Request");
	  para.put("homepage", "http://github/anjalipatil19") ;
	  	
	  RestAssured.given()
	  		.auth()
	  		.oauth2("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")
	  		.header("Content-Type", "application/json")
	  		.body(para.toJSONString())
	  .when()
	  		.post("https://api.github.com/user/repos")
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(422);
	  }
}
