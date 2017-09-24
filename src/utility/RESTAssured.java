package utility;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import utility.Log;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import utility.Constant;

public class RESTAssured 
{
	public static void main(String[] args) 
	{
		try 
		{
			Response responseSecurityLogin=SecurityManagement_Login();
			VerifyBasicProperties_Rest(responseSecurityLogin);
			REST_GET_RetrieveCustomer_Details("100000009");
			
		} 
		catch (URISyntaxException | IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static Map<String, String> REST_GET_RetrieveCustomer_Details(String customer_ID) throws URISyntaxException, IOException
	{
	    Map<String,String> customer_Details_Map_REST = new HashMap<String,String>();
		Response responseSecurityLogin=SecurityManagement_Login();
		VerifyBasicProperties_Rest(responseSecurityLogin);
		Log.info("-----------------------------------------------------------XOX------------------------------------------------------------------\n");
		String uriPath="https://"+Constant.ENV_Host+":28501/customers-ms/customer-management/v1/customers/"+customer_ID;
		Log.info("The POST REST is fired for  URI = " +uriPath);
		URI url = new URI(uriPath);
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType("application/json; charset=UTF-8");
		Response response1= given().accept(ContentType.JSON).and().given().config(config.sslConfig(new SSLConfig().relaxedHTTPSValidation())).get(url);
		
		customer_Details_Map_REST.put("Customer_ID",response1.path("id"));
	    customer_Details_Map_REST.put("Customer_Name",response1.path("name"));
	    customer_Details_Map_REST.put("ORG_ID",response1.path("owningParty.id"));
	    customer_Details_Map_REST.put("ORG_Name",response1.path("owningParty.name"));
	    customer_Details_Map_REST.put("Contact_ID",response1.path("primaryContact.id"));
	    customer_Details_Map_REST.put("Contact_Name",response1.path("primaryContact.name"));
	    customer_Details_Map_REST.put("Customer_Type_ID",response1.path("customerType.id"));
	    customer_Details_Map_REST.put("Customer_Type_Title",response1.path("customerType.title"));
	    customer_Details_Map_REST.put("Customer_Subtype_ID",response1.path("customerSubtype.id"));
	    customer_Details_Map_REST.put("Customer_Subtype_Title",response1.path("customerSubtype.title"));
	    Log.info("-----------------------------------------------------------XOX------------------------------------------------------------------\n");
	    return customer_Details_Map_REST;	
	}
	
	public static Response SecurityManagement_Login() throws URISyntaxException, IOException
	{
		String uriPath="https://"+Constant.ENV_Host+":28501/securitymanagement-ms/Login";
		String payLoad = "{\"user\":\"Asmsa1\",\"password\":\"Asmsa1\"}";
		
		Log.info("The POST REST is fired for URI = " +uriPath);
		Log.info("Pay load for REST is = "  +payLoad);
		URI url = new URI(uriPath);
		String APIBody =payLoad;
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(APIBody);
		builder.setContentType("application/json; charset=UTF-8");
		
		RequestSpecification requestSpec = builder.build();
		Response response = given().accept(ContentType.JSON).and().given().config(config.sslConfig(new SSLConfig().relaxedHTTPSValidation())).spec(requestSpec).when().post(url);	
		return response;
	}
	
	public static void VerifyBasicProperties_Rest(Response response) throws URISyntaxException 
	{
		Log.info("REST_Status_Code = "+response.statusCode());
		//System.out.println("Rest has header as  " + response.getHeaders());
		Log.info("REST_Content_Type = " +response.getContentType());
		Log.info("Response_Time = " + response.time());
		Log.info("REST_Session_ID = " +response.sessionId());
		Log.info("REST_Response = " +response.asString());
	}

	public static Response POST_REST_Call(String uri , String payload) throws URISyntaxException 
	{
		Log.info("The POST REST is fired for  URI = " +uri);
		Log.info("Pay load for REST is = "  +payload);
		URI url = new URI(uri);
		String APIBody =payload;
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(APIBody);
		builder.setContentType("application/json; charset=UTF-8");
		RequestSpecification requestSpec = builder.build();
		Response response = given().accept(ContentType.JSON).and().given().config(config.sslConfig(new SSLConfig().relaxedHTTPSValidation())).spec(requestSpec).when().post(url);
		//response.then().log().all();
		Log.info("REST_Response = " +response.asString());
		return response;
	}
}
