package TestCases.Regression;


import PageObjects.UBA_DashBoard_PageObjects;
import PageObjects.UBA_Login_PageObjects;
import Utils.Globals;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class Login_Verify_Login_and_Dashboard_Screen extends Globals {

    //Verify smoke steps for Login screen
    @Test(priority = 1)
    public void smokeTest_Verify_LoginScreen() throws Exception {
        UBA_Login_PageObjects login = new UBA_Login_PageObjects(driver);

        // Login with valid credentials
        String env = login.login_Env();

        //logout from application
        login.application_Logout();

        // Verify Welcome back text
        verifyElementExistence(login.hdr_Welcome, "isDisplayed", "Welcome Back");

        // Verify Signup and forgot password links
        verifyElementExistence(login.lnk_register, "isDisplayed", "Register for New User");
        verifyElementExistence(login.lnk_ForgotPassword, "isDisplayed", "Forgot Password");

        // Verify Images loaded properly
        verifyElementExistence(login.img_TerraLogo, "isDisplayed", "Terragon Prime Logo");
        verifyElementExistence(login.btn_Login, "isEnabled", "Login button");

    }

    @Test(priority = 2)
    public void smokeTest_Verify_DashBoardScreen() throws Exception {
        UBA_Login_PageObjects login = new UBA_Login_PageObjects(driver);
        UBA_DashBoard_PageObjects dashBoard = new UBA_DashBoard_PageObjects(driver);

        // Login with valid credentials
        String env = login.login_Env();

        //Verify Dashboard header is present to verify dashboard page load
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

       // Verify create campaign button and Header icons are displayed
        verifyElementExistence(dashBoard.btn_CreateCampaign, "isDisplayed", "Create Campaign");
        verifyElementExistence(dashBoard.icon_Inbox, "isDisplayed", "Inbox Icon");
        verifyElementExistence(dashBoard.icon_Notification, "isDisplayed", "Notification Icon");

        //verify cards are loaded and displayed
        verifyElementExistence(dashBoard.lbl_PotentialAudience, "isDisplayed", "Potential Audience");
        verifyElementExistence(dashBoard.lbl_TotalCampaigns, "isDisplayed", "Total Campaigns");
        verifyElementExistence(dashBoard.lbl_ProfilesEngaged, "isDisplayed", "Profiles Engaged");
        verifyElementExistence(dashBoard.lbl_TotalSpend, "isDisplayed", "Total Spend");

        //verify detailed insights labels are displayed
        verifyElementExistence(dashBoard.lbl_Detailedinsights, "isDisplayed", "Detailed insights");
        verifyElementExistence(dashBoard.lbl_CampaignsbyChannel, "isDisplayed", "Campaigns by Channel");
        verifyElementExistence(dashBoard.hdrGraph_ProfilesEngaged, "isDisplayed", "Profiles Engaged");

        //Verify Calender for date range
        verifyElementExistence(dashBoard.Date_calender, "isDisplayed", "Campaign selection Calender");

       // Logout from application
        login.application_Logout();


    }

}
