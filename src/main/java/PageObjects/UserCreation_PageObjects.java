package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

// User creation class implementing Page Object Model for User creation functionality
public class UserCreation_PageObjects {
	
	private WebDriver driver;
	private WebDriverWait wait;

	private By signUpButton = By.id("signupbutton");
	private By username = By.id("username");
	private By email = By.id("email");
	private By password = By.id("password");
	private By fullname = By.id("fullname");

	public UserCreation_PageObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void creatUser() {
		
		// Navigate to User creation form
		driver.findElement(By.linkText("Sign Up")).click();
		
		// Fill the user creation form
		driver.findElement(username).sendKeys("gera45");
		driver.findElement(password).sendKeys("Password123!");
		driver.findElement(fullname).sendKeys("Geraldine Ugwu");
		driver.findElement(email).sendKeys("geraldine@yopmail.com");
		
		
		// Click the Sign Up button
		driver.findElement(signUpButton).click();
	}

}