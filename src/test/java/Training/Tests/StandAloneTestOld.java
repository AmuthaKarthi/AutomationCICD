package Training.Tests;

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

import Training.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.edge.EdgeDriver;


public class StandAloneTestOld {

	public static void main(String[] args) throws InterruptedException
	{

	WebDriverManager.chromedriver().setup();
	WebDriver driver =new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/client/");
	//LandingPage landingPage = new LandingPage(driver);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.manage().window().maximize();
	String myChoice ="ADIDAS ORIGINAL";
	driver.findElement(By.id("userEmail")).sendKeys("ame@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Cvmad135");
	driver.findElement(By.id("login")).click();
	List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	WebElement selectedProduct = products.stream().filter
			(product->product.findElement(By.cssSelector("b")).getText().equals(myChoice)).findFirst().orElse(null);
	selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	List<WebElement> cartproduct = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartproduct.stream().anyMatch(cpro->cpro.getText().equalsIgnoreCase(myChoice));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("234");
	driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Karthi Ame");
	driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("FIRST SALE");
	//driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
	Actions a =new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	Thread.sleep(5000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
	List<WebElement> countries = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
	WebElement selectedCountry = countries.stream().filter
		(country->country.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("india")).findFirst().orElse(null);
	selectedCountry.click();
	driver.findElement(By.cssSelector("a[class='btnn action__submit ng-star-inserted']")).click();
	String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	
	
	
	Thread.sleep(5000);
	driver.quit();
	}

}
