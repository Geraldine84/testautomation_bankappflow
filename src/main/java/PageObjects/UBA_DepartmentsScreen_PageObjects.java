package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UBA_DepartmentsScreen_PageObjects extends Globals {
    public UBA_DepartmentsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of DashBoard screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Departments']")
    public WebElement hdr_Departments;

    @FindBy(xpath = "//div[contains(text(),'Create new department')]")
    public WebElement btn_CreateNewDept;

    @FindBy(xpath = "//input[@placeholder='search department']")
    public WebElement inp_SearchMessages;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

   



}
