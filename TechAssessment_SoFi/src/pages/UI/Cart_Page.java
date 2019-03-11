package pages.UI;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.Base_driver;

public class Cart_Page extends Base_driver{

	static By div_InventoryItems = By.cssSelector(".cart_item .inventory_item_name");
	
	/* Verify the added items in cart
	 * TODO - Items list can also be stored in Excel data file.
	 * @param List itemsList: list of items title. 
	 */
	public static void verifyCartItems(List<String> itemsList) {
		String listItem;
		
		//validates number of items added in cart are as expected.
		Assert.assertTrue(driver.findElements(div_InventoryItems).size() == itemsList.size(), "Mismatch Number of items in cart.");
		
		List<WebElement> foundItems = driver.findElements(div_InventoryItems);
		for(WebElement item: foundItems) {
			//validates items added in carts are as expected.
			listItem = item.getText().trim();
			Assert.assertTrue(itemsList.contains(listItem), listItem+" item not found in cart.");
		}
	}
}
