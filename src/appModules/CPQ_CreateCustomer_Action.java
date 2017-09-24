package appModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.CPQ_CreateCustomer_Page;
import pageObjects.CPQ_SelectCustomer_Page;
import utility.Log;
import utility.Utils;
import utility.Constant;
import utility.ExcelUtils;

public class CPQ_CreateCustomer_Action extends BaseClass 
{
	public CPQ_CreateCustomer_Action(WebDriver driver) 
	{
		super(driver);
	}

	static private String INI_FilePath = Constant.File_ObjectRepository +"Create_Customer.ini";
	static private String Section_eleValue_Name="Create_Cust_Value";
	
	@Test
	public static void Execute(int iTestCaseRow) throws Exception
	{
		long randomInt = Math.round(Math.random() * 89999) + 10000;

		try 
		{
			Log.info("Object Repository File Path for Create_Customer :"+INI_FilePath);
			Utils.waitFor_ElementToBeClickable(CPQ_CreateCustomer_Page.txt_CustomerID_Input(),10);
			CPQ_CreateCustomer_Page.btn_Menu().click();
			Thread.sleep(1000);

			Utils.waitFor_ElementToBeClickable(CPQ_CreateCustomer_Page.lnk_NewCustomer(),10);
			CPQ_CreateCustomer_Page.lnk_NewCustomer().click();
			Thread.sleep(1000);

			Utils.waitFor_PageLoad(driver);
			Utils.waitFor_ElementToBeClickable(CPQ_CreateCustomer_Page.drpDown_CustomerType(),10);
			CPQ_CreateCustomer_Page.txt_CustomerName().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_CustomerName"))+randomInt);	


			Utils.waitFor_ElementToBeClickable(CPQ_CreateCustomer_Page.drpDown_CustomerType(),10);
			CPQ_CreateCustomer_Page.drpDown_CustomerType().click();
			CPQ_CreateCustomer_Page.drpDown_CustomerType_Corporate().click();

			Utils.waitFor_ElementToBeClickable(CPQ_CreateCustomer_Page.drpDown_CustomerSubtype(),10);
			CPQ_CreateCustomer_Page.drpDown_CustomerSubtype().click();
			CPQ_CreateCustomer_Page.drpDown_CustomerSubtype_Federal().click();

			Utils.waitFor_ElementToBeClickable(CPQ_CreateCustomer_Page.drpDown_NumberEmployees(),10);
			CPQ_CreateCustomer_Page.drpDown_NumberEmployees().click();
			CPQ_CreateCustomer_Page.drpDown_NumberEmployees_10_99().click();


			CPQ_CreateCustomer_Page.txt_NumberSites().sendKeys(Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_NumberSites"));
			CPQ_CreateCustomer_Page.txt_Address1().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_Address1"))+randomInt);
			CPQ_CreateCustomer_Page.txt_Address2().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_Address2"))+randomInt);
			CPQ_CreateCustomer_Page.txt_City().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_City"))+randomInt);
			CPQ_CreateCustomer_Page.txt_ZipCode().sendKeys(String.valueOf(randomInt));
			CPQ_CreateCustomer_Page.txt_FirstName().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_FirstName"))+randomInt);
			CPQ_CreateCustomer_Page.txt_LastName().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_LastName"))+randomInt);
			CPQ_CreateCustomer_Page.txt_PhoneNumber().sendKeys((String.valueOf(randomInt))+(String.valueOf(randomInt)));
			CPQ_CreateCustomer_Page.txt_Email().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_Email"))+randomInt+"@Amdocs.com");
			CPQ_CreateCustomer_Page.txt_Email2().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleValue_Name, "txt_Email2"))+randomInt+"@Amdocs.com");

			new Actions(driver).moveToElement(CPQ_CreateCustomer_Page.btn_Create()).perform();
			CPQ_CreateCustomer_Page.btn_Create().click();

			Thread.sleep(3000);	

			Reporter.log("Create Customer Action is successfully performed");

			if (Utils.waitFor_ElementToBeVisible(CPQ_SelectCustomer_Page.txt_CustomerID_Input(),20))
			{
				Utils.waitFor_PageLoad(driver);
				String txt_CustomerID_Input = CPQ_SelectCustomer_Page.txt_CustomerID_Input().getAttribute("value");
				Log.info("Customer Sucessfully Created");
				Log.info("Customer ID is : "+txt_CustomerID_Input);
				ExcelUtils.setCellData(txt_CustomerID_Input, iTestCaseRow, Constant.Col_CustomerID);
				Log.info("Customer ID sucessfully added in : "+Constant.Path_TestData+Constant.File_TestData);
			}
			else
			{	
				Log.error("Customer Creation Failed");
				Utils.takeScreenshot(driver, "Create Customer");
			}
		} 
		catch (Exception e) 
		{
			Utils.takeScreenshot(driver, "Create Customer");
			Log.error(e.getMessage());
			throw (e);
		}            
	}	
}