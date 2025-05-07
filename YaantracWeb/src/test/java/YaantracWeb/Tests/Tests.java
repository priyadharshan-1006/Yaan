package YaantracWeb.Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import YaantracWeb.PageObject.LandingPage;

public class Tests {
	
	public static void main (String[] args) throws AWTException {
		
		String VehicleStatus = "Parked"; 
	WebDriver driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	LandingPage landingPage = new LandingPage( driver)	;
	driver.manage().window().fullscreen();
	driver.findElement(By.xpath("//button[text()='Login']")).click();
	driver.findElement(By.xpath("//input[@name='userID']")).sendKeys("demo");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Demo@12345!");
	driver.findElement(By.xpath("//button[text()='Login']")).click();
	 Robot robot = new Robot();
	 robot.delay(2000); // Adjust delay as necessary
	 robot.keyPress(KeyEvent.VK_TAB);  // Tab through to the "Allow" button
	 robot.keyRelease(KeyEvent.VK_TAB);
     robot.keyPress(KeyEvent.VK_ENTER); // Press "Enter" to select "Allow"
     robot.keyRelease(KeyEvent.VK_ENTER);   
	//driver.findElement(By.xpath("//button[text()='Login']")).click();
	//driver.findElement(By.xpath("//div[@class='analytics-content-box MuiBox-root css-0']")).click();
	
	
     List<WebElement> widgets = driver.findElements(By.xpath("//div[@class='css-1an2d61']"));

     Optional<WebElement> movingWidget = widgets.stream()
         .filter(widget -> {
             try {
                 WebElement status = widget.findElement(By.xpath(".//p[contains(@class, 'analytics-typo2')]"));
                 return status.getText().equalsIgnoreCase("VehicleStatus");
             } catch (NoSuchElementException e) {
                 return false;
             }
         })
         .findFirst();

     movingWidget.ifPresent(widget -> {
         widget.click();
         System.out.println("Clicked on the 'Moving' widget.");
     });

     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement element = driver.findElement(By.xpath("//div[text()='TN11AX9837']"));
     Actions actions = new Actions(driver);
     actions.moveToElement(element).click().perform();
     
     String ConfirmStatus = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 online css-9l3uo3']")).getText();     
     Assert.assertTrue(ConfirmStatus.equalsIgnoreCase(VehicleStatus));

     driver.close();
     
     
     
	}

}
