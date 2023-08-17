package TestCases.Smoke;


import PageObjects.Access_AuditLogsScreen_PageObjects;
import PageObjects.Access_DashBoardScreen_PageObjects;
import PageObjects.Access_DepartmentsScreen_PageObjects;
import PageObjects.Access_LoginScreen_PageObjects;
import Utils.Globals;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyAuditLogsScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_AuditLogs_Screen() throws Exception {
        Access_LoginScreen_PageObjects login = new Access_LoginScreen_PageObjects(driver);
        Access_AuditLogsScreen_PageObjects auditlogs = new Access_AuditLogsScreen_PageObjects(driver);
        Access_DashBoardScreen_PageObjects dashBoard = new Access_DashBoardScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
        // Login with valid credentials
        String env = login.login_Env();

        //verify user logged in and dashboard page is loaded without errors
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //get the user role
        String UserRole = login.txt_LoginRole.getText();

        if (UserRole.equals("Global Admin")) {

            //Click on Department to navigate and verify Department screen is loaded
            clickWait(dashBoard.menu_AuditLogs);
            wait(2);
            verifyElementExistence(auditlogs.hdr_AuditLogs, "isDisplayed", "Audit Logs header");

            wait(1);
            String event = auditlogs.lbl_tableRows.get(0).getText();
            if (event.isEmpty()){

                String Filepath = getScreenShot("AuditLogs_Screen",driver);
                extentLog.addScreenCaptureFromPath(getScreenShot("AuditLogs_Screen", driver));
                log("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                reportLog("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                Assert.fail("no data loaded");
            }

            //Verify Calender for date range
            verifyElementExistence(dashBoard.Date_calender, "isDisplayed", "Message Calender");

            //select actions from the action dropdown to filter the values
            auditlogs.selectDropdownValue("User logged in");
          wait(2);
            // verify data table labels are displayed
            verifyElementExistence(auditlogs.lbl_tableHeaders.get(0), "isDisplayed", "Event");
            verifyElementExistence(auditlogs.lbl_tableHeaders.get(1), "isDisplayed", "User");
            verifyElementExistence(auditlogs.lbl_tableHeaders.get(2), "isDisplayed", "Department");
            verifyElementExistence(auditlogs.lbl_tableHeaders.get(3), "isDisplayed", "Time");

        } else {
            log(UserRole + " is not have the access to the screen "+ auditlogs.hdr_AuditLogs.getText());

            reportLog(UserRole + " is not have the access to the screen "+ auditlogs.hdr_AuditLogs.getText());
        }

       // Logout from application
        login.application_Logout();

    }

}
