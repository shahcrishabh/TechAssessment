package test_scripts.API;
import org.skyscreamer.jsonassert.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base_driver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Search_Movies extends Base_driver {
	static Response response;

	/*Verify the Movie Search Results Response data against expected values provided in config file.
	 * Utilized JSON Assert Api which compares JSON strings and returns if any mismatch with any item or its value found.
	 * TODO- Expected Movie Search Results can also be stored under Excel file. 
	 * 		 Function can be written to construct the JSON String if expected data is in other data type.	  
	 */
	@Test
	public static void verifySearchResults() {
		String remainingURL = objPro.getProperty("Web_API.search_movies_URL")+"?api_key="+objPro.getProperty("Web_API.api_key")+"&query="+
								objPro.getProperty("Web_API.movie_search");
		response = request.get(remainingURL);
		Assert.assertEquals(response.getStatusCode(), 200, "Invalid Status Code");
		
		// JSON Assert compares the JSON strings and returns any mismatch with any item or its value found 
		JSONAssert.assertEquals(objPro.getProperty("Web_API.movie_search_result"), response.body().asString(), false);
	}

	
	/*Verify 401 response and expected message is sent for Invalid API key input.
	 * 
	 */
	@Test
	public static void verify401Response() {
		String invalidAPIKey = "1234567890";
		String remainingURL = objPro.getProperty("Web_API.search_movies_URL")+"?api_key="+invalidAPIKey+"&query="+objPro.getProperty("Web_API.movie_search");
		
		response = request.get(remainingURL);
		Assert.assertEquals(response.getStatusCode(), 401, "Invalid Status Code");

		JsonPath responseJSONBody = response.jsonPath();
		softAssert.assertEquals(responseJSONBody.get("status_message"), "Invalid API key: You must be granted a valid key.", "Invalid Status Message");
		softAssert.assertEquals(responseJSONBody.get("status_code"), 7, "Invalid Body Status Code");
		softAssert.assertAll(); 
	}

	
	/*Verify 422 response and expected message is sent for empty search query input.
	 * 
	 */	
	@Test
	public static void verify422Response() {
		String emptySearchQuery = "";
		
		String remainingURL = objPro.getProperty("Web_API.search_movies_URL")+"?api_key="+objPro.getProperty("Web_API.api_key")+"&query="+emptySearchQuery;
		response = request.get(remainingURL);
		
		JsonPath responseJSONBody = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 422, "Invalid Status Code");
		softAssert.assertEquals(responseJSONBody.getList("errors").get(0), "query must be provided", "Invalid Error Message");
		softAssert.assertAll(); 
	}
	

	
	
	
	
	
	
}
