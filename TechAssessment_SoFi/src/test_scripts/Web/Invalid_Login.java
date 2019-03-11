package test_scripts.Web;

import org.testng.annotations.Test;

import base.Base_driver;
import pages.UI.Login_Page;


public class Invalid_Login extends Base_driver {

	/* Validates for invalid credentials, User is displayed Invalid user error message and can retry to login.
	 *  TODO - Similar other scenarios can be automated under this class such as Login with only UserName, Login with only password, 
	 *         Clicking on Login button without any credentials, etc.
	 */
	@Test
	public void LoginWithInvalidCredentials() {
		Login_Page.invalidCredentials();
	}

}
