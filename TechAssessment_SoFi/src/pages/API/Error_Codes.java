package pages.API;

import org.testng.Assert;

import base.Base_driver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Error_Codes extends Base_driver{
	static Response response;	

	/*Verify 401 response and expected message is sent for Invalid API key input.
	 * 
	 */
	public static void verify401Response(String remainingURL) {
		response = null;
		if (null!=remainingURL) {
			response = request.get(remainingURL);
			Assert.assertEquals(response.getStatusCode(), 401, "Invalid Status Code");

			JsonPath responseJSONBody = response.jsonPath();
			softAssert.assertEquals(responseJSONBody.get("status_message"), "Invalid API key: You must be granted a valid key.", "Invalid Status Message");
			softAssert.assertEquals(responseJSONBody.get("status_code"), 7, "Invalid Body Status Code");
		}
	}
	
	
	/*Verify 404 response and expected message.
	 * 
	 */
	public static void verify404Response(String remainingURL) {
		response = null;
		if (null!=remainingURL) {
			response = request.get(remainingURL);
			Assert.assertEquals(response.getStatusCode(), 404, "Invalid Status Code");
			
			JsonPath responseJSONBody = response.jsonPath();
			softAssert.assertEquals(responseJSONBody.get("status_message"), "The resource you requested could not be found.", "Invalid Status Message");
			softAssert.assertEquals(responseJSONBody.get("status_code").toString(), "34", "Invalid Body Status Code");
		}
	}
	
	
	/*Verify 422 response and expected message.
	 * 
	 */	
	public static void verify422Response(String remainingURL) {
		response = null;
		if (null!=remainingURL) {
			response = request.get(remainingURL);
			Assert.assertEquals(response.getStatusCode(), 422, "Invalid Status Code");
		
			JsonPath responseJSONBody = response.jsonPath();
			softAssert.assertEquals(responseJSONBody.getList("errors").get(0), "query must be provided", "Invalid Error Message");
		}
	}

}
