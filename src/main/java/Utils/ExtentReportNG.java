package Utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ExtentReportNG extends Globals
{
    static ExtentReports extent;

    public static ExtentReports getReportObject() throws IOException {

        String filename = directoryPath + File.separator + "Reports" + File.separator + "ExtentReport" + File.separator + getConfigData("ExtentReportName") + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(filename);
        reporter.config().setReportName("Test Automation Results");
        reporter.config().setDocumentTitle("Terragon Test Automation");

        extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Author", "Santhosh");
        return extent;

    }

}