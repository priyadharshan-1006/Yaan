package YaantracWeb.BaseTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import YaantracWeb.PageObject.LandingPage;

public class BaseTest {
  
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
    Properties prop = new Properties();
    FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\YaantracWeb\\resources\\GlobalData.properties");
    prop.load(file);
    
    String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser"); // To dynamically run in Therminal 

    if (browserName.equalsIgnoreCase("chrome")) {
    	
        driver = new ChromeDriver();
    }
    
    else if (browserName.equalsIgnoreCase("edge")) {
      
       driver = new EdgeDriver();
       EdgeOptions options = new EdgeOptions();
       options.addArguments("disable-infobars");
       options.addArguments("--disable-extensions");
       options.addArguments("start-maximized");
       options.addArguments("--guest");  // important
       driver = new EdgeDriver(options);
    }
    
    else if(browserName.equalsIgnoreCase("Firefox")) {
    	
    	 driver = new  FirefoxDriver();
    }
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
	return driver;

	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchAppication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage( driver)	;
		landingPage.goTo();
		return landingPage;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		// to read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8	);
		
		// String to HashMap (Jackson Datbind)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
		
	}
	
	public String getScreenShort(String testCaseName, WebDriver driver ) throws IOException {
		TakesScreenshot Ts = (TakesScreenshot) driver;
		File source = Ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\Reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\Reports\\" + testCaseName + ".png" ;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
		
	}


	
}
