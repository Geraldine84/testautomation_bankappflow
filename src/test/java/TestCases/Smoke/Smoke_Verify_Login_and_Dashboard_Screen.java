package TestCases.Smoke;


import PageObjects.UBA_DashBoardScreen_PageObjects;
import PageObjects.UBA_LoginScreen_PageObjects;
import Utils.Globals;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class Smoke_Verify_Login_and_Dashboard_Screen extends Globals {

    //Verify smoke steps for Login screen
    @Test(priority = 1)
    public void smokeTest_Verify_LoginScreen() throws Exception {
        UBA_LoginScreen_PageObjects login = new UBA_LoginScreen_PageObjects(driver);

        // Login with valid credentials
        String env = login.login_Env();

        //logout from application
        login.application_Logout();

        // Verify Welcome back text
        verifyElementExistence(login.hdr_Welcome, "isDisplayed", "Welcome Back");

        // Verify Signup and forgot password links
       // verifyElementExistence(login.lnk_register, "isDisplayed", "Register for New User");
       // verifyElementExistence(login.lnk_ForgotPassword, "isDisplayed", "Forgot Password");

        // Verify Images loaded properly
        verifyElementExistence(login.img_TerraLogo, "isDisplayed", "Terragon Prime Logo");
        verifyElementExistence(login.btn_Login, "isEnabled", "Login button");

    }

    @Test(priority = 2)
    public void smokeTest_Verify_DashBoardScreen() throws Exception {
        UBA_LoginScreen_PageObjects login = new UBA_LoginScreen_PageObjects(driver);
        UBA_DashBoardScreen_PageObjects dashBoard = new UBA_DashBoardScreen_PageObjects(driver);

        // Login with valid credentials
        String env = login.login_Env();

        //Verify Dashboard header is present to verify dashboard page load
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

       // Verify create campaign button and Header icons are displayed


        //verify detailed insights labels are displayed
        verifyElementExistence(dashBoard.lbl_Detailedinsights, "isDisplayed", "Detailed insights");

        //Verify Calender for date range
        verifyElementExistence(dashBoard.Date_calender, "isDisplayed", "Campaign selection Calender");

       // Logout from application
        login.application_Logout();


    }

}
