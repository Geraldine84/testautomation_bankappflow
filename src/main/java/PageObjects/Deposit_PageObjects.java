package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

// Deposit class implementing Page Object Model for Deposit functionality
public class Deposit_PageObjects {
	
	private WebDriver driver;
	private WebDriverWait wait;

	private By depositButton = By.id("depositButton");
	private By userID = By.id("user ID");
	private By amount = By.id("amount");
	private By currency = By.id("currency");

	public Deposit_PageObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void depositAmount() {
		
		// Navigate to Deposit form
		driver.findElement(By.linkText("Deposit")).click();
		
		// Fill the Deposit form
		driver.findElement(userID).sendKeys("7634982");
		driver.findElement(currency).sendKeys("NGA");
		driver.findElement(amount).sendKeys("20000");
		
		// Click the deposit button
		driver.findElement(depositButton).click();
	}

}