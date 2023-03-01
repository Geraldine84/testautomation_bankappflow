package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UBA_AuditLogsScreen_PageObjects extends Globals {
    public UBA_AuditLogsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of DashBoard screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Audit Logs']")
    public WebElement hdr_AuditLogs;

    @FindBy(xpath = "//input[@class='ant-select-selection-search-input ng-untouched ng-pristine ng-valid']")
    public List<WebElement> drp_Action_Department;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

   public void selectDropdownValue(String Value){



   }



}
