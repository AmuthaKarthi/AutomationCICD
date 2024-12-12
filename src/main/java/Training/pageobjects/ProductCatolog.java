package Training.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Training.AbstractComponents.AbstractParentReuseable;

public class ProductCatolog extends AbstractParentReuseable
{
	WebDriver driver;
	public ProductCatolog(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	//List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getSelectedProduct(String productName)
	{
		
		WebElement selectedProduct = getProductList().stream().filter
				(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return selectedProduct;
	}
	public void addProductToCart(String productName)
	{
		WebElement selectedProduct = getSelectedProduct(productName);
		selectedProduct.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(spinner);
		
	}
	
}
