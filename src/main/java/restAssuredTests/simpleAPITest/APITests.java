package restAssuredTests.simpleAPITest;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITests {

	@BeforeTest
	public void setUp() {

		RestAssured.baseURI = "https://reqres.in/api/users";
	}

	// @Test
	public void testListOfUsersAndPrintResponse() {

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
	 * 
	 * deserialization-with-generics
	 */
	@Test
	public void EvaluateJsonPath() {

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
	 * Trying to get the values from the response based on some condition
	 * 
	 * Given response:
	 * {
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "name": "cerulean",
            "year": 2000,
            "color": "#98B2D1",
            "pantone_value": "15-4020"
        },        
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
	 */
	@Test
	public void testResponseItemsExtraction() {
		// Request Object
		RequestSpecification requestSpecification = given();
		// Response Object
		Response response = requestSpecification.request(Method.GET);

		JsonPath jsonPath = response.jsonPath();

		List<String> names = jsonPath.getList("data.findAll{it.name=='blue turquoise'}.year");
		for (String name : names) {
			System.out.println(name);
		}
	}

	/**
	 * https://reqres.in/api/users/2
	 */
	@Test
	public void testFirstNameAndLastName() {

		// Request Object
		RequestSpecification requestSpecification =  given().basePath("api/users/{userid}").pathParam("userid", 2);
		
		System.out.println(RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath );
		
		// Response Object
		Response response = requestSpecification.request(Method.GET);
		String jsonString = response.jsonPath().prettyPrint();
		JsonPath jsonPath = new JsonPath(jsonString).setRoot("data");

		// get first and last name
		String firstName = jsonPath.getString("first_name");
		String lastName = jsonPath.getString("last_name");

		Assert.assertEquals(firstName, "Janet", "The first Name is not as expected");
		Assert.assertEquals(lastName, "Weaver", "The last name is not as expected");

	}

	/**
	 * Create a user and verify if it was created successfully
	 */
	@Test
	public void testPOSTRequest() {

		// Request Object
		String name = "TestName";
		String body = "{\r\n" + "    \"name\": \"" + name + "\",\r\n" + "    \"job\": \"leader\"\r\n" + "}\r\n";
		RequestSpecification requestSpecification = given().basePath("api/users").body(body);

		// Response Object
		Response response = requestSpecification.request(Method.POST);

		// Validate the response code
		Assert.assertTrue(response.getStatusCode() == 201, "The response code is not 201");

	}

	/**
	 * 
	 * Another way to send the response as Hash Map instead of String in the body
	 * 
	 * { "name": "morpheus", "job": "zion resident" }
	 * 
	 * 
	 * Response: { "name": "morpheus", "job": "zion resident", "updatedAt":
	 * "2021-06-11T10:08:32.663Z" }
	 */
	@Test
	public void testPUTRequest() {

		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "morpheus");
		jsonAsMap.put("job", "zion resident");

		given().contentType(ContentType.JSON).body(jsonAsMap).when().put("/api/users?page=2").then().statusCode(200)
				.body("name", equalTo("morpheus"), "job", equalTo("zion resident"));

		// Another way to do the same
		given().contentType(ContentType.JSON).body(jsonAsMap).basePath("/api/users").queryParam("page", 2).put().then()
				.statusCode(200).body("name", equalTo("morpheus"), "job", equalTo("zion resident"));

	}
	
	/**
	 * Extracting data from response after validation
	 */
	@Test
	public void testExtractResponseData() {

		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "morpheus");
		jsonAsMap.put("job", "zion resident");

		String updatedTime = given().contentType(ContentType.JSON).body(jsonAsMap).when().put("/api/users?page=2").then()
				//verify the status code
				.statusCode(200)
				//Verify the diff parameters in the response body
				.body("name", equalTo("morpheus"), "job", equalTo("zion resident"))
				//extract the updated 
				.extract().path("updatedAt");
		
		System.out.println("Updated Time is" +updatedTime);
	}
	
	/**
	 * https://reqres.in/api/users/2
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testPATCHandExtractTheResponse() {

		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "morpheus");
		jsonAsMap.put("job", "zion resident");

		// Request Object
		Response response = given()
				.body(jsonAsMap)
				.when()
		.patch("api/users/2")
		.then().
		assertThat().statusCode(201)
		.and()
		.extract().response();
		System.out.println(RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath );
		
		JsonPath jsonPath = response.jsonPath();
		String name = jsonPath.getString("name");
		System.out.println("Following data was updated :"+name + jsonPath.getString("job"));

	}

}
