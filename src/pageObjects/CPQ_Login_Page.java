package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.Utils;

public class CPQ_Login_Page extends BaseClass 
{
	static private String INI_FilePath = Constant.File_ObjectRepository +"CPQ_Login.ini";
	static private String Section_eleProperty_Name="Login_Obj_Property";

	public CPQ_Login_Page(WebDriver driver)
	{
		super(driver);
	}     
	
	public static WebElement txt_UserName() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_UserName");
	}
	public static WebElement txt_Password() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_Password");
	}
	public static WebElement btn_Submit() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "btn_Submit");
	}
}
