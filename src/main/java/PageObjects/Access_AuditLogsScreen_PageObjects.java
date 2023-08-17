package PageObjects;

import Utils.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_AuditLogsScreen_PageObjects extends Globals {
    public Access_AuditLogsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Audit Logs screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Audit Logs']")
    public WebElement hdr_AuditLogs;

    @FindBy(xpath = "//nz-select[@nzplaceholder='Action']")
    public WebElement drp_Action_Department;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

    @FindBy(xpath = "//div[@class='first-ob-dv1']")
    public List<WebElement> lbl_tableRows;

    public void selectDropdownValue(String Value) throws InterruptedException {
        wait(1);

        drp_Action_Department.click();

        driver.findElement(By.xpath("//div[contains(text(),'"+Value+"')]")).click();


    }



}
