package PageObjects;

import Utils.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Access_RatesScreen_PageObjects extends Globals {
    public Access_RatesScreen_PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Define page objects of Rates screen
    @FindBy(xpath = "//div[@class='header-block']/div[text()='Rates']")
    public WebElement hdr_Rates;

    //table headers
    @FindBy(xpath = "//div[contains(text(),'Network provider')]")
    public WebElement lbl_NetwrokProvider;

    @FindBy(xpath = "//div[contains(text(),'Country')]")
    public WebElement lbl_Country;

    @FindBy(xpath = "//div[contains(text(),'SMS cost(Promotional)')]")
    public WebElement lbl_smsCostPromotional;

    @FindBy(xpath = "//div[contains(text(),'SMS cost(Transactional)')]")
    public WebElement lbl_SMScostTransactional;


    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']/div")
    public List<WebElement> lbl_tableRowValues;

    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']/div[1]")
    public List<WebElement> td_NetworkProvider;

    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']/div[4]")
    public List<WebElement> td_SmsCostTransactional;

    @FindBy(xpath = "//div[@class='table-content-div ng-star-inserted']/div[3]")
    public List<WebElement> td_SmsCostPromotional;






}
