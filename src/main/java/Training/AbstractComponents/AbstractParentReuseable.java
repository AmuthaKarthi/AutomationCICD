package Training.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Training.pageobjects.CartPage;
import Training.pageobjects.OrderPage;

public class AbstractParentReuseable 
{
	WebDriver driver;
	public AbstractParentReuseable(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(WebElement element)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(element));
	
	}
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cart;
	public CartPage clickCartOnDashBoard()
	{
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement order;
	public OrderPage clickOrderOnDashBoard()
	{
		order.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
}
