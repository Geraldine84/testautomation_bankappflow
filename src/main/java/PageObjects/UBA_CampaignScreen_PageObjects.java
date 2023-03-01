package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UBA_CampaignScreen_PageObjects extends Globals {
    public UBA_CampaignScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of DashBoard screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Campaigns']")
    public WebElement hdr_Campaigns;

    @FindBy(xpath = "//div[contains(text(),'Create Campaign')]")
    public WebElement btn_CreateCampaigns;

    @FindBy(xpath = "//input[@placeholder='search campaigns']")
    public WebElement inp_SearchCampaign;

    @FindBy(xpath = "//button[@class='ant-btn ant-dropdown-trigger']")
    public WebElement drp_Filters;

    @FindBy(xpath = "//div[@class='ant-dropdown-menu-submenu-title']")
    public WebElement drp_status;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']/div")
    public List<WebElement> lbl_tableRowValues;

    @FindBy(xpath = "//div[@class='ant-dropdown-menu-submenu-title']")
    public WebElement lnk_BacktoCamp;

    @FindBy(xpath = "//div[@class='queue-container-main']/div[1]")
    public WebElement lbl_CampStatus;

    @FindBy(xpath = "//div[@class='queue-container-main']/div[2]")
    public WebElement lbl_CampTitle;

    @FindBy(xpath = "//div[@class='queue-container-main']/div[3]")
    public WebElement hdr_CampMessage;

    @FindBy(xpath = "//div[@class='queue-container-main']/div[4]")
    public WebElement lbl_CampMessage;







}
