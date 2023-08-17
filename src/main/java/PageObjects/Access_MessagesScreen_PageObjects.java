package PageObjects;

import Utils.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_MessagesScreen_PageObjects extends Globals {
    public Access_MessagesScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Messages screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Messages']")
    public WebElement hdr_Messages;

    @FindBy(xpath = "//div[contains(@class,'sms-log-div')]")
    public WebElement btn_Logs;

    @FindBy(xpath = "//div[contains(text(),'Download log')]")
    public WebElement btn_DownloadLogs;

    @FindBy(xpath = "//input[@placeholder='Search phone number']")
    public WebElement inp_SearchMessages;

    @FindBy(xpath = "//button[@class='ant-btn ant-dropdown-trigger']")
    public WebElement drp_Filters;

    @FindBy(xpath = "//div[@class='ant-dropdown-menu-submenu-title']")
    public List<WebElement> drp_status;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']/div")
    public List<WebElement> lbl_tableRowValues;

    public void selectDropdownValue(String Value) throws InterruptedException {
    wait(1);

        drp_Filters.click();
        drp_status.get(0).click();

        driver.findElement(By.xpath("//li[contains(text(),'"+Value+"')]")).click();


    }



}
