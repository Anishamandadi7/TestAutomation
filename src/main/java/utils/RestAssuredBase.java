package utils;

import java.util.HashMap;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBase {
	public Response response;
	RequestSpecification request = RestAssured.given();

	public RestAssuredBase() {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/";
	}

	public HashMap<String, String> getHeaders() {

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public void get(String endPoint) {

		response = RestAssured.given().headers(getHeaders()).get(endPoint);
		System.out.println(response.getBody().asString());
	}

	public void post(String endPoint, String body) {

		response = RestAssured.given().headers(getHeaders()).body(FileReaderUtil.readFile(body)).post(endPoint);
	}

	public void delete(String endPoint) {
		response = RestAssured.given().headers(getHeaders()).delete(endPoint);
	}

	public void put(String endPoint, String body) {
		response = RestAssured.given().headers(getHeaders()).body(FileReaderUtil.readFile(body)).put(endPoint);
		
	}

	public String getReponse() {
		return response.getBody().asString();
	}

	public void validate200(ExtentTest test) {
		boolean result = 200 == response.getStatusCode();
		if (result)
			test.pass("validated 200 response code");
		else
			test.fail("expected response code 200 not equal to actual response code " + response.getStatusCode());
		Assert.assertEquals(200, response.getStatusCode());
	}

}
