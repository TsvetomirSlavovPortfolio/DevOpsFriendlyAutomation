package ExtentReports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport 
{
	ExtentHtmlReporter htmlReporter=null;
	ExtentReports extent=null;
	ExtentTest test=null;
	
	@BeforeTest
	public void startReport()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();	

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/resources/logs/ExtentReports/ExtentReport_"+dateFormat.format(date)+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS Info", "Windows 10");
		extent.setSystemInfo("User Name", "Rahul Kinge");
		extent.setSystemInfo("Host Name", "Swati Kinge");
		
		htmlReporter.config().setDocumentTitle("Automation Extent Report by Rahul Kinge");
		htmlReporter.config().setReportName("My Own Automation Extent Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	@Test
	public void demoTestPass()
	{
		test = extent.createTest("demoTestPass","This test will demonstrate the PASS test case");
		Assert.assertTrue(true);
	}
	
	@Test
	public void demoTestFail()
	{
		test = extent.createTest("demoTestFail","This test will demonstrate the FAIL test case");
		Assert.assertTrue(false);
	}
	
	@Test
	public void demoTestSkip()
	{
		test = extent.createTest("demoTestSkip","This test will demonstrate the SKIP test case");
		throw new SkipException("This test not is ready for execution");
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "Test Case Failed due to below issues", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if(result.getStatus()== ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ "Test Case is SUCCESS", ExtentColor.GREEN));
		}
		else
		{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "Test Case SKIPPED", ExtentColor.YELLOW));
			test.fail(result.getThrowable());
		}
	}
	
	@AfterTest
	public void tearDown()
	{
		extent.flush();
	}	
}