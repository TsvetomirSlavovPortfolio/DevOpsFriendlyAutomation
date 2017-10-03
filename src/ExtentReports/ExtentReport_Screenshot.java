package ExtentReports;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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

public class ExtentReport_Screenshot 
{
	String driverPath="C:\\Java_WorkSpace\\Drivers and Jars\\Drivers\\Chrome\\2.32\\chromedriver.exe";
	String htmlReportPath=System.getProperty("user.dir")+"/resources/logs/ExtentReports/";
	String htmlReportScreenshotPath=System.getProperty("user.dir")+"/resources/logs/ExtentReports/ScreenShot/";
	String screenshotExtension = ".jpg";	//e.g.    .jpg  or .png
	DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	Date date = new Date();	
	WebDriver driver=null;


	ExtentHtmlReporter htmlReporter=null;
	ExtentReports extent=null;
	ExtentTest test=null;

	@BeforeTest
	public void startReport()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();	

		htmlReporter = new ExtentHtmlReporter(htmlReportPath+"ExtentReport_ScreenShot_"+dateFormat.format(date)+".html");
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
	public void extentReport_Screenshot() 
	{
		try 
		{
			test = extent.createTest("extentReport_Screenshot()","This test will capture and save the screenshot in extentreports");
			System.out.println("Launching Chrome Browser");
			System.setProperty("webdriver.chrome.driver",driverPath);
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			driver.get("http://google.com");
			String homePageTitle = driver.getTitle();
			System.out.println("Google Home Page Title is : "+homePageTitle);
			Assert.assertEquals(homePageTitle, "Google");

			driver.findElement(By.name("q")).sendKeys("Rahul Kinge");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			String googleSearchTitle = driver.getTitle();
			System.out.println("Google Search Result Title is : "+googleSearchTitle);
			Assert.assertEquals(googleSearchTitle, "Rahul Kinge - Google Searc");
		} 
		catch (InterruptedException e) 
		{
			//driver.close();
			e.printStackTrace();
		}
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
		try 
		{
			if(result.getStatus()== ITestResult.FAILURE)
			{
				test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "Test Case Failed", ExtentColor.RED));
				if (driver != null) 
				{
					String screenShotPath = capture(driver,result.getName());
					test.fail("SnapShot Below: "+test.addScreenCaptureFromPath(screenShotPath));
				}
				test.fail(result.getThrowable());
			}
			else if(result.getStatus()== ITestResult.SUCCESS)
			{
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ "Test Case is SUCCESS", ExtentColor.GREEN));
				if (driver != null) 
				{
					String screenShotPath = capture(driver,result.getName());
					test.pass("SnapShot Below: "+test.addScreenCaptureFromPath(screenShotPath));
				}
			}
			else
			{				
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "Test Case SKIPPED", ExtentColor.YELLOW));
				if (driver != null) 
				{
					String screenShotPath = capture(driver,result.getName());
					test.skip("SnapShot Below: "+test.addScreenCaptureFromPath(screenShotPath));
				}
				test.skip(result.getThrowable());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@AfterClass
	public void tearDown()
	{
		extent.flush();
		driver.close();
		driver.quit();
	}	


	public String capture(WebDriver driver, String screenShotName)
	{
		String destinationImage="";
		try 
		{
			TakesScreenshot screenShot= (TakesScreenshot) driver;
			File source = screenShot.getScreenshotAs(OutputType.FILE);
			destinationImage = htmlReportScreenshotPath+screenShotName+dateFormat.format(date)+screenshotExtension;
			File destinationPath = new File(destinationImage);
			FileUtils.copyFile(source,destinationPath);	
			System.out.println("ScreenShot Captured...." + destinationImage);
		} 
		catch (WebDriverException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return destinationImage;
	}

}