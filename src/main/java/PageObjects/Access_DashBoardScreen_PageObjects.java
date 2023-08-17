package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_DashBoardScreen_PageObjects extends Globals {
    public Access_DashBoardScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of DashBoard screen
    @FindBy(xpath = "//div[@class='ng-star-inserted'][text()='Dashboard']")
    public WebElement hdr_Dashboard;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Dashboard']")
    public WebElement menu_Dashboard;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Campaigns']")
    public WebElement menu_Campaigns;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Messages']")
    public WebElement menu_Messages;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Rates']")
    public WebElement menu_Rates;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Reports']")
    public WebElement menu_Reports;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Departments']")
    public WebElement menu_Departments;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Audit Logs']")
    public WebElement menu_AuditLogs;

    @FindBy(xpath = "//div[@class='sidenav-list-menu']//div[text()='Settings']")
    public WebElement menu_Settings;

    @FindBy(xpath = "//div/nz-range-picker[contains(@class,'dateRangeBox')]")
    public WebElement Date_calender;

    @FindBy(xpath = "//input[@placeholder='Start date']")
    public WebElement inp_StartDate;

    @FindBy(xpath = "//input[@placeholder='End date']")
    public WebElement inp_EndDate;

    @FindBy(xpath = "//div[contains(text(),'Total SMS Units')]")
    public WebElement lbl_TotalsmsUnits;

    @FindBy(xpath = "//div[contains(text(),'Total SMS Count')]")
    public WebElement lbl_TotalsmsCount;

    @FindBy(xpath = "//div[contains(text(),'Total consumption')]")
    public WebElement lbl_TotalConsumption;

    @FindBy(xpath = "//div[@class='dshbrd-rw-1-cnt-txt-dv']")
    public List<WebElement> txt_sms;

    @FindBy(xpath = "//div[text()='Detailed Insights']")
    public WebElement lbl_Detailedinsights;

    @FindBy(xpath = "//div[@class='dshbrd-grph-hd-cntr-dv']/div[2]")
    public WebElement date_Detailedinsights;

    @FindBy(xpath = "//div[contains(text(),'Delivery Ratio')]")
    public WebElement hdrGraph_Deliveryratio;

    @FindBy(xpath = "//div[contains(text(),'Traffic volume by network')]")
    public WebElement hdrGraph_TraficVolByNtwrk;



}
