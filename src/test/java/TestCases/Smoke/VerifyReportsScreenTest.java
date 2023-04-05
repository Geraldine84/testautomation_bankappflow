package TestCases.Smoke;


import PageObjects.UBA_DashBoardScreen_PageObjects;
import PageObjects.UBA_LoginScreen_PageObjects;
import PageObjects.UBA_RatesScreen_PageObjects;
import PageObjects.UBA_ReportsScreen_PageObjects;
import Utils.Globals;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyReportsScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_RatesScreen() throws Exception {
        UBA_LoginScreen_PageObjects login = new UBA_LoginScreen_PageObjects(driver);
        UBA_ReportsScreen_PageObjects report = new UBA_ReportsScreen_PageObjects(driver);
        UBA_DashBoardScreen_PageObjects dashBoard = new UBA_DashBoardScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
        // Login with valid credentials
        String env = login.login_Env();

        //verify user logged in and dashboard page is loaded without errors
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //get the user role - Messages screen has the access to all roles
        String UserRole = login.txt_LoginRole.getText();

        if (UserRole.equals("System Admin") || (UserRole.equals("Global Admin"))) {

            //Click on Messages to navigate and verify Messages screen is loaded
            clickWait(dashBoard.menu_Reports);
            verifyElementExistence(report.hdr_Reports, "isDisplayed", "Reports header");

            wait(1);
            String totalSms = report.lbl_tableHeaders.get(1).getText();
            if (totalSms.isEmpty()){

                String Filepath = getScreenShot("Report_Screen",driver);
                extentLog.addScreenCaptureFromPath(getScreenShot("Report_Screen", driver));
                log("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                reportLog("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                Assert.fail("no data loaded");
            }


            //Verify and select the Summary type as Daily or Monthly
            verifyElementExistence(report.lbl_SummaryType, "isDisplayed", "Summary Type");

            //select the different summary type
            clickWait(report.drp_SelectSummaryType);
            clickWait(report.drp_SelectSummaryTypeMonthly);

            // verify data table labels are displayed
            verifyElementExistence(report.lbl_tableHeaders.get(0), "isDisplayed", "Dates");
            verifyElementExistence(report.lbl_tableHeaders.get(1), "isDisplayed", "Total SMS sent");
            verifyElementExistence(report.lbl_tableHeaders.get(2), "isDisplayed", "Amount");

        } else {
            log(UserRole + " is not have the access to the screen "+ report.hdr_Reports.getText());

            reportLog(UserRole + " is not have the access to the screen "+ report.hdr_Reports.getText());
        }

        // Logout from application
        login.application_Logout();
    }

}
