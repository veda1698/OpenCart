package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjectClasses.HomePage;
import pageObjectClasses.LoginPage;
import pageObjectClasses.MyAccountPage;

public class TC002_LoginTest extends BaseTest{
	
	
	@Test(groups={"Sanity","Master"})
	public void testLogin() throws Throwable {
		logger.info("*****TC002_LoginTest execution started*******");
		try {
		HomePage hp= new HomePage(getDriver());
		hp.clickMyAccount();
		hp.clickLogin();
		Thread.sleep(3000);
		LoginPage lp= new LoginPage(getDriver());
		lp.setEmail(property.getProperty("email"));
		Thread.sleep(3000);
		lp.setPassword(property.getProperty("password"));
		lp.clickLoginBtn();
		Thread.sleep(3000);
		MyAccountPage mac= new MyAccountPage(getDriver());
		logger.info("******Validation*******");
		boolean status= mac.msgDisplayed();
		
		Assert.assertEquals(status, true);
		}
		catch(Throwable t) {
			logger.error("****Error Logs****"+t.getMessage());
			captureBrowserConsoleLogs();
			throw t;
			//Assert.fail();
		}
		finally {
			logger.info("****Testing TC002_LoginTest completed *******");
		}
		
		
		
		
		
		
		
		
		
	}

}
