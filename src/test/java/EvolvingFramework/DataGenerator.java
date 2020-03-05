package EvolvingFramework;

import java.util.HashMap;
import java.util.Map;

public class DataGenerator {
	Map<String, String> data = new HashMap<String, String>();

	public Map<String, String> createEmpPayload(String name, String salary, String age) {
		data.put("name", name);
		data.put("salary", salary);
		data.put("age", age);

		return data;
	}

}
