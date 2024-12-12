package Training.pageobjects;

 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training.AbstractComponents.AbstractParentReuseable;

public class LandingPage extends AbstractParentReuseable
{
	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToWebsite()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	@FindBy(id="userEmail")
	WebElement userEmailId;
	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userPassword")
	WebElement passwordV;
	//WebElement password = driver.findElement(By.id("userPassword"));
	
	
	@FindBy(id="login")
	WebElement submit;
	//WebElement submit = driver.findElement(By.id("login"));
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	//By toasterror = By.cssSelector("div[class='ng-trigger-flyInOut']");
	
	public String ErrorMessage()
	{
		//waitForElementToAppear(toasterror); // By Locator
		waitForWebElementToAppear(errormsg);  //using WebElement
		return errormsg.getText();
	}
	
	
	public ProductCatolog loginApplication(String name,String password)
	{
		userEmailId.sendKeys(name);
		passwordV.sendKeys(password);
		submit.click();
		ProductCatolog productCatolog = new ProductCatolog(driver);
		return productCatolog;
	}
	
}
