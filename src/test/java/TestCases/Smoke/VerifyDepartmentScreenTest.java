package TestCases.Smoke;


import PageObjects.Access_CampaignScreen_PageObjects;
import PageObjects.Access_DashBoardScreen_PageObjects;
import PageObjects.Access_DepartmentsScreen_PageObjects;
import PageObjects.Access_LoginScreen_PageObjects;
import Utils.Globals;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyDepartmentScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_Department_Screen() throws Exception {
        Access_LoginScreen_PageObjects login = new Access_LoginScreen_PageObjects(driver);
        Access_DepartmentsScreen_PageObjects dept = new Access_DepartmentsScreen_PageObjects(driver);
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
            clickWait(dashBoard.menu_Departments);
            verifyElementExistence(dept.hdr_Departments, "isDisplayed", "Department header");
            wait(1);

            String deptName = dept.lbl_tableRows.get(0).getText();
            if (deptName.isEmpty()){

                String Filepath = getScreenShot("Department_Screen",driver);
                extentLog.addScreenCaptureFromPath(getScreenShot("Department_Screen", driver));
                log("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                reportLog("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                Assert.fail("no data loaded");
            }

            //Verify Search Department
            verifyElementExistence(dept.inp_SearchDept, "isDisplayed", "Search Department");

            //Verify Create new department
            verifyElementExistence(dept.btn_CreateNewDept, "isDisplayed", "Create Department");
            clickWait(dept.btn_CreateNewDept);
            wait(1);
            clickWait(dept.lnk_backtoDept);

            // verify data table labels are displayed
            verifyElementExistence(dept.lbl_tableHeaders.get(0), "isDisplayed", "Department Name");
            verifyElementExistence(dept.lbl_tableHeaders.get(1), "isDisplayed", "Contact Name");
            verifyElementExistence(dept.lbl_tableHeaders.get(2), "isDisplayed", "Contact Email");

        } else {
            log(UserRole + " is not have the access to the screen "+ dept.hdr_Departments.getText());

            reportLog(UserRole + " is not have the access to the screen "+ dept.hdr_Departments.getText());
        }

       // Logout from application
        login.application_Logout();

    }

}
