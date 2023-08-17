package TestCases.Smoke;


import PageObjects.Access_CampaignScreen_PageObjects;
import PageObjects.Access_DashBoardScreen_PageObjects;
import PageObjects.Access_LoginScreen_PageObjects;
import Utils.Globals;
import com.google.common.base.Verify;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


//Verify login basic checks and dashboard screen
public class VerifyCampaignScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_CampaignScreen() throws Exception {
        Access_LoginScreen_PageObjects login = new Access_LoginScreen_PageObjects(driver);
        Access_CampaignScreen_PageObjects camp = new Access_CampaignScreen_PageObjects(driver);
        Access_DashBoardScreen_PageObjects dashBoard = new Access_DashBoardScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
        // Login with valid credentials
        String env = login.login_Env();

        //verify user logged in and dashboard page is loaded without errors
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //get the user role
        String UserRole = login.txt_LoginRole.getText();

        //Click on Campaign to navigate and verify campaign screen is loaded
        clickWait(dashBoard.menu_Campaigns);
        wait(1);
        verifyElementExistence(camp.hdr_Campaigns, "isDisplayed", "Campaign header");

        wait(1);
        String campaign = camp.lbl_tableRowValues.get(0).getText();

        if (campaign.isEmpty()){

            String Filepath = getScreenShot("Campaign_Screen",driver);
            extentLog.addScreenCaptureFromPath(getScreenShot("Campaign_Screen", driver));
            log("No Records are displayed on the screen, please refer screenshot: "+Filepath);
            reportLog("No Records are displayed on the screen, please refer screenshot: "+Filepath);
            Assert.fail("no data loaded");
        }

        //verify the screen based on the logged in role
        if(UserRole.equalsIgnoreCase("Department User") || UserRole.equalsIgnoreCase("Department Admin"))
        {
            //Department User = menu_Campaigns,menu_Messages,menu_Settings and Department admin = + Rates screen

            //verify create campaign button is enabled and displayed
            verifyElementExistence(camp.btn_CreateCampaigns, "isDisplayed", "Create Campaign Button");

            //click on create campaign to check the button is enabled and navigated to camp create screen
            clickWait(camp.btn_CreateCampaigns);

            //verify create camp screen
            verifyElementExistence(camp.hdr_CreateCamp, "isDisplayed", "Create Campaign Header");

            //navigate back to camp screen
            clickWait(camp.lnk_BacktoCamp);
            wait(2);
        }else {
            //Verify Campaign screen without Create camp for other roles
            // verify data table labels are displayed
            verifyElementExistence(camp.lbl_tableHeaders.get(0), "isDisplayed", "Campaign Name");
            verifyElementExistence(camp.lbl_tableHeaders.get(1), "isDisplayed", "Start Date");
            verifyElementExistence(camp.lbl_tableHeaders.get(2), "isDisplayed", "Push Time");
            verifyElementExistence(camp.lbl_tableHeaders.get(3), "isDisplayed", "Status");

            // verify filter Status options are displayed
            camp.drp_Filters.click();
            wait(1);
            camp.drp_status.click();
            wait(1);

            verifyElementExistence(camp.drp_status_Running, "isDisplayed", "Running");
            camp.drp_status_Completed.click();
            Wait(camp.lbl_tableRowValues.get(0), Duration.ofSeconds(10));
            //Verify Search field
            verifyElementExistence(camp.inp_SearchCampaign, "isDisplayed", "Search for Campaign");

            //get the camp from the list and search from the search bar
            String campaign1 = camp.lbl_tableRowValues.get(0).getText();

            camp.inp_SearchCampaign.sendKeys(campaign1);
            wait(1);
            camp.inp_SearchCampaign.sendKeys(Keys.ENTER);

            clickWait(camp.lbl_tableRowValues.get(0));
        wait(1);
            String camp_title = camp.lbl_CampTitle.getText();
            verifyResult(campaign1,camp_title);

        }

       // Logout from application
        login.application_Logout();

    }

}
