package CI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class NewTest {
	@Test(groups = { "sanity", "regression" })
	public void f1() {

		System.out.println("in f1");
	}

	@Test(groups = { "regression" })
	public void f2() {
		System.out.println("in f2");

	}

	@Test(groups = { "smoke" })
	public void f3() {

		System.out.println("in f3");
	}

	@Parameters("url")
	@Test
	public void f4(@Optional("testurl") String url) {

		System.out.println("in f4: Url: " + url);
	}

	@Test(dataProvider = "provideData")
	public void createEmployee(String name, String salary, String age) {

		System.out.println("Name:" + name);
		System.out.println("Salary:" + salary);
		System.out.println("Age:" + age);

	}

	@DataProvider
	public String[][] provideData() {

		String[][] data = new String[2][3];
		data[0][0] = "abc";
		data[0][1] = "3333";
		data[0][2] = "30";

		data[1][0] = "xyz";
		data[1][1] = "22322";
		data[1][2] = "26";

		return data;

	}

}
