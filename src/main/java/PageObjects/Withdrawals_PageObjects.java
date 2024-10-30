package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

// Withdrawal class implementing Page Object Model for Withdrawal functionality
public class Withdrawals_PageObjects {
	
	private WebDriver driver;
	private WebDriverWait wait;

	private By withdrawalButton = By.id("withdrawaltButton");
	private By userID = By.id("user ID");
	private By withdrawalAmount = By.id("amount");
	private By description = By.id("description");

	public Withdrawals_PageObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void withdrawAmount() {
		
		// Navigate to Withdrawal form
		driver.findElement(By.linkText("Withdrawal")).click();
		
		// Fill the Withdrawal form
		driver.findElement(userID).sendKeys("123444");
		driver.findElement(withdrawalAmount).sendKeys("20000");
		driver.findElement(description).sendKeys("market expenses");
		
		// Click the Withdrawal button
		driver.findElement(withdrawalButton).click();
		
	}

}