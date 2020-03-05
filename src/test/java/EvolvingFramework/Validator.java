package EvolvingFramework;

import org.testng.Assert;

import io.restassured.response.ResponseBody;

public class Validator {

	public static void validateResponseCode(int statusCode, int scOk) {

		Assert.assertEquals(statusCode, scOk);
	}

	public static void validateResponseField(ResponseBody body, String field,
			String expectedValue) {

		String actualFieldValue = body.jsonPath().get(field);
		Assert.assertEquals(actualFieldValue, expectedValue);
	}
}
