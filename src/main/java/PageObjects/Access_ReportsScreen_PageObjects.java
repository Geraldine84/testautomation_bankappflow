package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_ReportsScreen_PageObjects extends Globals {
    public Access_ReportsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Report screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Reports']")
    public WebElement hdr_Reports;

    @FindBy(xpath = "//span[contains(text(),'Summary Type:')]")
    public WebElement lbl_SummaryType;

    @FindBy(xpath = "//div[contains(text(),'Download log')]")
    public WebElement btn_DownloadLogs;

    @FindBy(xpath = "//*[@class='ant-select-selection-item ng-star-inserted']")
    public WebElement drp_SelectSummaryType;

    @FindBy(xpath = "//div[contains(text(),'Daily')]")
    public WebElement drp_SelectSummaryTypeDaily;

    @FindBy(xpath = "//div[contains(text(),'Monthly')]")
    public WebElement drp_SelectSummaryTypeMonthly;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

   



}
