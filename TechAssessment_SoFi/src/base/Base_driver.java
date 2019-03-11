package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/*This is template class which will run before & after code for each test and suite along with calling the tests mentioned under testnNG xml file.
 * 
 */
public class Base_driver {

	public static WebDriver driver;
	private FileInputStream fis;
	static public Properties objPro;
	static public WebDriverWait wait;
	static public RequestSpecification request; 
	static public SoftAssert softAssert;
	static public String strPackageName;
	
	/* Initialize property file based on environment to run.
	 * Initialize the Chrome driver path.
	 * 		@param environment: Value is passed from testNG xml file. Respective name config file is required under config folder.  
	 */
	@Parameters("environment")
	@BeforeSuite
	public void beforeSuite(String environment) {
		openPropertyFile(environment);
		System.setProperty("webdriver.chrome.driver", "libs\\drivers\\chromedriver.exe"); 
	}
	
	/* Initialize SoftAssert.
	 * For Web scripts, Initialize ChromeDriver, Implicit & Explicit timeout, Open the Web site.
	 * For API scripts, Initialize Rest Assured. 
	 * TODO - If condition for each type of driver type can be added for cross browser testing.    
	 * 		@param method: Based on script's package name, required classes are initialized. 
	 */
	@BeforeMethod
	public void beforeTest(Method method) {
		strPackageName = method.getDeclaringClass().getPackage().getName();
		softAssert = new SoftAssert();
		
		if(strPackageName.endsWith("Web")) {
			
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,10);
			driver.manage().window().maximize();
		  	driver.get(objPro.getProperty("Web_UI.url"));
		}
		else if (strPackageName.endsWith("API")) {
			RestAssured.baseURI = objPro.getProperty("Web_API.base_URL").toString();
			request = RestAssured.given();	
		}
	}
	
	/*Closes the browsers open by Web driver, if any are opened.
	 * 
	 */
	
	@AfterMethod
	public void afterMethod() {
		 if(strPackageName.endsWith("Web") && (null!=driver)) {
			    driver.quit(); 
		    }
	}
	
	/* Closes the fileinput stream is opened before suite run.
	 * 
	 */
	@AfterSuite
	public void afterSuite() {
		if(null!= fis) {	
			try {
				fis.close();
			} catch (IOException e) {
				Assert.assertTrue(false, e.getMessage());
			}
		}
	}

	
	
	/* Opens the Property file.
	 * @param fileName The config file name which is stored under config folder. 
	 * It should be same as the environment param value provided under testNG xml file.
	 */
	private void openPropertyFile(String fileName) {
		String configFilePath = System.getProperty("user.dir") + "\\config\\"+fileName+".properties";
		try {
			fis = new FileInputStream(configFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(false, e.getMessage());
		} 
		
		objPro = new Properties();
		try {
			objPro.load(fis);
		} catch (IOException e) {
			Assert.assertTrue(false, e.getMessage());
		}
		
		
	}
	
	
}
