package restAssuredTests.simpleAPITest;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SerializationAndDeserializationTest {

	@BeforeTest
	public void setUp() {

		RestAssured.baseURI = "https://reqres.in/api/users";
	}

	@Test
	public void testCreateUser() throws JsonProcessingException {
		ReqUser reqUser = new ReqUser();
		reqUser.setJob("TestQA");
		reqUser.setName("Sumit");


		RequestSpecification requestSpecification = given().basePath("api/users").body(reqUser);

		// Response Object
		Response response = requestSpecification.request(Method.POST);

		// Validate the response code
		Assert.assertTrue(response.getStatusCode() == 201, "The response code is not 201");

	}
	
	@Test
	public void testGetAllUserDetails() {

			// Request Object
			RequestSpecification requestSpecification = given();

			// Response Object
			Response response = requestSpecification.request(Method.GET);

			// print the values in the console
			UsersDetails userDetails = response.as(UsersDetails.class);
			List<Datum> userData = userDetails.getData();
			Support userSupportData = userDetails.getSupport();
			
			System.out.println(userDetails.getPage());
			System.out.println(userData.get(0).getfirst_name());
			System.out.println(userSupportData.getUrl());
		
	}
}


