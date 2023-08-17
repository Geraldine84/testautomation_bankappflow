package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_DepartmentsScreen_PageObjects extends Globals {
    public Access_DepartmentsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Department screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Departments']")
    public WebElement hdr_Departments;

    @FindBy(xpath = "//div[contains(text(),'Create new department')]")
    public WebElement btn_CreateNewDept;

    @FindBy(xpath = "//div[contains(text(),'Go back to Departments')]")
    public WebElement lnk_backtoDept;

    @FindBy(xpath = "//input[@placeholder='search department']")
    public WebElement inp_SearchDept;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']//div")
    public List<WebElement> lbl_tableRows;

   



}
