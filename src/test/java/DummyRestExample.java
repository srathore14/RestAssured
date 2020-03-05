import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DummyRestExample {

	Map<String, String> data = new HashMap<String, String>();

	@Test(dataProvider = "empData")
	public void createEmp(String name, String salary, String age) {

		createEmpPayload(name, salary);
		data.put("age", age);
		given().when().body(data).log().all().post(Constants.baseUrl + Constants.V1_CREATE_API).then().log().all()
				.assertThat().statusCode(HttpStatus.SC_OK).and().body("data.name", equalTo(name));

	}

	@Test(dataProvider = "empData")
	public void createEmpShouldReturnError(String name, String salary, String age) {

		createEmpPayload(name, salary);
		given().when().body(data).log().all().post("http://dummy.restapiexample.com/api/v1/create").then().log().all()
				.assertThat().statusCode(400);
	}

	private void createEmpPayload(String name, String salary) {
		data.put("name", name);
		data.put("salary", salary);
	}

	@DataProvider
	public Object[][] empData() {

		Object[][] data = new Object[2][3];
		data[0][0] = "abc";
		data[0][1] = "50000";
		data[0][2] = "21";

		data[1][0] = "xyz";
		data[1][1] = "30000";
		data[1][2] = "31";

		return data;
	}

}
