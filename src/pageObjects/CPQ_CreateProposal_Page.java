package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.Utils;

public class CPQ_CreateProposal_Page extends BaseClass 
{
	static private String INI_FilePath = Constant.File_ObjectRepository +"Create_Proposal.ini";
	static private String Section_eleProperty_Name="Create_Proposal_Property";

	public CPQ_CreateProposal_Page(WebDriver driver)
	{
		super(driver);
	}     
	
	public static WebElement txt_ProposalName() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_ProposalName");
	}
	public static WebElement txt_Description() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_Description");
	}
	public static WebElement txt_OpportunityName() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_OpportunityName");
	}
	public static WebElement txt_OpportunityID() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_OpportunityID");
	}
	public static WebElement txt_AgreementDuration() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_AgreementDuration");
	}
	public static WebElement txt_ProposalStatus() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "txt_ProposalStatus");
	}
	public static WebElement btn_CreateProposal() throws Exception
	{
		return Utils.getElementDetails(INI_FilePath, Section_eleProperty_Name, "btn_CreateProposal");
	}
}
