import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.response.Response;

public class Validator {

	public static void validateResponseForStatusCode(int actualStatusCode,
			int expectedStatusCode) {
		Assert.assertEquals(actualStatusCode, expectedStatusCode);
	}

	public static void validateField(Response response, String fieldName,
			String expectedValue) {

		response.then().assertThat().body(fieldName, equalTo(expectedValue));
	}

}
