package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UBA_ReportsScreen_PageObjects extends Globals {
    public UBA_ReportsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of DashBoard screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Reports']")
    public WebElement hdr_Reports;

    @FindBy(xpath = "//span[contains(text(),'Summary Type:')]")
    public WebElement lbl_SummaryType;

    @FindBy(xpath = "//div[contains(text(),'Download log')]")
    public WebElement btn_DownloadLogs;

    @FindBy(xpath = "//*[@class='ng-tns-c106-8 ant-select-selector']")
    public WebElement drp_SelectSummaryType;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

   



}