package appModules;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.CPQ_CreateProposal_Page;
import pageObjects.CPQ_SelectCustomer_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;


public class CPQ_CreateProposal_Action extends BaseClass 
{
	public CPQ_CreateProposal_Action(WebDriver driver) 
	{
		super(driver);
	}

	static private String INI_FilePath = Constant.File_ObjectRepository +"Create_Proposal.ini";
	static private String Section_eleProperty_Name="Create_Proposal_Value";

	@Test
	public static void Execute(int iTestCaseRow) throws Exception
	{
		long randomInt = Math.round(Math.random() * 89999) + 10000;
		try 
		{
			Log.info("Object Repository File Path for CPQ Create Proposal :"+INI_FilePath);

			CPQ_CreateProposal_Page.txt_ProposalName().sendKeys((Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_ProposalName"))+randomInt);
			Log.info("Proposal Name "+ (Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_ProposalName"))+randomInt +" successfully entered on CPQ Create Proposal screen" );

			CPQ_CreateProposal_Page.txt_Description().sendKeys(Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_Description"));
			Log.info("Proposal Description "+ (Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_Description"))+" successfully entered on CPQ Create Proposal screen" );

			CPQ_CreateProposal_Page.txt_OpportunityName().sendKeys(Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_OpportunityName"));
			Log.info("Opportunity Name "+ (Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_OpportunityName"))+" successfully entered on CPQ Create Proposal screen" );
			
			CPQ_CreateProposal_Page.txt_OpportunityID().sendKeys(String.valueOf(randomInt));
			Log.info("Opportunity ID "+String.valueOf(randomInt)+" successfully entered on CPQ Create Proposal screen" );
			
			CPQ_CreateProposal_Page.txt_AgreementDuration().sendKeys(Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_AgreementDuration"));
			Log.info("Agreement Duration  "+ (Utils.readINIFile(INI_FilePath, Section_eleProperty_Name, "txt_Description"))+" successfully entered on CPQ Create Proposal screen" );
			
			CPQ_CreateProposal_Page.btn_CreateProposal().click();
			Log.info("Click on Create Proposal Button is performed on  Create Proposal screen");

			Reporter.log("Submit Login Action is successfully performed");  
			
			Utils.waitFor_ElementToBeVisible(CPQ_CreateProposal_Page.txt_ProposalStatus(),10);
			Utils.waitFor_PageLoad(driver);
			String txt_ProposalStatus = CPQ_CreateProposal_Page.txt_ProposalStatus().getAttribute("value");
			
			if(txt_ProposalStatus=="Draft")
			{
				Log.info("Proposal Sucessfully Created");
				Log.info("Proposal Status is : "+txt_ProposalStatus);
			}
			else
			{
				Log.error("Proposal Creation Failed");
				Log.info("Proposal Status is : "+txt_ProposalStatus);
			}

		} 
		catch (Exception e) 
		{
			Log.error(e.getMessage());
			throw (e);
		}  
	}	
}