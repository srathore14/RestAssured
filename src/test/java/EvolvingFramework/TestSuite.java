package EvolvingFramework;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class TestSuite {

	ObjectMapper mapper = new ObjectMapper();
	DataGenerator dataGenerator = new DataGenerator();
	HttpMethods method = new HttpMethods();

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "empData")
	public void createEmp(String name, String salary, String age) {
		Map<String, String> data = dataGenerator.createEmpPayload(name, salary, age);
		Response response = method.post(Constants.baseUrl + Constants.V1_CREATE_API, data);

		Validator.validateResponseCode(
				method.post(Constants.baseUrl + Constants.V1_CREATE_API, data).getStatusCode(),
				HttpStatus.SC_OK);
		Validator.validateResponseField(
				method.post(Constants.baseUrl + Constants.V1_CREATE_API, data).getBody(),
				Constants.BodyConstants.DATA_NAME, name);
	}

//	@Test(dataProvider = "empData")
//	public void createEmpShouldReturnError(String name, String salary, String age) {
//
//		createEmpPayload(name, salary);
//		given().when().body(data).log().all().post("http://dummy.restapiexample.com/api/v1/create")
//				.then().log().all().assertThat().statusCode(400);
//	}
//
//	@Test(dataProvider = "empData")
//	public void createEmpUsingFileInput(String name, String salary, String age) throws IOException {
//
//		@SuppressWarnings("deprecation")
//		String jsonPayload = FileUtils.readFileToString(
//				new File("C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\Employee.json"));
//
//		jsonPayload = jsonPayload.replace("$name", name).replace("$salary", salary).replace("$age",
//				age);
//
//		given().when().body(jsonPayload).log().all()
//				.post(Constants.baseUrl + Constants.V1_CREATE_API).then().log().all().assertThat()
//				.statusCode(HttpStatus.SC_OK).and().body("data.name", equalTo(name));
//
//	}
//
//	@Test(dataProvider = "empData")
//	public void createEmpUsingFilePOJO(String name, String salary, String age) throws IOException {
//
//		@SuppressWarnings("deprecation")
//		String jsonPayload = FileUtils.readFileToString(
//				new File("C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\Employee.json"));
//
//		EmployeeInput employee = mapper.readValue(jsonPayload, EmployeeInput.class);
//		employee.setName(name);
//		employee.setAge(age);
//		employee.setSalary(salary);
//
//		given().when().body(employee).log().all().post(Constants.baseUrl + Constants.V1_CREATE_API)
//				.then().log().all().assertThat().statusCode(HttpStatus.SC_OK).and()
//				.body("data.name", equalTo(name));
//
//	}

}
