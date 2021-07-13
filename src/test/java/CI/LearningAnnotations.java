package CI;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class LearningAnnotations {
	@Test
	public void f1() {
		System.out.println("In test f1");
	}

	@Test
	public void f2() {
		System.out.println("In test f2");
	}

//	@BeforeSuite
//	public void beforeSuite() {
//		System.out.println("In before suite");
//	}
//
//	@AfterSuite
//	public void tearDown() {
//		System.out.println("In after suite");
//	}

//	@BeforeClass
//	public void beforeClass() {
//		System.out.println("In before class");
//	}
//
//	@AfterClass
//	public void afterClass() {
//		System.out.println("In after class");
//	}

//	@BeforeMethod
//	public void beforeMethod() {
//		System.out.println("In Before Method");
//	}

//
//	@AfterMethod
//	public void afterMethod() {
//		System.out.println("In after Method");
//	}
//
//  @BeforeClass
//
//	@BeforeTest
//	public void beforeTest() {
//		System.out.println("In before test");
//	}
//
//	@AfterTest
//	public void afterTest() {
//		System.out.println("In after test");
//	}

}
