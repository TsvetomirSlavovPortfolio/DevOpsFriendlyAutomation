package testCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utility.Constant;
import utility.DB_ReadWrite;
import utility.RESTAssured;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import utility.WindowsProcess;

import appModules.CPQ_CreateCustomer_Action;
import appModules.CPQ_CreateProposal_Action;
import appModules.CPQ_Login_Action;
import appModules.CPQ_SelectCustomer_Action;

public class CPQ_MAT_Flow
{
	public WebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	@BeforeMethod
	public void beforeMethod() throws Exception 
	{
		PropertyConfigurator.configure("log4j.properties");
		WindowsProcess.CheckProcessRunning();
		sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.startTestCase(sTestCaseName);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
		driver = Utils.OpenBrowser(iTestCaseRow);
		new BaseClass(driver);  
	}

	@Test
	public void main() throws Exception 
	{
		try
		{
			Log.info("\n---------------------------------------  Login Page Test Case Begins  --------------------------------------- \n");
			CPQ_Login_Action.Execute(iTestCaseRow);

			Log.info("\n---------------------------------------  Create Customer Page Test Case Begins  --------------------------------------- \n");
			CPQ_CreateCustomer_Action.Execute(iTestCaseRow);
			
			Log.info("\n---------------------------------------  Select Customer Page Test Case Begins  --------------------------------------- \n");
			String Customer_ID = CPQ_SelectCustomer_Action.Execute_NewCustomer(iTestCaseRow);
			
			//CPQ_SelectCustomer_Action.Execute_ExistingCustomer(iTestCaseRow);
			
			
			Log.info("\n---------------------------------------  Create Proposal Page Test Case Begins  --------------------------------------- \n");
			CPQ_CreateProposal_Action.Execute(iTestCaseRow);
			
			if(BaseClass.bResult==true)
			{
				ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
			}
			else
			{
				throw new Exception("Test Case Failed because of Verification");
			}
		}
		catch (Exception e)
		{
			ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
			Utils.takeScreenshot(driver, sTestCaseName);
			Log.error(e.getMessage());
			throw (e);
		}
	}

	@AfterSuite
	public void afterMethod() 
	{
		Log.endTestCase(sTestCaseName);
		driver.close();
	}

	static
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		System.setProperty("rootPath", System.getProperty("user.dir"));
	}
}
