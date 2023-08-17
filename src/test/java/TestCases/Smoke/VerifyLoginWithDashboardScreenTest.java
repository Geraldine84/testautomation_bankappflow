package TestCases.Smoke;


import PageObjects.Access_DashBoardScreen_PageObjects;
import PageObjects.Access_LoginScreen_PageObjects;
import Utils.Globals;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyLoginWithDashboardScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test(priority = 1)
    public void smokeTest_Verify_LoginScreen() throws Exception {
        Access_LoginScreen_PageObjects login = new Access_LoginScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
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
       // verifyElementExistence(login.img_TerraLogo, "isDisplayed", "Terragon Prime Logo");
        verifyElementExistence(login.btn_Login, "isEnabled", "Login button");

    }

    @Test(priority = 2)
    public void smokeTest_Verify_DashBoardScreen() throws Exception {
        Access_LoginScreen_PageObjects login = new Access_LoginScreen_PageObjects(driver);
        Access_DashBoardScreen_PageObjects dashBoard = new Access_DashBoardScreen_PageObjects(driver);

        // Login with valid credentials
        String env = login.login_Env();

        //Verify Dashboard header is present to verify dashboard page load
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //Get the user role logged in with user details
        String UserRole = login.txt_LoginRole.getText();
        String User = login.txt_LoginName.getText();
        log("Logged in User : "+User+", with User Role: "+UserRole);
        reportLog("Logged in User : "+User+", with User Role: "+UserRole);

        //verify insights labels and details are displayed
        verifyElementExistence(dashBoard.lbl_Detailedinsights, "isDisplayed", "Detailed insights");
        String TotalSMS_Unites = dashBoard.txt_sms.get(0).getText();
        if (TotalSMS_Unites.isEmpty()){

            String Filepath = getScreenShot("InsightDetails_Screen",driver);
            extentLog.addScreenCaptureFromPath(getScreenShot("InsightDetails_Screen", driver));
            log("No Records are displayed on the screen, please refer screenshot: "+Filepath);
            reportLog("No Records are displayed on the screen, please refer screenshot: "+Filepath);
            Assert.fail("no data loaded");
        }else {
            String TotalSMS_Count = dashBoard.txt_sms.get(1).getText();
            String Total_Consumption = dashBoard.txt_sms.get(2).getText();
            log("TotalSMS_Count= " + TotalSMS_Count + ", and Total_Consumption=" + Total_Consumption + ", " +
                    "For the TotalSMS_Unites=" + TotalSMS_Unites);
            reportLog("TotalSMS_Count= " + TotalSMS_Count + ", and Total_Consumption=" + Total_Consumption + ", " +
                    "For the TotalSMS_Unites=" + TotalSMS_Unites);


            //verify the screen based on the logged in role
            if (UserRole.equalsIgnoreCase("Department User")) {
                //Department User = menu_Campaigns,menu_Messages,menu_Settings
                verifyElementExistence(dashBoard.menu_Campaigns, "isDisplayed", "Campaign menu");
                verifyElementExistence(dashBoard.menu_Messages, "isDisplayed", "Messages menu");
                verifyElementExistence(dashBoard.menu_Settings, "isDisplayed", "Settings menu");

            } else if (UserRole.equalsIgnoreCase("Department Admin")) {
                //Department User = menu_Campaigns,menu_Messages,menu_Settings,menu_Rates
                verifyElementExistence(dashBoard.menu_Rates, "isDisplayed", "Rates menu");

            } else if (UserRole.equalsIgnoreCase("System Admin")) {
                //Department User = menu_Campaigns,menu_Messages,menu_Settings,menu_Rates,menu_Reports
                verifyElementExistence(dashBoard.menu_Reports, "isDisplayed", "Reports menu");

            } else if (UserRole.equalsIgnoreCase("Global Admin")) {
                //Department User = menu_Campaigns,menu_Messages,menu_Settings,menu_Rates,menu_Reports,menu_Departments,menu_AuditLogs
                verifyElementExistence(dashBoard.menu_Departments, "isDisplayed", "Departments menu");
                verifyElementExistence(dashBoard.menu_AuditLogs, "isDisplayed", "AuditLogs menu");

            } else {
                reportLog("Logged in role " + UserRole + " is not a part of UBA role");
            }

        }

       // Logout from application
        login.application_Logout();

    }

}
