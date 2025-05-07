package YaantracWeb.Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import YaantracWeb.BaseTestComponents.BaseTest;
import YaantracWeb.BaseTestComponents.Retry;
import YaantracWeb.PageObject.LoginPage;

public class errorValidation extends BaseTest {
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\YaantracWeb\\data\\Datas.json");
		return new Object[][] {{data.get(2)},{data.get(3)}};
	}
	
	@Test(dataProvider="getData")
	public void LoginInValidUserName(HashMap<String , String> input) throws AWTException {
		
		landingPage.toClickLoginButton();
		LoginPage loginPage = new LoginPage(driver);	
		loginPage.login(input.get("userName"), input.get("userPassword"));
		loginPage.toGetErrorMessage();
		Assert.assertEquals("Invalid Username or Password", loginPage.toGetErrorMessage());
	}
	
	
	@Test(retryAnalyzer=Retry.class)
	public void loginInvalid() throws AWTException {
	    landingPage.toClickLoginButton();
	    LoginPage loginPage = new LoginPage(driver);	
	    loginPage.login("Demo", "Demo@12345!98");
	    String errorMessage = loginPage.toGetErrorMessage();
	    Assert.assertEquals("Inv@@alid Username or Password", errorMessage);
	}
	

}
