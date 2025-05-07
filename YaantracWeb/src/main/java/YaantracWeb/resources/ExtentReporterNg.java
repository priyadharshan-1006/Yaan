package YaantracWeb.resources;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNg {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("Yaantrac Test");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports Extent = new ExtentReports();
		Extent.attachReporter(reporter);
		Extent.setSystemInfo("Tester", "Priyadharshan");
		return Extent;
		
		
	}

}
