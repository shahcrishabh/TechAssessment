package test_scripts.Web;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import base.Base_driver;
import pages.UI.Cart_Page;
import pages.UI.Login_Page;
import pages.UI.Products_Page;

public class Add_Item_To_Cart extends Base_driver{
	
	/* Login and Add Items to the cart mentioned in config file.
	 * TODO -  Items list can stored under excel file as well.
	 */
	@Test
	public void productCheckout() {
		Login_Page.validLogin();
		List <String> itemsList = Arrays.asList(objPro.getProperty("Web_UI.itemsToAddInCart").split(";"));
		Products_Page.addItemsToCart(itemsList);
		Products_Page.goToCart();
		Cart_Page.verifyCartItems(itemsList);
	}
	

}
