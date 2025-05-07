package YaantracWeb.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import YaantracWeb.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
	
	
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement landingPageLoginButton;
	
	public void goTo()
	{
		driver.get("https://qa-v2.yaantrac.com");
	}
	
	public void toClickLoginButton()
	{
		landingPageLoginButton.click();
	}

}
