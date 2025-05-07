package YaantracWeb.BaseTestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import YaantracWeb.resources.ExtentReporterNg;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest Test;
	ExtentReports extents = ExtentReporterNg.getReportObject();
	ThreadLocal <ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // To overcome overriding issue while running the test parallel
	
	@Override
	public void onTestStart(ITestResult result) {
		Test = extents.createTest(result.getMethod().getMethodName());
		extentTest.set(Test); // extentTest will provide unique thread id 
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test Passed");	
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 
		 String filePath = null;
		try {
			filePath = getScreenShort(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(org.testng.ITestContext context) {
	    extents.flush();
	}
	
	
	
}
