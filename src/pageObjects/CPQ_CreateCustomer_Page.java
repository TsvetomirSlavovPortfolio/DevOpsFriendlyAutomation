package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.Utils;

public class CPQ_CreateCustomer_Page extends BaseClass 
{
	static private String INI_FilePath = Constant.File_ObjectRepository +"Create_Customer.ini";
	static private String Section_eleProperty_Name="Create_Cust_Property";

	public CPQ_CreateCustomer_Page(WebDriver driver)
	{
		super(driver);
	}     

	public static WebElement txt_CustomerName() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_CustomerName");
	}
	public static WebElement txt_NumberSites() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_NumberSites");
	}
	public static WebElement txt_Address1() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_Address1");
	}
	public static WebElement txt_Address2() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_Address2");
	}
	public static WebElement txt_City() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_City");
	}
	public static WebElement txt_ZipCode() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_ZipCode");
	}
	public static WebElement txt_FirstName() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_FirstName");
	}
	public static WebElement txt_LastName() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_LastName");
	}
	public static WebElement txt_PhoneNumber() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_PhoneNumber");
	}	
	public static WebElement txt_Email() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_Email");
	}
	public static WebElement txt_Email2() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_Email2");
	}
	public static WebElement txt_CustomerID_Input() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_CustomerID_Input");
	}
	
	public static WebElement drpDown_CustomerType() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_CustomerType");
	}
	public static WebElement drpDown_CustomerType_Corporate() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_CustomerType_Corporate");
	}
	public static WebElement drpDown_CustomerSubtype() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_CustomerSubtype");
	}
	public static WebElement drpDown_CustomerSubtype_Federal() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_CustomerSubtype_Federal");
	}
	public static WebElement drpDown_NumberEmployees() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_NumberEmployees");
	}
	public static WebElement drpDown_NumberEmployees_10_99() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_NumberEmployees_10_99");
	}
	public static WebElement drpDown_StateOrProvince() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_StateOrProvince");
	}
	public static WebElement drpDown_Country() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "drpDown_Country");
	}
	
	
	public static WebElement btn_Menu() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "btn_Menu");
	}	
	public static WebElement btn_Create() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "btn_Create");
	}	
	
	
	public static WebElement lnk_NewCustomer() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "lnk_NewCustomer");
	}
}
