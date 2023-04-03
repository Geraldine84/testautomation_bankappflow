package TestCases.Smoke;


import PageObjects.UBA_DashBoardScreen_PageObjects;
import PageObjects.UBA_LoginScreen_PageObjects;
import PageObjects.UBA_MessagesScreen_PageObjects;
import Utils.Globals;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyMessageScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_MessagesScreen() throws Exception {
        UBA_LoginScreen_PageObjects login = new UBA_LoginScreen_PageObjects(driver);
        UBA_MessagesScreen_PageObjects msg = new UBA_MessagesScreen_PageObjects(driver);
        UBA_DashBoardScreen_PageObjects dashBoard = new UBA_DashBoardScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
        // Login with valid credentials
        String env = login.login_Env();

        //verify user logged in and dashboard page is loaded without errors
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //get the user role - Messages screen has the access to all roles
        String UserRole = login.txt_LoginRole.getText();

        //Click on Messages to navigate and verify Messages screen is loaded
        clickWait(dashBoard.menu_Messages);
        verifyElementExistence(msg.hdr_Messages, "isDisplayed", "Messages header");

        //verify search field is displayed
        verifyElementExistence(msg.inp_SearchMessages, "isDisplayed", "Search field");

        // verify data table labels are displayed
        verifyElementExistence(msg.lbl_tableHeaders.get(0), "isDisplayed", "Message Name");
        verifyElementExistence(msg.lbl_tableHeaders.get(1), "isDisplayed", "Recipient");
        verifyElementExistence(msg.lbl_tableHeaders.get(2), "isDisplayed", "Type");
        verifyElementExistence(msg.lbl_tableHeaders.get(7), "isDisplayed", "Status");

        //Verify Download Log button is enabled to click after search with mobile number.
        String mobile = msg.lbl_tableRowValues.get(1).getText();
        msg.inp_SearchMessages.sendKeys(mobile);
        wait(1);
        msg.btn_DownloadLogs.click();

        //Verify Calender for date range
        verifyElementExistence(dashBoard.Date_calender, "isDisplayed", "Message Calender");

        // verify filter Status options are displayed
        msg.selectDropdownValue("Delivered");

        // Logout from application
        login.application_Logout();

    }
}
