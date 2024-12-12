package Training.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training.AbstractComponents.AbstractParentReuseable;

public class ConformationPage extends AbstractParentReuseable {

	WebDriver driver;
	public ConformationPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	@FindBy(css=".hero-primary")
	WebElement text;
	
	public String verificationMessage()
	{
	return text.getText();
	}

}

