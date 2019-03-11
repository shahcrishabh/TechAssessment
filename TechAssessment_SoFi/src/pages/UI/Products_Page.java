package pages.UI;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.Base_driver;

public class Products_Page extends Base_driver {
	
	static By div_Cart = By.id("shopping_cart_container");
	static By div_CartContainer = By.cssSelector("#cart_contents_container");
	
	/* Add required items in to the cart. Items list is mentioned under config property file.
	 *  TODO - Items list can also be stored in Excel data file.
	 *  @param itemList List of items title which needs to be added in cart. 
	 */
	public static void addItemsToCart(List<String> itemsList) {
		
		for(String item : itemsList) {
		By btnProdAddToCart = By.xpath("//div[(@class='inventory_item_label') and a/div[text()='"+ item.trim() +
				"']]/following-sibling::div/button[text()='ADD TO CART']");
		driver.findElement(btnProdAddToCart).click();
		
		//validates 'Remove' button is displayed for items which are added into the cart.
	 	String strRemove = "//div[(@class='inventory_item_label') and a/div[text()='" + item.trim() +"']]/following-sibling::div/button[text()='REMOVE']";
	 	Assert.assertTrue(driver.findElement(By.xpath(strRemove)).isDisplayed(), "Remove Button is got displayed for "+item);
		}
	}
	
	/* Navigates to Cart's page.
	 * Wait until cart is loaded.
	 */
	public static void goToCart() {
		driver.findElement(div_Cart).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(div_CartContainer)));
	}
	
}
