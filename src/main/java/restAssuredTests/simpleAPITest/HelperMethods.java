package restAssuredTests.simpleAPITest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class HelperMethods {

	public void setUpTestURL(String baseURL) {
		RestAssured.baseURI = baseURL; //"https://reqres.in/api/users";
	}
	
	public RequestSpecBuilder buildReqstSpec(String pathParamName,Integer pathParamValue) {
		
		RequestSpecBuilder requestBuilder =  new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addPathParam(pathParamName, pathParamValue);
		return requestBuilder;
	}
	
	public ResponseSpecBuilder buildRspnSpec(int statusCode) {
		ResponseSpecBuilder rspnsBldr = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(statusCode);
		return rspnsBldr;
	}
}
