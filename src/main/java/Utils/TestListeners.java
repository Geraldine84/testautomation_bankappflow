package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class TestListeners extends Globals implements ITestListener
{
   // ExtentTest test;
    ExtentReports extent= ExtentReportNG.getReportObject();
   // ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public TestListeners() throws IOException {
    }

    //before all test execution initialise the extent test
    public void onTestStart(ITestResult result) {

        extentLog = extent.createTest(result.getMethod().getMethodName());
        //extentTest.set(test);

    }

    public void  onTestSkipped(ITestResult result) {

        extentLog.fail(result.getThrowable());
        extentLog.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        extentLog.info("Test failed but it is in defined success ratio " + result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {

        extentLog.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        //Screenshot
        extentLog.fail(result.getThrowable());
        String testMethodName = result.getMethod().getMethodName();

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           // extentTest.get().addScreenCaptureFromPath("."+getScreenShot(testMethodName, driver), result.getMethod().getMethodName());
            extentLog.addScreenCaptureFromPath(getScreenShot(testMethodName, driver));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //after all test execution completed
    public void onFinish(ITestContext context) {
        //flush() - to write or update test information to your report.
        extent.flush();

    }




}
