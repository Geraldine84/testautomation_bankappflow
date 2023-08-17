package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_SettingsScreen_PageObjects extends Globals {
    public Access_SettingsScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Settings screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Settings']")
    public WebElement hdr_Settings;

    @FindBy(xpath = "//div[contains(@class,'sms-log-div')][contains(text(),'Sender ID')]")
    public WebElement lnk_SenderID;

    @FindBy(xpath = "//div[contains(@class,'sms-log-div')][contains(text(),'Users')]")
    public WebElement lnk_Users;

    @FindBy(xpath = "//div[contains(text(),'Add new user')]")
    public WebElement btn_AddNewUser;

    @FindBy(xpath = "//input[@name='users']")
    public WebElement inp_SearchUsers;

    @FindBy(xpath = "//div[@class='table-header-div']/div")
    public List<WebElement> lbl_tableHeaders;

   



}
