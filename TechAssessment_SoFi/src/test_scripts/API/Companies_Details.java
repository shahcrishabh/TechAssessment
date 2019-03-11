package test_scripts.API;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;

//import java.util.Map;

import org.testng.annotations.Test;
import base.Base_driver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Companies_Details extends Base_driver {
	static Response response;
	
	/*Verify the Company Details Response data against expected values provided in config file
	 * TODO- Expected Company details can also be stored under Excel file.
	 */
	@Test
	public static void verifyDetails() {
				
		String remainingURL = objPro.getProperty("Web_API.company_URL")+objPro.getProperty("Web_API.company_id")+"?api_key="+objPro.getProperty("Web_API.api_key");	
		response = request.get(remainingURL);
		
		Assert.assertEquals(response.getStatusCode(), 200, "Invalid Status Code");
				
		Map<String, String> compDetails = new HashMap<String, String>();
		String strCompDetails = objPro.getProperty("Web_API.company_details");
		for(String eachField : strCompDetails.split(";")) {
			String[] eachItem = eachField.split("=");
			compDetails.put(eachItem[0], eachItem[1]);
		}
		
		for(String item : compDetails.keySet()) {
			softAssert.assertEquals(compDetails.get(item).trim().toUpperCase(), response.jsonPath().get(item).toString().trim().toUpperCase(),
									"Mismatch with Company details found.");
		}
		softAssert.assertAll();
	}
	
	
	/*Verify 401 response and expected message is sent for Invalid API key input.
	 * 
	 */
	@Test
	public static void verify401Response() {
		String invalidAPIKey = "1234567890";
		String remainingURL = objPro.getProperty("Web_API.company_URL")+objPro.getProperty("Web_API.company_id")+"?api_key="+invalidAPIKey;	
		
		response = request.get(remainingURL);
		Assert.assertEquals(response.getStatusCode(), 401, "Invalid Status Code");
		
		JsonPath responseJSONBody = response.jsonPath();
		softAssert.assertEquals(responseJSONBody.get("status_message"), "Invalid API key: You must be granted a valid key.", "Invalid Status Message");
		softAssert.assertEquals(responseJSONBody.get("status_code"), 7, "Invalid Body Status Code");
		softAssert.assertAll(); 
	}

	
	/*Verify 404 response and expected message is sent for Invalid Company ID input.
	 * 
	 */
	@Test
	public static void verify404Response() {
		String invalidCompanyID = "1234567890";
		String remainingURL = objPro.getProperty("Web_API.company_URL")+invalidCompanyID+"?api_key="+objPro.getProperty("Web_API.api_key");	
		
		response = request.get(remainingURL);
		Assert.assertEquals(response.getStatusCode(), 404, "Invalid Status Code");
		
		JsonPath responseJSONBody = response.jsonPath();
		softAssert.assertEquals(responseJSONBody.get("status_message"), "The resource you requested could not be found.", "Invalid Status Message");
		softAssert.assertEquals(responseJSONBody.get("status_code").toString(), "34", "Invalid Body Status Code");
		softAssert.assertAll(); 
	}
	

}
