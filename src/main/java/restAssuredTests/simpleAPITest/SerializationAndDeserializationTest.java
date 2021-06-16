package restAssuredTests.simpleAPITest;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import restAssuredTests.simpleAPITest.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SerializationAndDeserializationTest {

HelperMethods helperMethods = new HelperMethods();

	@BeforeTest
	public void setUp() {
			helperMethods.setUpTestURL("https://reqres.in");
		
	}

	@Test
	public void testCreateUser() throws JsonProcessingException {
		
		String desiredName = "Sumit";
		
		ReqUser reqUser = new ReqUser();
		reqUser.setJob("TestQA");
		reqUser.setName(desiredName);

		RequestSpecification requestSpecification = given().body(reqUser);
				

		ResponseSpecification responseSpecification = helperMethods.buildRspnSpec(201).build();

		// Validate the response code		
		Response response = given().spec(requestSpecification)
		.log().uri()
		.when().post("api/users")
		.then()
		.spec(responseSpecification)
		.log().all()				
		.extract().response();
		
		//Validate the content of the response
		assertTrue(response.asString().contains(desiredName));

	}
	
	@Test
	public void testGetAllUserDetails() {			

		    RequestSpecification requestSpecification = given().body(helperMethods.buildReqstSpec("page", 1));
			// Response Object
			ResponseSpecification responseSpecification = helperMethods.buildRspnSpec(201).build();

			// Validate the response code		
			Response response = given().spec(requestSpecification)
			.log().uri()
			.when().get("/api/users")
			.then()
			.spec(responseSpecification)
			
			//
			.log().body()				
			.extract().response();

			// print the values in the console
			UsersDetails userDetails = response.as(UsersDetails.class);
			List<Datum> userData = userDetails.getData();
			Support userSupportData = userDetails.getSupport();
			
			System.out.println(userDetails.getPage());
			System.out.println(userData.get(0).getfirst_name());
			System.out.println(userSupportData.getUrl());
		
	}
}


