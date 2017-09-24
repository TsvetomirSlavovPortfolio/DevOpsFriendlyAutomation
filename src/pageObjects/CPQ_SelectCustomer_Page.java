package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.Utils;

public class CPQ_SelectCustomer_Page extends BaseClass 
{
	static private String INI_FilePath = Constant.File_ObjectRepository +"Select_Customer.ini";
	static private String Section_eleProperty_Name="Select_Cust_Property";

	public CPQ_SelectCustomer_Page(WebDriver driver)
	{
		super(driver);
	}     

	public static WebElement txt_CustomerID_Input() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_CustomerID_Input");
	}

	public static WebElement btn_SelectCustomer_Submit() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "btn_SelectCustomer_Submit");
	}
}
