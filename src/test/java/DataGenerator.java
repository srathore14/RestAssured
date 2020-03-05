import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import DomainObjects.EmployeeInput;

public class DataGenerator {

	public Map<String, String> data = new HashMap<String, String>();

	public Map<String, String> createEmpPayload(String name, String salary) {
		data.put("name", name);
		data.put("salary", salary);

		return data;
	}

	public EmployeeInput getEmployeePayload(String empJsonFilePath, String name, String salary,
			String age) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		String empJson = FileUtils.readFileToString(new File(empJsonFilePath));

		// Approach 1
		// empJson = empJson.replace("$name", name).replace("$age",
		// age).replace("$salary", "salary");

		// Approach 2

		EmployeeInput emp = mapper.readValue(empJson, EmployeeInput.class);
		emp.setName(name);
		emp.setSalary(salary);
		emp.setAge(age);
		return emp;
	}
}
