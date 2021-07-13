package Framework;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import DomainObjects.EmployeeInput;
import io.restassured.RestAssured;
import Framework.Listener;

@Listeners(Listener.class)
public class TestSuite {
	Properties properties;
	ObjectMapper mapper;

	static Logger logger = Logger.getLogger(TestSuite.class);

	@BeforeSuite
	public void init() throws IOException {
		PropertyReader pr = new PropertyReader();
		pr.readProperties();
		properties = pr.getProp();
		mapper = new ObjectMapper();
		RestAssured.baseURI = properties.getProperty("baseURI");
		RestAssured.basePath = "api/v1";
		DOMConfigurator.configure("log4j.xml");
	}

	@Test
	public void should_create_multiple_employees() throws IOException {

		// try {
		logger.info("Test Started..");
		String employeeJson = FileUtils.readFileToString(
				new File("C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\Employee.json"));
		EmployeeInput emp = mapper.readValue(employeeJson, EmployeeInput.class);
		emp.setAge("24");
		emp.setName("Abc");
		emp.setSalary("123345");
		String empJson = mapper.writeValueAsString(emp);
		given().body(empJson).post("/create");
		logger.info("Test Ended..");
//		} catch (Exception e) {
//			logger.error("Exception:{}", e);
//		}
	}

	@AfterClass
	public void publishOnSlack() throws IOException {

		Slack slack = Slack.getInstance();

		Map<String, Object> failureResults = Listener.failureResults;
		Map<String, Object> successResults = Listener.sucessResults;
		Map<String, Object> skippedResults = Listener.skippedResults;
		// String webhookUrl = System.getenv(
		// "https://hooks.slack.com/services/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");
		// // https://hooks.slack.com/services/T1234567/AAAAAAAA/ZZZZZZ

		@SuppressWarnings("unchecked")
		Map<String, Object> mappings = mapper.readValue(new File(
				"C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\ServiceUserMappings.json"),
				Map.class);

		Map<String, Object> newMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : mappings.entrySet()) {
			for (Map.Entry<String, Object> resultsEntry : failureResults.entrySet()) {
				if (entry.getKey().equals(resultsEntry.getKey())) {
					newMap.put(resultsEntry.getKey(), entry.getValue());
				}
			}
		}

		StringBuilder resultString = new StringBuilder();
		newMap.forEach((k, v) -> resultString
				.append("*" + k + "* is failed." + v + " please check the failures!\n"));
		;

		int totalTests = failureResults.size() + successResults.size() + skippedResults.size();

		String summaryString = String.format(
				"*Test Status*:\n\t Passed: %s, Failed: %s, Skipped: %s \n", successResults.size(),
				failureResults.size(), skippedResults.size());

		String finalPayload = summaryString + resultString.toString();
		Payload payload = Payload.builder().text(finalPayload).build();
		System.out.println("Payload:" + payload.toString());
		WebhookResponse response = slack.send(
				"https://hooks.slack.com/services/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",
				payload);
		// System.out.println(response);

	}

	@Test
	public void test2() {

		assertTrue(false);
	}

}
