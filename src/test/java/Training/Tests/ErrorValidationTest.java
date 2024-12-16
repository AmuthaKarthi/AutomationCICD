package Training.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Training.TestComponents.BaseTest;
import Training.TestComponents.Retry;
import Training.pageobjects.CartPage;
import Training.pageobjects.ProductCatolog;


public class ErrorValidationTest extends BaseTest
{

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
	
	// LandingPage landingPage = launchPageApplication();   -no need this because we already run it using Before method
	
	landingPage.loginApplication("ame@gmail.com", "Cvmad1");
	
	Assert.assertEquals("Incorrect email or password.", landingPage.ErrorMessage());

	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	String productName ="ADIDAS ORIGINAL";	

	// LandingPage landingPage = launchPageApplication();   -no need this because we already run it using Before method
	// change 2
	ProductCatolog productCatolog = landingPage.loginApplication("ame@gmail.com", "Cvmad135");
	
	List<WebElement> products = productCatolog.getProductList();
	productCatolog.addProductToCart(productName);
	CartPage cartPage = productCatolog.clickCartOnDashBoard();
	
	Boolean match = cartPage.verificationProduct(productName);
	Assert.assertTrue(match);
	
	}
}
		