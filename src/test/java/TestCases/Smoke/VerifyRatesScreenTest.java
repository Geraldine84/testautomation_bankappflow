package TestCases.Smoke;


import PageObjects.Access_DashBoardScreen_PageObjects;
import PageObjects.Access_LoginScreen_PageObjects;
import PageObjects.Access_RatesScreen_PageObjects;
import Utils.Globals;
import org.testng.Assert;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyRatesScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_RatesScreen() throws Exception {
        Access_LoginScreen_PageObjects login = new Access_LoginScreen_PageObjects(driver);
        Access_RatesScreen_PageObjects rates = new Access_RatesScreen_PageObjects(driver);
        Access_DashBoardScreen_PageObjects dashBoard = new Access_DashBoardScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
        // Login with valid credentials
        String env = login.login_Env();

        //verify user logged in and dashboard page is loaded without errors
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //get the user role - Messages screen has the access to all roles
        String UserRole = login.txt_LoginRole.getText();

        if (!UserRole.equals("Department User")) {

            //Click on Messages to navigate and verify Messages screen is loaded
            clickWait(dashBoard.menu_Rates);
            verifyElementExistence(rates.hdr_Rates, "isDisplayed", "Rates header");
            wait(1);

            String smscost = rates.td_SmsCostPromotional.get(0).getText();
            if (smscost.isEmpty()){

                String Filepath = getScreenShot("Rates_Screen",driver);
                extentLog.addScreenCaptureFromPath(getScreenShot("Rates_Screen", driver));
                log("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                reportLog("No Records are displayed on the screen, please refer screenshot: "+Filepath);
                Assert.fail("no data loaded");
            }

            // verify data table labels are displayed
            verifyElementExistence(rates.lbl_NetwrokProvider, "isDisplayed", "Network Provider");
            verifyElementExistence(rates.lbl_Country, "isDisplayed", "Country");
            verifyElementExistence(rates.lbl_smsCostPromotional, "isDisplayed", "Promotional SMS Cost");
            verifyElementExistence(rates.lbl_SMScostTransactional, "isDisplayed", "Transactional SMS Cost");
        } else {
            log(UserRole + " is not have the access to the screen "+ rates.hdr_Rates.getText());

            reportLog(UserRole + " is not have the access to the screen "+ rates.hdr_Rates.getText());
        }

        //Verify the SMS costs of each Network provider.

       // int size = rates.td_NetworkProvider.size();
       // for (int i = 0; i <= size; i++) {

            verifyResult(rates.td_SmsCostPromotional.get(0).getText(), "₦" + getConfigData("MTN"));
            verifyResult(rates.td_SmsCostPromotional.get(1).getText(), "₦" + getConfigData("Airtel"));
            verifyResult(rates.td_SmsCostPromotional.get(2).getText(), "₦" + getConfigData("Glo"));
            verifyResult(rates.td_SmsCostPromotional.get(3).getText(), "₦" + getConfigData("Mobile9"));


        // Logout from application
        login.application_Logout();
    }

}
