package pageObjects;
import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.openqa.selenium.*;
import utility.Log;
import utility.Utils;
public class Sahi_Registration_Page extends BaseClass 
{
	private static WebElement element = null;
	static Ini iniFile=null;
	static String Login_INI_FilePath = System.getProperty("user.dir")+"\\Object_Repository\\Registration.ini";

	public Sahi_Registration_Page(WebDriver driver)
	{
		super(driver);
	}     

	public static WebElement txt_UserName() throws Exception
	{
		//return getElementDetails("UserName Text Box ",getElement_Property("User_Details_Property", "txt_UserName"));
		return Utils.getElementDetails(Login_INI_FilePath, "User_Details_Property", "txt_UserName");
	}
	public static WebElement txt_Password() throws Exception
	{
		return Utils.getElementDetails(Login_INI_FilePath, "User_Details_Property", "txt_Password");
	}
	public static WebElement txt_RepeatPassword() throws Exception
	{
		return Utils.getElementDetails(Login_INI_FilePath, "User_Details_Property", "txt_RepeatPassword");
	}
	public static WebElement btn_LogIn() throws Exception
	{
		return getElementDetails("UserName Text Box ",iniFile.fetch("User_Details_Property", "txt_UserName"));
	}
	public static WebElement getElementDetails(String eleName, String eleProperty) throws Exception
	{
		System.out.println("Element Name is - "+eleName);
		System.out.println("Element Object Property is - "+eleProperty);
		try
		{
			element = driver.findElement(By.xpath(eleProperty));
			Log.info(eleName +" is found on the Login page");
		}catch (Exception e)
		{
			Log.error(eleName+" is not found on the Login Page");
			throw(e);
		}
		return element;
	}
	public static String getElement_Property(String Key, String Value)
	{
		try
		{
			String elementValue;
			elementValue =  new Ini(new File(Login_INI_FilePath)).fetch(Key,Value);
			if ((elementValue.equals(null))||(elementValue =="")){}
			return elementValue;
		}catch (NullPointerException  e)
		{
			e.printStackTrace();
			System.out.println(Login_INI_FilePath +" File unable to retrieve data from Key = "+Key+" and Value = "+Value);
			Log.error(Login_INI_FilePath +"File not able to retrieve value from Key = "+Key+" and Value = "+Value);
			return null;
		}catch (InvalidFileFormatException e)
		{
			e.printStackTrace();
			System.out.println("InvalidFileFormatException Exception Thrown");
			Log.error("InvalidFileFormatException Exception Thrown");
			return null;
		}catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("IOException Exception Thrown");
			Log.error("IOException Exception Thrown");
			return null;
		}
	}
}
