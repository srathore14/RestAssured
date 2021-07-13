package Framework;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

	public static Map<String, Object> failureResults = new HashMap<>();
	public static Map<String, Object> sucessResults = new HashMap<>();
	public static Map<String, Object> skippedResults = new HashMap<>();

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		sucessResults.put(result.getName(), ITestResult.SUCCESS);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		failureResults.put(result.getName(), ITestResult.FAILURE);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		skippedResults.put(result.getName(), ITestResult.SKIP);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
