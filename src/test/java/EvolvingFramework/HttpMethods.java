package EvolvingFramework;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpMethods {

	public Response post(String uri, Map<String, String> payload) {
		RequestSpecification spec = RestAssured.given().when().body(payload);
		return spec.post(uri);
	}
}
