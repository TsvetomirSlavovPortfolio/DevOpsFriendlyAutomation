package utility;

public class Constant 
{
	    	
		public static final String ENV_Host = "ilcm34713.eaas.amdocs.com";
	    public static final String URL = "https://"+ENV_Host+":28501/B2B/";
		public static final String Path_TestData = System.getProperty("user.dir")+"\\resources\\testData\\";
		public static final String File_TestData = "TestData.xlsx";
		public static final String File_ObjectRepository= System.getProperty("user.dir")+"\\resources\\Object_Repository\\";
		public static final String Path_ScreenShot = System.getProperty("user.dir")+"\\resources\\Screenshots\\";
		public static final String Path_PSR = System.getProperty("user.dir")+"\\resources\\psr\\";
		public static final String Path_ScreenRecord = System.getProperty("user.dir")+"\\resources\\screenRecord\\jpeg\\";
		
		//Test Data Sheet Columns
		public static final int Col_TestCaseName = 0;	
		public static final int Col_UserName =1 ;
		public static final int Col_Password = 2;
		public static final int Col_Browser = 3;
		public static final int Col_CustomerID = 4;
		public static final int Col_ProductNumber = 5;
		public static final int Col_FirstName = 6;
		public static final int Col_LastName = 7;
		public static final int Col_Address = 8;
		public static final int Col_City = 9;
		public static final int Col_Country = 10;
		public static final int Col_Phone = 11;
		public static final int Col_Email = 12;
		public static final int Col_Result = 13;
	}