package TestCases.Regression;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.UserCreation_PageObjects;
import PageObjects.Withdrawals_PageObjects;
import PageObjects.Deposit_PageObjects;
import PageObjects.Login_PageObjects;

public class GlobalTests {
	private WebDriver driver;
	private WebDriverWait wait;

	private static final String BASE_URL = "https://mock-api-testing.onrender.com/";

	public GlobalTests(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(BASE_URL);
	}

	@Test
	public void testLogin() {
		Login_PageObjects loginPage = new Login_PageObjects(driver, wait);
		loginPage.login("user", "pass");
		WebElement accountPage = loginPage.getAccountPage();
		assert accountPage.isDisplayed();
	}

	@Test
	public void testUserCreation() {
		
		UserCreation_PageObjects userCreation = new UserCreation_PageObjects(driver, wait);
		userCreation.creatUser();
		// Assert user creation success
		WebElement successMessage = driver.findElement(By.id("successMessage"));
		Assert.assertTrue(successMessage.isDisplayed());
		Assert.assertEquals("User created successfully", successMessage.getText());
	}

	@Test
	public void testDeposit() {

		// Login
		testLogin();

		Deposit_PageObjects deposit = new Deposit_PageObjects(driver, wait);
		deposit.depositAmount();
		// Assert deposit success
		WebElement depositSuccessMessage = driver.findElement(By.id("successMessage"));
		Assert.assertTrue(depositSuccessMessage.isDisplayed());
		Assert.assertEquals("Deposit successful", depositSuccessMessage.getText());

	}

	@Test
	public void testWithdrawals() {

		// Login
		testLogin();

		Withdrawals_PageObjects withdrawal = new Withdrawals_PageObjects(driver, wait);
		withdrawal.withdrawAmount();
		// Assert Withdrawal success
		WebElement depositSuccessMessage = driver.findElement(By.id("successMessage"));
		Assert.assertTrue(depositSuccessMessage.isDisplayed());
		Assert.assertEquals("Withdrawal successful", depositSuccessMessage.getText());

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}