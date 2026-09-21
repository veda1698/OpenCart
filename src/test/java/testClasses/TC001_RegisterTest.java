package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjectClasses.AccountRegistrationPage;
import pageObjectClasses.HomePage;

public class TC001_RegisterTest extends BaseTest{
	
	
	@Test(groups={"Regression","Master"})
	public void testRegisteration() throws InterruptedException {
		
		logger.info("******Testing TC001_RegisterTest*******");
		try {
		HomePage home= new HomePage(getDriver());
		home.clickMyAccount();
		logger.info("Clicked on My account link");
		home.clickRegister();
		logger.info("Clicked on Register link");
		AccountRegistrationPage register = new AccountRegistrationPage(getDriver());
		
		logger.info("Providing customer details.....");
		String firstname=randomString();
		register.setFirstName(firstname);
		register.setLastName(randomString());
		register.setEmail(firstname+"@gmail.com");
		//below are different ways of writing 
		/*
		register.setEmail(randomString()+"@gmail.com");
		register.setEmail("John"+System.currentTimeMillis()+"@gmail.com");
		
		//BaseTest class methods
		register.setEmail(dynamicEmail("John"));
		register.setEmail(dynamicEmail(randomString()));
		register.setEmail(dynamicEmail1());
		register.setEmail(dynaminEmail2());
		
		*/
		register.setTelephone(randomNumber());
		String password= randomAlphaNumberic();
		register.setPassword(password);
		register.setConfirmPasword(password);
		register.clickYesBtn();
		register.selectCheckBox();
		register.clickContinue();
		Thread.sleep(1000);
		
		logger.info("Validating expected message");
		String message = register.getConfirmatiomMsg();
		/*
		if(message.equals("Your Account Has Been Created!!!")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}*/
		Assert.assertEquals(message,"Your Account Has Been Created!");
		}
		catch(Throwable t) {
			logger.error("test failed",t.getMessage());
			logger.debug("Debug logs....");
			captureBrowserConsoleLogs();
			throw t;
			//or
			//Assert.fail();
			
		}
		finally {
			logger.info("*******Finished testing**********");
		}
		
		
	}

}
