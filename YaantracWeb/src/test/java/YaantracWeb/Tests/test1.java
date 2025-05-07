package YaantracWeb.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import YaantracWeb.BaseTestComponents.BaseTest;
import YaantracWeb.PageObject.LandingPage;
import YaantracWeb.PageObject.LoginPage;

public class test1 extends BaseTest {
	
@DataProvider
public Object[][] getData() throws IOException {
	List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\YaantracWeb\\data\\Datas.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}
	
@Test(groups= {"SmokeTest"}, dataProvider="getData")
public void Login(HashMap<String , String> input) throws AWTException, IOException {
	 
	landingPage.toClickLoginButton();
	LoginPage loginPage = new LoginPage(driver);
	loginPage.login(input.get("userName"), input.get("userPassword"));
	
}




}
