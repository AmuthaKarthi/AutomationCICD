package Training.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

//import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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


public class SubmitOrderTest extends BaseTest
{
	String productName ="ADIDAS ORIGINAL";	

	@Test(dataProvider="getData",groups= {"purchase"})
	public void SubmitOrder(String email,String password,String productName) throws IOException, InterruptedException
	{
	
	// LandingPage landingPage = launchPageApplication();   -no need this because we already run it using Before method
	// Changes done here
	ProductCatolog productCatolog = landingPage.loginApplication(email, password);
	
	List<WebElement> products = productCatolog.getProductList();
	productCatolog.addProductToCart(productName);
	CartPage cartPage = productCatolog.clickCartOnDashBoard();
	
	Boolean match = cartPage.verificationProduct(productName);
	Assert.assertTrue(match);
	CheckOutPage checkOutPage = cartPage.goToCheckOut();
	
	ConformationPage conformationPage = checkOutPage.creditCardDetails("234", "Karthi Ame", "First Sale", "india");
	
	String message = conformationPage.verificationMessage();
	
	AssertJUnit.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
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
	public Object[][] getData()
	{
		return new Object[][] {{"ame@gmail.com","Cvmad135","ADIDAS ORIGINAL"},{"karthiame@gmail.com","Cvmad1350","ZARA COAT 3"}};
	}
}
	
