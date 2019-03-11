package pages.API;

import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;


import base.Base_driver;
import io.restassured.response.Response;

public class Search extends Base_driver{
	
	static Response response;

	/* Verify the Movie Search Results Response data against expected values provided in config file.
	 * Utilized JSON Assert Api which compares JSON strings and returns if any mismatch with any item or its value found.
	 * TODO- Expected Movie Search Results can also be stored under Excel file. 
	 * 		 Function can be written to construct the JSON String if expected data is in other data type then constructed String.	  
	 */
	public static void verifySearchMovies(String remainingUrl) {
		if(null!=remainingUrl) {
			response = request.get(remainingUrl);
			Assert.assertEquals(response.getStatusCode(), 200, "Invalid Status Code");
			
			// JSON Assert compares the JSON strings and returns any mismatch with any item or its value found 
			JSONAssert.assertEquals(objPro.getProperty("Web_API.movie_search_result"), response.body().asString(), false);
		}
	}
	
}
