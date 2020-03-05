package EvolvingFramework;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider
	public Object[][] empData() {

		Object[][] data = new Object[2][4];
		data[0][0] = "abc";
		data[0][1] = "50000";
		data[0][2] = "21";

		data[1][0] = "xyz";
		data[1][1] = "30000";
		data[1][2] = "31";

		return data;
	}
}
