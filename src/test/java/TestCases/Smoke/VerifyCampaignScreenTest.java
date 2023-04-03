package TestCases.Smoke;


import PageObjects.UBA_CampaignScreen_PageObjects;
import PageObjects.UBA_DashBoardScreen_PageObjects;
import PageObjects.UBA_LoginScreen_PageObjects;
import Utils.Globals;
import com.google.common.base.Verify;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;


//Verify login basic checks and dashboard screen
public class VerifyCampaignScreenTest extends Globals {

    //Verify smoke steps for Login screen
    @Test
    public void smokeTest_Verify_CampaignScreen() throws Exception {
        UBA_LoginScreen_PageObjects login = new UBA_LoginScreen_PageObjects(driver);
        UBA_CampaignScreen_PageObjects camp = new UBA_CampaignScreen_PageObjects(driver);
        UBA_DashBoardScreen_PageObjects dashBoard = new UBA_DashBoardScreen_PageObjects(driver);

        //log(encrypt("Test@123"));
        // Login with valid credentials
        String env = login.login_Env();

        //verify user logged in and dashboard page is loaded without errors
        verifyElementExistence(login.hdr_Dashboard, "isDisplayed", "DashBoard");

        //get the user role
        String UserRole = login.txt_LoginRole.getText();

        //Click on Campaign to navigate and verify campaign screen is loaded
        clickWait(dashBoard.menu_Campaigns);
        verifyElementExistence(camp.hdr_Campaigns, "isDisplayed", "Campaign header");

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

            //Verify Search field
            verifyElementExistence(camp.inp_SearchCampaign, "isDisplayed", "Search for Campaign");

            //get the camp from the list and search from the search bar
            String campaign = camp.lbl_tableRowValues.get(0).getText();

            camp.inp_SearchCampaign.sendKeys(campaign);
            wait(1);

            clickWait(camp.lbl_tableRowValues.get(0));

            String camp_title = camp.lbl_CampTitle.getText();
            verifyResult(campaign,camp_title);

        }

       // Logout from application
        login.application_Logout();


    }

}
