package Framework;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import DomainObjects.EmployeeInput;
import io.restassured.RestAssured;

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

		try {
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
		} catch (Exception e) {
			logger.error("Exception:{}", e);
		}
	}
}
