package appModules;

import org.ini4j.Ini;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageObjects.Sahi_Registration_Page;
import utility.Log;
import utility.Utils;

public class Sahi_Registration_Action 
{
	static String Login_INI_FilePath = System.getProperty("user.dir")+"\\Object_Repository\\Registration.ini";
	public static Ini ini=null;

	@Test
	public static void Execute(int iTestCaseRow) throws Exception
	{
		Log.info("Object Repository File Name :"+Login_INI_FilePath);
		System.out.println("Object Repository File Name :"+Login_INI_FilePath);

		Sahi_Registration_Page.txt_UserName().sendKeys(Utils.readINIFile(Login_INI_FilePath, "User_Details_Value", "txt_UserName"));
		Log.info("Username "+Utils.readINIFile(Login_INI_FilePath, "User_Details_Value", "txt_UserName")+"  successfully entered on login screen" );

		Sahi_Registration_Page.txt_Password().sendKeys(Utils.readINIFile(Login_INI_FilePath, "User_Details_Value", "txt_Password"));
		Log.info("Password "+Utils.readINIFile(Login_INI_FilePath, "User_Details_Value", "txt_Password")+" successfully entered on login screen" );

		Log.info("Click action is performed on Submit button");

		Reporter.log("SignIn Action is successfully perfomred");            
	}
}