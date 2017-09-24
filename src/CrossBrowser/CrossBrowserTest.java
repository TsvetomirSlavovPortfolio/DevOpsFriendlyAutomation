package CrossBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest 
{
	// Adding second commit
	WebDriver driver;

	@BeforeMethod
	@Parameters({"browser", "driverPath"})
	public void beforeTest(String browser, String driverPath) 
	{
		if (browser.equalsIgnoreCase("firefox")) 
		{
			System.out.println("launching firefox browser");
			driver = new FirefoxDriver();
		} 
		else if (browser.equalsIgnoreCase("chrome")) 
		{
			System.out.println("launching chrome browser");
			System.setProperty("webdriver.chrome.driver",driverPath);
			driver = new ChromeDriver();
		} 
		else if (browser.equalsIgnoreCase("ie")) 
		{
			System.out.println("launching ie browser");
			System.setProperty("webdriver.ie.driver",driverPath);
			driver = new InternetExplorerDriver();
		} 
		else 
		{
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}

	@Test
	public void login() throws InterruptedException 
	{		
		driver.manage().window().maximize();
		driver.get("http://stackoverflow.com");
	}

	@AfterMethod
	public void afterTest() 
	{
		try 	
		{
			driver.wait(5000);
			driver.quit();
		} 
		catch (Exception e) 
		{
			driver.quit();
		}
	}
}