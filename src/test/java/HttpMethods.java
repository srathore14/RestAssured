import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpMethods {

	public static Response post(RequestSpecification spec, String url) {
		return spec.post(url).andReturn();
	}
}
