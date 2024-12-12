package Training.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training.AbstractComponents.AbstractParentReuseable;

public class CheckOutPage extends AbstractParentReuseable

	{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	WebElement cvv;

	@FindBy(xpath="(//input[@type='text'])[3]")
	WebElement nameOnCard;

	@FindBy(xpath="(//input[@type='text'])[4]")
	WebElement coupon;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> countries;
	
	@FindBy(css="a[class='btnn action__submit ng-star-inserted']")
	WebElement submit;
	
	By countriesV = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	By sCountry = By.cssSelector("span");
	
	public ConformationPage creditCardDetails(String cvvNumber,String nameOnCreditCard,String couponNo,String countryName)
	{
		cvv.sendKeys(cvvNumber);
		nameOnCard.sendKeys(nameOnCreditCard);
		coupon.sendKeys(couponNo);
		Actions a =new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(countriesV);
		WebElement selectedCountry = countries.stream().filter
				(country->country.findElement(sCountry).getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		selectedCountry.click();
		submit.click();
		return new ConformationPage(driver);
	}
	
}
