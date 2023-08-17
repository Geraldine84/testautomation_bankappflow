package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Access_LoginScreen_PageObjects extends Globals {

    public Access_LoginScreen_PageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Login screen
    @FindBy(xpath = "//h1[contains(text(),'Welcome back')]")
    public WebElement hdr_Welcome;
    @FindBy(xpath = "//label[contains(text(),'Email address')]")
    public WebElement lbl_loginEmail;
    @FindBy(xpath = "//label[contains(text(),'Password')]")
    public WebElement lbl_LoginPassword;
    @FindBy(xpath = "//input[@id='email']")
    public WebElement inp_loginEmail;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement inp_LoginPassword;
    @FindBy(xpath = "//button/span[contains(text(),'Login')]")
    public WebElement btn_Login;
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Dashboard']")
    public WebElement hdr_Dashboard;
    @FindBy(xpath = "//div[@class='usr-nme-cntr-dv-nme']")
    public WebElement txt_LoginName;

    @FindBy(xpath = "//div[@class='sdnv-usr-nme-cntr-dv']/div/div[2]")
    public WebElement txt_LoginRole;

    @FindBy(xpath = "//div[@class='sd-nv-dprtmnt-nm-cntr-dv ng-star-inserted']")
    public WebElement txt_LoginDepartment;

    @FindBy(xpath = "//span[contains(text(),'Register >')]")
    public WebElement lnk_register;
    @FindBy(xpath = "//span[contains(text(),'Forgot Password?')]")
    public WebElement lnk_ForgotPassword;
    @FindBy(xpath = "//*[@class='tpLogo']")
    public WebElement img_TerraLogo;
    //logout
    @FindBy(xpath = "//div[text()='Logout']")
    public WebElement btn_LogOut;

    //Welcome screen locators
    @FindBy(xpath = "//h2[normalize-space()='Welcome to Terragon Prime']")
    public WebElement hdr_HelpWelcome;

    @FindBy(xpath = "//span[@class='ant-modal-close-x']/i")
    public WebElement btn_close;

    //common methods used in different classes

    //Login to the application and returns Application environment
    public String login_Env() throws Exception {

        //Can be used to set the parameters in jenkins
        String Environment = System.getProperty("EnvironmentJenkins");
        log("Jenkins Environment : " + Environment);

        // Declare hash map to read excel data
        HashMap<String, String> testData;

        // If no environment parameter provided then take default as QA
        if ((Environment == null) || (Environment.equals("EnvValueNotPassed"))) {

            testData = ReadExcelFile("." + File.separator + "TestData", "UBA_Portal_Login.xlsx", "UBA_Login", 4);
            application_login(testData.get("Login_URL"), testData.get("Login_User_ID"), testData.get("Login_Password"));
        } else {

            // Use case to switch between environments
            switch (Environment) {
                case "QA": {

                    testData = ReadExcelFile("." + File.separator + "TestData", "UBA_Portal_Login.xlsx", "UBA_Login", 1);
                    application_login(testData.get("Login_URL"), testData.get("Login_User_ID"), testData.get("Login_Password"));

                    break;
                }
                case "DEV": {

                    testData = ReadExcelFile("." + File.separator + "TestData", "UBA_Portal_Login.xlsx", "UBA_Login", 2);
                    application_login(testData.get("Login_URL"), testData.get("Login_User_ID"), testData.get("Login_Password"));

                    break;
                }
                case "STAGING": {

                    testData = ReadExcelFile("." + File.separator + "TestData", "UBA_Portal_Login.xlsx", "UBA_Login", 3);
                    application_login(testData.get("Login_URL"), testData.get("Login_User_ID"), testData.get("Login_Password"));

                    break;
                }
                case "PRODUCTION": {

                    testData = ReadExcelFile("." + File.separator + "TestData", "UBA_Portal_Login.xlsx", "UBA_Login", 4);
                    application_login(testData.get("Login_URL"), testData.get("Login_User_ID"), testData.get("Login_Password"));

                    break;
                }
            }
            log("------------------------------- Logged in into: " + Environment + " -------------------------------");
        }
        return Environment;
    }

    //logout from application
    public void application_Logout() {

        try {
            wait(2);
            btn_LogOut.click();
            log("Successfully!! Logged out From application");
            reportLog("Successfully!! Logged out From application");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //login to application
    private void application_login(String login_url, String login_user_id, String login_password) throws Exception {

        //Get the time after url passed and page started loading
        long startTime = System.currentTimeMillis();

        //Pass the url of the application to load
        driver.get(login_url);
        wait(2);

        //Get the time once the page loaded
        long stopTime = System.currentTimeMillis();

        //Total time took to load the page
        long totalTime = stopTime - startTime;
        long timetakeninsec = TimeUnit.MILLISECONDS.toSeconds(totalTime);

        log((" ****************    Total time took to load the Login Page : " + timetakeninsec + " Seconds   *******************"));
        reportLog((" ****************    Total time took to load the Login Page : " + timetakeninsec + " Seconds   *******************"));

        Wait(lbl_loginEmail, Duration.ofSeconds(20));
        inp_loginEmail.sendKeys(login_user_id);

        Wait(lbl_LoginPassword, Duration.ofSeconds(20));
        inp_LoginPassword.sendKeys(decrypt(login_password));

        wait(1);
        btn_Login.click();

        //close the welcome popup window if displays on top of the application screen

        try {
            wait(2);
            if (hdr_HelpWelcome.isDisplayed()) {
                wait(2);
                btn_close.click();
                log("Welcome to Terragon Prime Window closed successfully");
            }
        }catch (Exception e)
        {
            log("No Welcome Window was present");
        }
        //wait for home/dashboard page to load
        Wait(hdr_Dashboard, Duration.ofSeconds(20));
        log("======  User  " + txt_LoginName.getText() + " Logged in successfully    ======");
        reportLog("======  User  " + txt_LoginName.getText() + " Logged in successfully    ======");

    }

}
