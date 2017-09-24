package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class Utils 
{
	public static void main(String[] args) 
	{
		PropertyConfigurator.configure("log4j.properties");
/*		Start_PSR();
		Stop_PSR();*/
		
		for (int i = 0; i < 10; i++) 
		{
			System.out.println(Math.round(Math.random() * 89999) + 10000);
		}
	}
	
	
	public static void Start_PSR()
	{
		try 
		{
			Log.info("psr utility started");
			String path="cmd /c start "+Constant.Path_PSR+"\\start_psr.bat";
			Runtime rn=Runtime.getRuntime();
			rn.exec(path);
			Thread.sleep(5000);
		} 
		catch (IOException | InterruptedException e) 
		{
			e.printStackTrace();
			Log.error("Some Error occureed in the processing of psr utility while starting");
			Log.error(e.getMessage());
		}
	}
	
	public static void Stop_PSR()
	{
		try 
		{
			Log.info("psr utility stopped");
			String path="cmd /c start "+Constant.Path_PSR+"\\stop_psr.bat ^& exit";
			Runtime rn=Runtime.getRuntime();
			rn.exec(path);
			Thread.sleep(1000);
		} 
		catch (IOException | InterruptedException e) 
		{
			e.printStackTrace();
			Log.error("Some Error occureed in the processing of psr utility while stopping");
			Log.info(e.getMessage());
		}
	}


	
	public static WebDriver driver = null;

	public static WebDriver OpenBrowser(int iTestCaseRow) throws Exception
	{
		String sBrowserName;
		try
		{
			sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
			if(sBrowserName.equals("Mozilla"))
			{
				//driver = new FirefoxDriver();
				File pathBinary = new File("E:\\A Useful Software\\Browsers\\Mozilla Firefox\\firefox.exe");
				FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
				FirefoxProfile firefoxProfile = new FirefoxProfile();       
				driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
				Log.info("New Firefox driver initialized");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Log.info("Implicit wait applied on the driver for 10 seconds");
				driver.get(Constant.URL);
				Log.info("Web application launched successfully on URL - "+Constant.URL);
			}
			if(sBrowserName.equalsIgnoreCase("Chrome"))
			{
				driver = setChromeDriver();
				Log.info("New Chrome driver initialized");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Log.info("Implicit wait applied on the driver for 10 seconds");
				driver.get(Constant.URL);
				Log.info("Web application launched successfully on URL - "+Constant.URL);
			}
		}catch (Exception e)
		{
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;
	}

	public static String getTestCaseName(String sTestCase)throws Exception
	{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			return value;
		}catch (Exception e)
		{
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw (e);
		}
	}

	public static void mouseHoverAction(WebElement mainElement, String subElement)
	{

		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();
		if(subElement.equals("Accessories")){
			action.moveToElement(driver.findElement(By.linkText("Accessories")));
			Log.info("Accessories link is found under Product Category");
		}
		if(subElement.equals("iMacs")){
			action.moveToElement(driver.findElement(By.linkText("iMacs")));
			Log.info("iMacs link is found under Product Category");
		}
		if(subElement.equals("iPads")){
			action.moveToElement(driver.findElement(By.linkText("iPads")));
			Log.info("iPads link is found under Product Category");
		}
		if(subElement.equals("iPhones")){
			action.moveToElement(driver.findElement(By.linkText("iPhones")));
			Log.info("iPhones link is found under Product Category");
		}
		action.click();
		action.perform();
		Log.info("Click action is performed on the selected Product Type");
	}	

	public static void waitFor_ElementToBeSelected(WebElement element, int timeWait)
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, timeWait);
			wait.until(ExpectedConditions.elementToBeSelected(element));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}		

	public static boolean waitFor_ElementToBeVisible(WebElement element, int timeWait)
	{
		boolean flag;
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, timeWait);
			wait.until(ExpectedConditions.visibilityOf(element));
			flag = true;
		} 
		catch (Exception e) 
		{
			flag = false;
			e.printStackTrace();
			Log.error(e.getMessage());
		}
		return flag;
	}

	public static void waitFor_PresenceOfElementLocated(By element, int timeWait)
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, timeWait);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}

	public static void waitFor_ElementToBeClickable(WebElement element, int timeWait)
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, timeWait);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}	

	public static void waitFor_PageLoad(WebDriver wdriver) 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(wdriver, 60);
			Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() 
			{
				@Override
				public boolean apply(WebDriver input) 
				{
					return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
				}
			};
			wait.until(pageLoaded);
		} catch (Exception e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}

	public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(Constant.Path_ScreenShot + sTestCaseName +"_"+dateFormat.format(date)+".jpg"));	
		} catch (Exception e)
		{
			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
			throw new Exception();
		}
	}

	public static WebDriver setChromeDriver()
	{
		Map<String, Object> prefs = new HashMap<String, Object>();//To Turns off multiple download warning
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
		prefs.put("download.prompt_for_download", false);//Turns off download prompt
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);//To Stop Save password prompts

		ChromeOptions options1 = new ChromeOptions();
		options1.addArguments("chrome.switches","--disable-extensions");
		options1.addArguments("--disable-notifications");//To Disable any browser notifications
		options1.addArguments("disable-infobars");//To disable yellow strip info bar which prompts info messages
		options1.addArguments("test-type");
		options1.addArguments("start-maximized");
		options1.addArguments("--js-flags=--expose-gc");  
		options1.addArguments("--enable-precise-memory-info"); 
		options1.addArguments("--disable-popup-blocking");
		options1.addArguments("--disable-default-apps"); 
		options1.setExperimentalOption("prefs", prefs);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options1);
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		System.setProperty("webdriver.chrome.driver", "C:\\Java_Workspace\\lib\\chromedriver_win32\\chromedriver.exe");
		WebDriver chromeDriver = new ChromeDriver(cap); 
		return chromeDriver;
	}

	public static String readINIFile(String filePath,String Key, String Value)
	{
		try
		{
			String elementValue;
			elementValue =  new Ini(new File(filePath)).fetch(Key,Value);
			if ((elementValue.equals(null))||(elementValue =="")){}
			return elementValue;
		}catch (NullPointerException  e)
		{
			e.printStackTrace();
			Log.error(filePath +"File not able to retrieve value from Key = "+Key+" and Value = "+Value);
			Log.error(e.getMessage());
			return null;
		}catch (InvalidFileFormatException e)
		{
			e.printStackTrace();
			Log.error("InvalidFileFormatException Exception Thrown");
			Log.error(e.getMessage());
			return null;
		}catch (IOException e)
		{
			e.printStackTrace();
			Log.error("IOException Exception Thrown");
			Log.error(e.getMessage());
			return null;
		}
	}

	public static WebElement getElementDetails(String INI_FilePath,String Key, String Value)
	{
		WebElement webElement = null;
		String elementValue;		
		try 
		{
			elementValue =  new Ini(new File(INI_FilePath)).fetch(Key,Value);
			webElement = driver.findElement(By.xpath(elementValue));
			Log.info("Element - "+Value +" is found on page");
			Log.info("Element Section Name is :"+Key+" and Element Object Property is :"+elementValue);
			Log.info("---------------------------------------------------------------------------------------------------------------------------");
		}catch (NoSuchElementException  e)
		{
			e.printStackTrace();
			Log.error(INI_FilePath +"File not able to retrieve value from Key = "+Key+" and Value = "+Value);
			Log.error(e.getMessage());
			return null;
		}
		catch (NullPointerException  e)
		{
			e.printStackTrace();
			Log.error(INI_FilePath +"File not able to retrieve value from Key = "+Key+" and Value = "+Value);
			Log.error(e.getMessage());
			return null;
		}catch (InvalidFileFormatException e)
		{
			e.printStackTrace();
			Log.error("InvalidFileFormatException Exception Thrown");
			Log.error(e.getMessage());
			return null;
		}catch (IOException e)
		{
			e.printStackTrace();
			Log.error("IOException Exception Thrown");
			Log.error(e.getMessage());
			return null;
		}
		return webElement;
	}

}
