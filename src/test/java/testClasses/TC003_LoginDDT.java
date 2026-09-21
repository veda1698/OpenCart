package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjectClasses.HomePage;
import pageObjectClasses.LoginPage;
import pageObjectClasses.MyAccountPage;
import utilities.DataProviders;


/*
Data valid- login success - pass - need to logout
Data valid - login unsucess= fail - remains on login

Data Invalid- login success - fail - need to logout
Data Invalid - login sucess= pass - remains on login
*/

public class TC003_LoginDDT extends BaseTest{
	
	
	@Test(dataProvider="testdata" , dataProviderClass= DataProviders.class)
	public void testLoginDDT(String email,String pwd, String expRes) {
		logger.info("******Tesing TC003_LoginDDT started*******");
		try {
		HomePage hp= new HomePage(getDriver());
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("******On login page *******");
		LoginPage lp = new LoginPage(getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLoginBtn();
		
		logger.info("******on my account page*******");
		MyAccountPage macc= new MyAccountPage(getDriver());
		
		boolean status= macc.msgDisplayed();
		
		if(expRes.equalsIgnoreCase("Valid")) {
			if(status==true) {
				macc.clickAccount();
				macc.clickLogout();
				logger.info("****Logged in with valid data successufully*******");
				Assert.assertTrue(true);
			}else {
				logger.info("****Not Logged in with valid data *******");
				Assert.assertTrue(false);
			}
		}
		
		if(expRes.equalsIgnoreCase("Invalid")) {
			if(status==true) {
				macc.clickAccount();
				macc.clickLogout();
				logger.info("****Logged in with Invalid data successufully*******");
				Assert.assertTrue(false);
			}else {
				logger.info("****Not Logged in with Invalid data *******");
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Throwable t) {
			logger.error("****Error Logs****"+t.getMessage());
			captureBrowserConsoleLogs();
			throw t;
			//Assert.fail();
		}
		logger.info("********Testing TC003_LoginDDT completed********");
	}

}
