package Training.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Training.TestComponents.BaseTest;
import Training.pageobjects.CartPage;
import Training.pageobjects.CheckOutPage;
import Training.pageobjects.ConformationPage;
import Training.pageobjects.LandingPage;
import Training.pageobjects.ProductCatolog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplimentaion extends BaseTest	
{
	public LandingPage landingPage;
	public ProductCatolog productCatolog;
	public ConformationPage conformationPage;
	@Given("I Landed On Ecommerce Page")
	public void  I_Landed_On_Ecommerce_Page() throws IOException
	{
		landingPage = launchPageApplication();
	}
	@Given("^I Logged In with UserName (.+) and Password (.+)$")
	public void I_Logged_In_with_UserName_and_Password(String userName,String password)
	{
		productCatolog = landingPage.loginApplication(userName,password);

	}
    
	@When("^I add the Product (.+) to cart$")
	public void I_add_the_Product_to_cart(String productName)
	{
		List<WebElement> products = productCatolog.getProductList();
		productCatolog.addProductToCart(productName);
	}
	@When("^Checkout (.+) and Submit Order$")
	public void Checkout_product_and_Submit_Order(String productName) throws InterruptedException
	{
		Thread.sleep(4000);
		CartPage cartPage = productCatolog.clickCartOnDashBoard();
		Boolean match = cartPage.verificationProduct(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
	    conformationPage = checkOutPage.creditCardDetails("234", "Karthi Ame", "First Sale", "india");	
	}
    @Then("{string} message displayed in Confirmation Page")
    public void Message_displayed_in_ConfirmationPage(String string)
    {
    	String message = conformationPage.verificationMessage();
    	Assert.assertTrue(message.equalsIgnoreCase(string));
    	driver.close();
    }
    @Then("{string} message displayed")
    public void Message_displayed(String string1)
    {
    	Assert.assertEquals(string1, landingPage.ErrorMessage());	
    	driver.close();
    }
    

}
