package Training.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Training.Resources.ExtentReporerNG;

public class Listeners extends BaseTest implements ITestListener

{        ExtentTest test;
		ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
		ExtentReports extent =ExtentReporerNG.getReportObject();
 		
	 	 public void onTestStart(ITestResult result) 
	 	 {
	 		test = extent.createTest(result.getMethod().getMethodName());
	 		extentTest.set(test);// it create one Thread Id here for error validation (Map will created back end
		  }

	 	 public void onTestSuccess(ITestResult result) 
	 	 {
	 		extentTest.get().log(Status.PASS, "Test Pass");
	 	 }

		  public void onTestFailure(ITestResult result) 
		 {
			  String filepath = null;
			  extentTest.get().log(Status.FAIL, "Test Failed");
			  extentTest.get().fail(result.getThrowable());
			  try 
			  {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			  } 
			  catch (Exception e) {
				e.printStackTrace();
			  }
			  try 
			  {
				filepath = getScreenShot(result.getMethod().getMethodName(),driver);
			  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
			 
			  extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		 }

		  public void onTestSkipped(ITestResult result)
		  {
		    // not implemented
		  }

		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
		  {
		    // not implemented
		  }

		  public void onTestFailedWithTimeout(ITestResult result) 
		  {
		    onTestFailure(result);
		  }

		  public void onStart(ITestContext context) 
		  {
		    // not implemented
		  }

		  public void onFinish(ITestContext context) 
		  {
		    extent.flush();
		  }
}
