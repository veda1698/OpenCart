package pageObjectClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement clickMyAccount;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logout;
	
	public boolean msgDisplayed() {
		try {
		return myAccount.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
	
	public void clickAccount() {
		clickMyAccount.click();
	}
	
	public void clickLogout() {
		
		logout.click();
		
	}
	
	
}
