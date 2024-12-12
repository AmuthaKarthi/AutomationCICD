package Training.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Training.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{

		public WebDriver driver;
		public LandingPage landingPage;
		public WebDriver initializeDrive() throws IOException
		{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java/Training//Resources//GlobalData.properties");
		p.load(fis);
		
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : p.getProperty("browser");
		 //p.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
		ChromeOptions option = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{
			option.addArguments("headless");
		}
		driver =new ChromeDriver(option);
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
				
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
				
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		}
@BeforeMethod(alwaysRun=true)	
		public LandingPage launchPageApplication() throws IOException
		{
			driver = initializeDrive();
			landingPage = new LandingPage(driver);
			landingPage.goToWebsite();
			return landingPage;
		}
@AfterMethod(alwaysRun=true)
		public void QuiteWebsite() throws InterruptedException
		{
			Thread.sleep(5000);
			driver.quit();

		}
		public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		}

		public List<HashMap<String, String>> getJsonDataToString(String filepath) throws IOException
		{
			//Read JSON to String
			String jsonContent = FileUtils.readFileToString(new File
					(filepath),StandardCharsets.UTF_8);
			//Convert String to HashMap
			//Add JACKSON DATABIND Dependerncy to pom.xml
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
			return data;
}

}
