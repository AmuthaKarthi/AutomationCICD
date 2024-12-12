package Training.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Training.TestComponents.BaseTest;
import Training.pageobjects.CartPage;
import Training.pageobjects.CheckOutPage;
import Training.pageobjects.ConformationPage;
import Training.pageobjects.LandingPage;
import Training.pageobjects.OrderPage;
import Training.pageobjects.ProductCatolog;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.edge.EdgeDriver;


public class SubmitOrderJsonDataReader extends BaseTest
{
	String productName ="ADIDAS ORIGINAL";	

	@Test(dataProvider="getData",groups= {"PHashMap"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
	
	// LandingPage landingPage = launchPageApplication();   -no need this because we already run it using Before method
	
	ProductCatolog productCatolog = landingPage.loginApplication(input.get("email"), input.get("password"));
	
	List<WebElement> products = productCatolog.getProductList();
	productCatolog.addProductToCart(input.get("productName"));
	CartPage cartPage = productCatolog.clickCartOnDashBoard();
	
	Boolean match = cartPage.verificationProduct(input.get("productName"));
	Assert.assertTrue(match);
	CheckOutPage checkOutPage = cartPage.goToCheckOut();
	
	ConformationPage conformationPage = checkOutPage.creditCardDetails("234", "Karthi Ame", "First Sale", "india");
	
	String message = conformationPage.verificationMessage();
	
	Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	@Test(dependsOnMethods	= {"SubmitOrder"})
	public void ProductVerify() 
	{
		
		ProductCatolog productCatolog = landingPage.loginApplication("ame@gmail.com", "Cvmad135");
		OrderPage orderPage = productCatolog.clickOrderOnDashBoard();
		Boolean match1 = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match1);
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToString(System.getProperty("user.dir")+ "//src//test//java//Training//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
	
