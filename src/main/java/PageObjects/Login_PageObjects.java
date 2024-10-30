package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

//LoginPage class implementing Page Object Model for the login functionality
public class Login_PageObjects {

	private WebDriver driver;
    private WebDriverWait wait;

    private By loginButton = By.id("loginButton");
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitButton = By.id("submitButton");
    private By accountPage = By.id("accountPage");

    
    public Login_PageObjects(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String username, String password) {
        driver.findElement(loginButton).click();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public WebElement getAccountPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountPage));
    }

}
