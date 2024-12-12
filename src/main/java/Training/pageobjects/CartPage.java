package Training.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Training.AbstractComponents.AbstractParentReuseable;

public class CartPage extends AbstractParentReuseable
{
	WebDriver driver;
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproduct;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public Boolean verificationProduct(String productName)
	{
	Boolean match = cartproduct.stream().anyMatch(cpro->cpro.getText().equalsIgnoreCase(productName));
	return match;
	}
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
