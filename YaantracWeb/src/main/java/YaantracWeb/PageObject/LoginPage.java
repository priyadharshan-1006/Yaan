package YaantracWeb.PageObject;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import YaantracWeb.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	WebDriver driver;
	public LoginPage(WebDriver driver)
{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath="//input[@name='userID']")
	 WebElement userName ;
	@FindBy(xpath="//input[@name='password']")
	 WebElement userPassword ;
	@FindBy(xpath="//button[text()='Login']")
	 WebElement login;	

	@FindBy(xpath="//div[text()='Invalid Username or Password']")

	WebElement errorValidation;
	
	
	public void login(String Name,String Password) throws AWTException
	{
		userName.sendKeys(Name);
		userPassword.sendKeys(Password);
		login.click();
		RobotClass();
		
	}	
	
public String toGetErrorMessage() {
	
	waitForWebElementsToAppeaar(errorValidation);
	return errorValidation.getText();
	
}
	
	
	
}

