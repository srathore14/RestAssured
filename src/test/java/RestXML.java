import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestXML {

	@Test
	public void testGetPetHeaders() {

		Response response = RestAssured.with().header("accept", "application/xml")
				.get("https://petstore.swagger.io/v2/pet/10").andReturn();
		response.then().assertThat().statusCode(200).contentType("application/xml");

		// String node =
		// response.then().extract().xmlPath().getString("Pet.category.name");
		String node = response.then().extract().path("Pet.category.name");
		System.out.println("Value:" + node);
	}

	@Test
	public void testGet() {

		Response response = RestAssured.with().header("accept", "application/xml")
				.get("https://petstore.swagger.io/v2/pet/5").andReturn();
		response.then().assertThat().statusCode(200).contentType("application/xml");

		// String node =
		// response.then().extract().xmlPath().getString("Pet.category.name");
		response.then().assertThat().body(hasXPath("//status", equalTo("sold")));
		List<Object> list = response.then().extract().xmlPath().getList("Pet.photoUrls.photoUrl");
		System.out.println(list);
		// System.out.println("Value:" + node);
	}

	@Test
	public void valiadateResponseTime() {

		RestAssured.given().get("https://petstore.swagger.io/v2/pet/10").then().time(lessThan(2L), TimeUnit.SECONDS);
	}

	@Test
	public void deletePet() {
		RestAssured.given().header("Authorization", "Bearer 43924b7a-5d96-415e-8466-c1cc60028c6e")
				.delete("https://petstore.swagger.io/v2/pet/5").then().assertThat().statusCode(200).log().ifError();
	}
}
