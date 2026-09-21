package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPWD;
	
	@FindBy(xpath="//label[@class='radio-inline'][1]")
	WebElement btnYes;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkbox;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnContinue;
	
	
	@FindBy(xpath="//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")
	WebElement warningMsg;
	

	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	
	
	
	
	
	public void setFirstName(String firstName) {
		txtFirstname.sendKeys(firstName);
	}
	
	
	public void setLastName(String lastName) {
		txtLastname.sendKeys(lastName);
	}
	
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	
	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	
	public void setConfirmPasword(String password) {
		txtConfirmPWD.sendKeys(password);
	}
	
	
	public void clickYesBtn() {
		btnYes.click();
	}
	
	
	public void selectCheckBox() {
		chkbox.click();
	}
	
	
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	
	public String getConfirmatiomMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			//return (warningMsg.getText());
			return(e.getMessage());
		}
	}
	
	
	
}


