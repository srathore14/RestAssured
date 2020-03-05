import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import DomainObjects.EmployeeInput;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DummyRestExample2 {

	DataGenerator dataGenerator = new DataGenerator();

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "empData")
	public void createEmp(String name, String salary, String age) {
		Map<String, String> data = dataGenerator.createEmpPayload(name, salary);
		data.put("age", age);
		Response response = given().when().body(data).log().all()
				.post(Constants.baseUrl + Constants.V1_CREATE_API).andReturn();

		Validator.validateResponseForStatusCode(response.statusCode(), HttpStatus.SC_OK);
		Validator.validateField(response, Constants.BodyConstants.DATA_NAME, name);
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "empDataFromExcel")
	public void createEmpShouldReturnError(String name, String salary, String age) {

		Map<String, String> data = dataGenerator.createEmpPayload(name, salary);
		data.put("age", age);

		RequestSpecification spec = RestAssured.given().when().body(data);
		Response response = HttpMethods.post(spec, Constants.baseUrl + Constants.V1_CREATE_API);

		Validator.validateResponseForStatusCode(response.statusCode(), HttpStatus.SC_OK);
		Validator.validateField(response, Constants.BodyConstants.DATA_NAME, name);
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "empData")
	public void createEmpUsingPOJO(String name, String salary, String age) throws IOException {

		EmployeeInput emp = dataGenerator.getEmployeePayload(
				"C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\Employee.json", name, salary,
				age);

		Response response = given().when().body(emp).log().all()
				.post(Constants.baseUrl + Constants.V1_CREATE_API).andReturn();

		Validator.validateResponseForStatusCode(response.statusCode(), HttpStatus.SC_OK);
		Validator.validateField(response, Constants.BodyConstants.DATA_NAME, name);

	}

}
