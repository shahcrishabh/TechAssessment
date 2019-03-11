package test_scripts.API;
import org.testng.annotations.Test;

import base.Base_driver;
import io.restassured.response.Response;
import pages.API.Search;
import pages.API.Error_Codes;

public class Search_Movies extends Base_driver {
	static Response response;

	/*Verify the Movie Search Results Response data against expected values provided in config file.
	 * 
	 */
	@Test
	public static void verifySearchResults() {
		String remainingURL = objPro.getProperty("Web_API.search_movies_URL")+"?api_key="+objPro.getProperty("Web_API.api_key")+"&query="+
								objPro.getProperty("Web_API.movie_search");
		
		Search.verifySearchMovies(remainingURL);
	}

	
	/*Verify 401 response and expected message is sent for Invalid API key input.
	 * 
	 */
	@Test
	public static void verify401Response() {
		String invalidAPIKey = "1234567890";
		String remainingURL = objPro.getProperty("Web_API.search_movies_URL")+"?api_key="+invalidAPIKey+"&query="+objPro.getProperty("Web_API.movie_search");
		
		Error_Codes.verify401Response(remainingURL);
		softAssert.assertAll();
	}

	
	/*Verify 422 response and expected message is sent for empty search query input.
	 * 
	 */	
	@Test
	public static void verify422Response() {
		String emptySearchQuery = "";
		String remainingURL = objPro.getProperty("Web_API.search_movies_URL")+"?api_key="+objPro.getProperty("Web_API.api_key")+"&query="+emptySearchQuery;
	
		Error_Codes.verify422Response(remainingURL);
		softAssert.assertAll();
	}
	
}
