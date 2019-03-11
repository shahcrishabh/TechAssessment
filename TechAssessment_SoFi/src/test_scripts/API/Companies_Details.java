package test_scripts.API;

import org.testng.annotations.Test;
import base.Base_driver;
import io.restassured.response.Response;
import pages.API.Companies;
import pages.API.Error_Codes;

public class Companies_Details extends Base_driver {
	static Response response;
	
	/*Verify the Company Details Response data against expected values provided in config file
	 * 
	 */
	@Test
	public static void verifyDetails() {				
		String remainingURL = objPro.getProperty("Web_API.company_URL")+objPro.getProperty("Web_API.company_id")+"?api_key="+objPro.getProperty("Web_API.api_key");	
		
		Companies.verifyCompaniesDetails(remainingURL);
		softAssert.assertAll();
	}
	
	
	/*Verify 401 response and expected message is sent for Invalid API key input.
	 * 
	 */
	@Test
	public static void verify401Response() {
		String invalidAPIKey = "1234567890";
		String remainingURL = objPro.getProperty("Web_API.company_URL")+objPro.getProperty("Web_API.company_id")+"?api_key="+invalidAPIKey;	
		
		Error_Codes.verify401Response(remainingURL);
		softAssert.assertAll();
	}

	
	/*Verify 404 response and expected message is sent for Invalid Company ID input.
	 * 
	 */
	@Test
	public static void verify404Response() {
		String invalidCompanyID = "1234567890";
		String remainingURL = objPro.getProperty("Web_API.company_URL")+invalidCompanyID+"?api_key="+objPro.getProperty("Web_API.api_key");	
		
		Error_Codes.verify404Response(remainingURL); 
		softAssert.assertAll();
	}
	

}
