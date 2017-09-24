package appModules;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.CPQ_SelectCustomer_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class CPQ_SelectCustomer_Action extends BaseClass 
{
	public CPQ_SelectCustomer_Action(WebDriver driver) 
	{
		super(driver);
	}
	
	@Test
	public static String Execute_NewCustomer(int iTestCaseRow) throws Exception
	{
		try 
		{
			Utils.waitFor_ElementToBeVisible(CPQ_SelectCustomer_Page.txt_CustomerID_Input(),20);
			Utils.waitFor_PageLoad(driver);
			
			String txt_CustomerID_Input = CPQ_SelectCustomer_Page.txt_CustomerID_Input().getAttribute("value");
			CPQ_SelectCustomer_Page.btn_SelectCustomer_Submit().click();
			Log.info(txt_CustomerID_Input+" - Customer Successfully Selected");
			Reporter.log("Select Customer Action is successfully performed");
			return txt_CustomerID_Input;
		} 
		catch (Exception e) 
		{
			Log.error(e.getMessage());
			throw (e);
		}      
	}
	
	@Test
	public static String Execute_ExistingCustomer(int iTestCaseRow) throws Exception
	{
		try 
		{
			Utils.waitFor_ElementToBeVisible(CPQ_SelectCustomer_Page.txt_CustomerID_Input(),20);
			Utils.waitFor_PageLoad(driver);
			
			String Customer_ID_Excel = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_CustomerID);
			CPQ_SelectCustomer_Page.txt_CustomerID_Input().sendKeys(Customer_ID_Excel);
			
			String txt_CustomerID_Input = CPQ_SelectCustomer_Page.txt_CustomerID_Input().getAttribute("value");
			CPQ_SelectCustomer_Page.btn_SelectCustomer_Submit().click();
			Log.info(txt_CustomerID_Input+" - Customer Successfully Selected");
			Reporter.log("Select Customer Action is successfully performed");
			return txt_CustomerID_Input;
		} 
		catch (Exception e) 
		{
			Log.error(e.getMessage());
			throw (e);
		}      
	}
}