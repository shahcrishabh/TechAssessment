package pages.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.Base_driver;


public class Login_Page extends Base_driver {
	
	static By input_UserName = By.id("user-name");
	static By input_Password = By.id("password");
	static By button_Login = By.cssSelector("input[value='LOGIN']");
	static By h3_InvalidUserErrText = By.xpath("//*[@data-test='error']");
	static By lbl_Product = By.cssSelector(".product_label");
	static String strExpeInvalidUserErrTxt = "Username and password do not match any user in this service";
	
	/* Validates for invalid credentials, User is displayed Invalid user error message and can retry to login.
	 * 
	 */
	public static void invalidCredentials() {
		driver.findElement(input_UserName).sendKeys("InvalidID");
		driver.findElement(input_Password).sendKeys("InvalidPassword");
		driver.findElement(button_Login).click();
		
		Assert.assertTrue(driver.findElement(h3_InvalidUserErrText).isDisplayed(), "Invalid User Error message element is not found.");
		String strActualErrorMsg = driver.findElement(h3_InvalidUserErrText).getText();
		Assert.assertTrue(strActualErrorMsg.contains(strExpeInvalidUserErrTxt), 
				"Invalid User Error Message is not displayed as expected");
		Assert.assertTrue(driver.findElement(button_Login).isDisplayed(), "Login button is not displayed after Invalid Login.");
	}

	
	/* Login into the Web site with valid credentials.
	 * Waits until next Product page is not loaded
	 */
	public static void validLogin() {
		driver.findElement(input_UserName).sendKeys(objPro.getProperty("Web_UI.userName"));
		driver.findElement(input_Password).sendKeys(objPro.getProperty("Web_UI.password"));
		driver.findElement(button_Login).click();
		
		//validates Invalid user error message is not displayed. 
		Assert.assertTrue(driver.findElements(h3_InvalidUserErrText).size() < 1 , "Invalid User Error message is displayed.");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lbl_Product)));
	
	}
	
}
