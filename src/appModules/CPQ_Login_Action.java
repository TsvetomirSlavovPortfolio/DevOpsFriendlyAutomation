package appModules;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.CPQ_Login_Page;
import utility.Constant;
import utility.Log;
import utility.Utils;


public class CPQ_Login_Action extends BaseClass 
{
	public CPQ_Login_Action(WebDriver driver) 
	{
		super(driver);
	}

	static private String INI_FilePath = Constant.File_ObjectRepository +"CPQ_Login.ini";
	static private String Section_eleProperty_Name="Login_Obj_Value";

	@Test
	public static void Execute(int iTestCaseRow) throws Exception
	{
		try 
		{
			Log.info("Object Repository File Path for CPQ Login Page :"+INI_FilePath);

			CPQ_Login_Page.txt_UserName().sendKeys(Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_UserName"));
			Log.info("Username Asmsa1 successfully entered on CPQ Login screen" );

			CPQ_Login_Page.txt_Password().sendKeys(Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_Password"));
			Log.info("Password Asmsa1 successfully entered on CPQ Login screen" );

			CPQ_Login_Page.btn_Submit().click();
			Log.info("Click on Submit Login Button is performed on CPQ Login screen");

			Reporter.log("Submit Login Action is successfully performed");  

		} 
		catch (Exception e) 
		{
			Log.error(e.getMessage());
			throw (e);
		}  
	}	
}