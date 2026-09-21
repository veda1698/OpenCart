package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccount;
	
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement register;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement login;
	
	public void clickMyAccount() {
		myAccount.click();
	}
	
	
	public void clickRegister() {
		register.click();
	}
	
	public void clickLogin() {
		login.click();;
	}
}
