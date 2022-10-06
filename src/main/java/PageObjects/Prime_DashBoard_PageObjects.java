package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Prime_DashBoard_PageObjects extends Globals {
    public Prime_DashBoard_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of DashBoard screen
    @FindBy(xpath = "//div[@class='ng-star-inserted'][text()='Dashboard']")
    public WebElement hdr_Dashboard;

    @FindBy(xpath = "//h1[contains(text(),'Welcome back')]")
    public WebElement menu_Dashboard;

    @FindBy(xpath = "//span[text()='Create Campaign']")
    public WebElement btn_CreateCampaign;

    @FindBy(xpath = "//img[@src='../assets/header-icon/fi_message-square.svg']")
    public WebElement icon_Inbox;

    @FindBy(xpath = "//img[@src='../assets/header-icon/fi_bell.svg']")
    public WebElement icon_Notification;

    @FindBy(xpath = "//dashboard[1]/div[2]/div[1]/div[1]/nz-range-picker[1]")
    public WebElement Date_calender;

    @FindBy(xpath = "//div[normalize-space()='Potential Audience']")
    public WebElement lbl_PotentialAudience;

    @FindBy(xpath = "//div[normalize-space()='Total Campaigns']")
    public WebElement lbl_TotalCampaigns;

    @FindBy(xpath = "//div[contains(text(),'Profiles Engaged')]")
    public WebElement lbl_ProfilesEngaged;

    @FindBy(xpath = "//div[normalize-space()='total Spend']")
    public WebElement lbl_TotalSpend;

    @FindBy(xpath = "//h3[normalize-space()='Detailed insights']")
    public WebElement lbl_Detailedinsights;

    @FindBy(xpath = "//div[@class='detailValue']")
    public WebElement date_Detailedinsights;

    @FindBy(xpath = "//b[normalize-space()='Campaigns by Channel']")
    public WebElement lbl_CampaignsbyChannel;

    @FindBy(xpath = "//b[normalize-space()='Profiles Engaged']")
    public WebElement hdrGraph_ProfilesEngaged;



}
