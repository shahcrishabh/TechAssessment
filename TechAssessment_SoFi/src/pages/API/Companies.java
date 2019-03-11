package pages.API;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base_driver;
import io.restassured.response.Response;

public class Companies extends Base_driver{
	static Response response;
	
	/*Verify the Company Details Response data against expected values provided in config file
	 * TODO- Expected Company details can also be stored under Excel file.
	 */
	@Test
	public static void verifyCompaniesDetails(String remainingURL) {
		response = null;

		if (null!=remainingURL) {
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
		}
	}

}
