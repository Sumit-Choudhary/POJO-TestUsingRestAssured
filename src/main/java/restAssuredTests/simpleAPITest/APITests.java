package restAssuredTests.simpleAPITest;

import static io.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITests {

	// @Test
	public void testListOfUsersAndPrintResponse() {
		// Base URI
		RestAssured.baseURI = "https://reqres.in/api/users";

		// Request Object
		RequestSpecification requestSpecification = given();

		// Response Object
		Response response = requestSpecification.request(Method.GET);

		// print the values in the console
		System.out.println(response.getBody().asPrettyString());
	}

	/**
	 * Trying to evalute the response of the following URL =
	 * https://reqres.in/api/users?page=2 Base uri = https://reqres.in Path =
	 * api/users Query paramter = page
	 */
	@Test
	public void EvaluateJsonPath() {
		// Base URI
		RestAssured.baseURI = "https://reqres.in";

		// Request Object
		RequestSpecification requestSpecification = given().basePath("api/users").queryParam("page", 2);

		// Response Object
		Response response = requestSpecification.request(Method.GET);

		JsonPath jsonPath = response.jsonPath();
		// Get respective value from the json path
		int pages = jsonPath.get("data[1].id");
		System.out.println(pages);

		// get all the ids
		System.out.println(jsonPath.getList("data.id"));

		// get the values under 'data' tag in a MAP
		List<Map<String, String>> data = response.jsonPath().getList("data");
		// Iterate and print all the values
		for (int index = 0; index < data.size(); index++) {
			Map<String, String> currDataMap = data.get(index);
			Set currKey = currDataMap.keySet();
			Iterator<String> iterator = currKey.iterator();
			System.out.println("Printing for index:" + index);
			while (iterator.hasNext()) {
				String key = iterator.next();
				System.out.println("Key: " + key + " Value: " + String.valueOf(currDataMap.get(key)));
			}
		}

	}

	/**
	 * https://reqres.in/api/users/2
	 */
	@Test
	public void testFirstNameAndLastName() {
		// Base URI
		RestAssured.baseURI = "https://reqres.in";

		// Request Object
		RequestSpecification requestSpecification = given().basePath("api/users/{userid}").pathParam("userid", 2);

		// Response Object
		Response response = requestSpecification.request(Method.GET);
		JsonPath jsonPath = response.jsonPath();

		// get first and last name
		String firstName = jsonPath.getString("data.first_name");
		String lastName = jsonPath.getString("data.last_name");

		Assert.assertEquals(firstName, "Janet", "The first Name is not as expected");
		Assert.assertEquals(lastName, "Weaver", "The last name is not as expected");

	}

	/**
	 * Create a user and verify if it was created successfully
	 */
	@Test
	public void testPOSTRequest() {

		// Base URI
		RestAssured.baseURI = "https://reqres.in";
		// Request Object
		String name = "TestName";
		String body = "{\r\n" + "    \"name\": \"" + name + "\",\r\n" + "    \"job\": \"leader\"\r\n" + "}\r\n";
		RequestSpecification requestSpecification = given().basePath("api/users").body(body);
		
		
		// Response Object
		Response response = requestSpecification.request(Method.POST);
		JsonPath jsonPath = response.jsonPath();

		// Validate the response code
		Assert.assertTrue(response.getStatusCode() == 201, "The response code is not 201");

	}

}
