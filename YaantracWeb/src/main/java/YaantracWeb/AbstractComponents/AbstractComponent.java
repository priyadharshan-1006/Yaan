package YaantracWeb.AbstractComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}

	
	public void waitForElementsToAppeaar(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
		}
	
	public void waitForWebElementsToAppeaar(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	
	
	
	
	public void RobotClass() throws AWTException
	{
		
		 Robot robot = new Robot();
		 robot.delay(2000); // Adjust delay as necessary
		 robot.keyPress(KeyEvent.VK_TAB);  // Tab through to the "Allow" button
		 robot.keyRelease(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_ENTER); // Press "Enter" to select "Allow"
	     robot.keyRelease(KeyEvent.VK_ENTER);
		
	}	
	
	
	
	
}
