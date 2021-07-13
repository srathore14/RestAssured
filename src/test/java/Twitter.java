
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.AuthenticationSpecificationImpl;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;

public class Twitter {

	RequestSpecification scheme;

	@BeforeClass
	public void init() {

		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses/";

//		
//		 scheme = given().auth().oauth("9BuTqcAbO2GeRE4U7dJbU4UAi", "r4CtdxE8swrOlSsyVgP92oz4LIgnDdZfP4KzO1l3s4IxRvClL2",
//				"1172521631542960128-97R2WVXXJPqYwoqTfg4wObBajgKje4", "EkkmNTBlnpev9I0uDRTEr4rnb0J3mh24VuLDGAk1TASYz");
//		
//		scheme.post(uri)

	}

	@Test
	public void postTweet() {

		given().auth()
				.oauth("9BuTqcAbO2GeRE4U7dJbU4UAi",
						"r4CtdxE8swrOlSsyVgP92oz4LIgnDdZfP4KzO1l3s4IxRvClL2",
						"1172521631542960128-97R2WVXXJPqYwoqTfg4wObBajgKje4",
						"EkkmNTBlnpev9I0uDRTEr4rnb0J3mh24VuLDGAk1TASYz")
				.param("status", "This is my Rest Assured tweet").post("update.json").then().log();

	}
}
