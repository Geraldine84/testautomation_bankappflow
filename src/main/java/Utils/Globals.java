package Utils;
/*
 * @author snaik
 */

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Properties;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class Globals {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";
    //global variables declaration
    public static WebDriver driver = null;
    public static Properties prop;
    public static ExtentTest extentLog;
    public static boolean result;
    //declare user directory path
    public static String directoryPath = System.getProperty("user.dir");
   // String Extentfilename = directoryPath + File.separator + "Reports" + File.separator + "ExtentReport" + File.separator + getConfigData("ExtentReportName") + ".html";

    static File destTxtLog = new File(directoryPath + File.separator + "Reports" + File.separator + "LogReport" + File.separator + "LogTextFile.txt");
    static File ScreenShotFilePath = new File(directoryPath + File.separator + "Reports/ScreenShots/");

    //global method to log the results -- Log a message to the console
    public static void log(String msg) {
        System.out.println(msg);

        try {
            FileWriter fw = new FileWriter(destTxtLog, true); // the true will
            // append the new data
            fw.write(msg + "\n");// appends the string to the file
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public static void reportLog(String msg) {
        try {
            extentLog.info(msg);
        } catch (Exception e) {
        e.getMessage();
        }
    }

    //global method for synchronisation - wait statements
    //wait for given time
    public static void wait(int numOfSeconds) throws InterruptedException {
        log("Waiting for " + numOfSeconds + " seconds...");
        Thread.sleep(numOfSeconds * 1000L);
    }

    //Explicit wait
    public static void Wait(WebElement element, Duration timeoutInSeconds)
            throws HeadlessException {

        log("Waiting for " + element + " for up to " + timeoutInSeconds + " seconds.");

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
           // wait.until(ExpectedConditions.elementToBeClickable(element));

            log("Element found.");
        } catch (Exception e) {
            log("*** Unable to find element");
            log(e.getMessage());
            log("***");
            // Fail the test
            fail();
        }
    }

    //initialise to Read Properties or config File
    public static void readPropertyFile() throws IOException {

        prop = new Properties();

        File file = new File("./config.properties");
        FileReader obj = new FileReader(file);

        prop.load(obj);

    }

    //load to read all the data from config file
    public static String getConfigData(String Data) throws IOException {

        readPropertyFile();
        return prop.getProperty(Data);
    }

    //global method to capture the screenshot on failure
    public static String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        //take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = ScreenShotFilePath + File.separator + testCaseName + "_" + getCurrentDateTime() + ".png";
        FileUtils.copyFile(source, new File(destinationFile));

        return "File://" + destinationFile;
    }

    //get current date time
    public static String getCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        // log("Current Time Stamp Default Format: "+dateTime);
        return DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss").format(dateTime);
    }

    //global method to initialise the browser
    @BeforeSuite(alwaysRun = true)
    public static WebDriver setBrowser() throws IOException {
        //delete the log file exist
        FileUtils.forceDelete(destTxtLog);
    //extentLog= ExtentReportNG.extent.createTest("test");
        //Delete old screenshots from folder
        FileUtils.cleanDirectory(ScreenShotFilePath);
        log("Older Screenshots deleted ");

        prop = new Properties();
        FileInputStream fis = new FileInputStream(directoryPath + File.separator + "config.properties");

        prop.load(fis);
        String browserName = prop.getProperty("BrowserName");//System.getProperty("Browser","chrome");//prop.getProperty("BrowserName");
        log("Selected Browser is -- " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {

            //call Chrome browser for local machine
            WebDriverManager.chromedriver().setup();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //disable cookies
            chromePrefs.put("profile.default_content_settings.cookies", 2);

            //Disable save password popup window
            chromePrefs.put("password_manager_enabled", false);
            chromePrefs.put("credentials_enable_service", false);

            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("no-sandbox");
            options.addArguments("disable-infobars");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            options.addArguments("--headless","--window-size=2560,1600");
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("Firefox")) {
            //call firefox
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("IE")) {
            //call Internet Explorer
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();

        } else if (browserName.equalsIgnoreCase("Safari")) {
            //call safari
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();


        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        log(browserName + " Browser Launched successfully!");

        //use it in separate method
        // driver.get(getConfigData("url_QA"));
        //wait(2);
        return driver;
    }

    //global teardown method to Close/quit the browser
    @AfterSuite(alwaysRun = true)
    public static void closeBrowser() {
        try {
            driver.quit();
            log("Browser closed successfully!");
        } catch (Exception e) {
            log("*** Error closing browser.");
            log("*** Error msg: " + e);
        }

    }

    //global method to verify elements existence
    public void verifyElementExistence(WebElement elem, String elementExistence, String elementType) throws InterruptedException {

        //check for the element type -- is Displayed condition
        wait(2);
        if (elementExistence.equalsIgnoreCase("isDisplayed") && elem.isDisplayed()) {
            log(elementType + " '" + elementExistence + "' properly. Passed.");
            assertTrue(true);
            result = true;
        }
        //check for the element type -- is Enabled condition
        else if (elementExistence.equalsIgnoreCase("isEnabled") && elem.isEnabled()) {
            log(elementType + " '" + elementExistence + "' properly. Passed.");
            assertTrue(true);
            result = true;
        }
        //check for the element type -- is Selected condition
        else if (elementExistence.equalsIgnoreCase("isSelected") && elem.isSelected()) {
            log(elementType + " '" + elementExistence + "' properly. Passed.");
            assertTrue(true);
            result = true;
        } else {
            log(elementType + " '" + elementExistence + "' failed. Did not find: " + elementType);
            StackTraceElement ste = new Exception().getStackTrace()[1];
            log(ste.getClassName() + "/" + ste.getMethodName() + ":" + ste.getLineNumber());
            fail();
            result = false;
        }
    }

    //get the method or test name before and after execution
    @BeforeMethod(alwaysRun = true)
    public void getTestNameBeforeExecution(Method method){
        log("--------------------- "+method.getName() +"  --> Test Execution Started ----------------");
    }
    @AfterMethod(alwaysRun = true)
    public void getTestNameAfterExecution(Method method){
        log("---------------------  "+method.getName() +"  --> Test Execution Completed  ----------------\n");
    }

    //Read Test data from Excel file
    public HashMap<String, String> ReadExcelFile(String filePath, String fileName, String sheetName, int set)
            throws IOException {
        File excelFile = new File(filePath + File.separator + fileName);
        FileInputStream excelInputStream = new FileInputStream(excelFile);
        Workbook excelWorkbook;

        // Assuming XLSX here. If XLS use HSSFWorkbook
        excelWorkbook = new XSSFWorkbook(excelInputStream);

        // Read the sheet inside the Excel workbook
        Sheet excelSheet = excelWorkbook.getSheet(sheetName);

        // Get the number of rows
        // int rowCount =
        // excelSheet.getLastRowNum()-excelSheet.getFirstRowNum();

        // Read all the rows
        // for (int i=0;i<=rowCount;i++){
        Row excelRow = excelSheet.getRow(0);
        Row excelSet = excelSheet.getRow(set);

        HashMap<String, String> test = new HashMap<>();

        // Now we need to read all the cell (column) values in each row
        for (int j = 0; j < excelRow.getLastCellNum(); j++) {
            // Output value to the console for now
            String key = excelRow.getCell(j).getStringCellValue();
            String value = excelSet.getCell(j).getStringCellValue();
            test.put(key, value);
        }

        // Close the file stream
        excelWorkbook.close();
        excelInputStream.close();
        return test;

    }

    // Encryption and Decryption methods
    // Using AES algorithm and using a 16 digit hexadecimal code as key for
    // decryption

    private static Key generateKey() {
        return new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    }


    // This method will encrypt the password read from the data sheet
    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return new Base64().encodeToString(encryptedByteValue);

    }

    // This method will decrypt the password read from the data sheet

    public static  String decrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = new Base64().decode(value);
        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
        return new String(decryptedByteValue, StandardCharsets.UTF_8);

    }
    //method to scroll to the element view
    public static void scrollToElement(WebElement element) throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element);
        log("Scroll to element view" + element);
    }

    //method to click on the element wait for the element to be clickable
    public static void clickWait(WebElement element) throws Exception {
        int a = 10;
        while (a > 0) {

            try {
                element.isEnabled();
                element.click();
                break;
            } catch (Exception e) {
                wait(2);
                log("Waiting for the element to be clickable");
                a--;
                if (a == 0) {
                    fail();
                }
            }
        }
        wait(2);

    }
    //Verify the element text with expected text value
    public boolean verifyElement(WebElement elem, String elementText, String elementType)
            throws HeadlessException {
        Wait(elem, Duration.ofSeconds(10));
        String elementDisplayed = elem.getText();
        if (elementDisplayed.contains(elementText)) {
            log(elementType + " '" + elementText + "' displayed properly. Passed.");
            assertTrue(true);
            result = true;
        } else {
            log(elementType + " '" + elementText + "' failed.");
            log("Expected value: \"" + elementText + "\"");
            log("Actual value: \"" + elementDisplayed + "\"");
            StackTraceElement ste = new Exception().getStackTrace()[1];
            log(ste.getClassName() + "/" + ste.getMethodName() + ":" + ste.getLineNumber());
            fail();
            result = false;
        }
        return true;
    }

    //Verify actual and expected results

    public void verifyResult(String Actual_Value, String Expected_Value) throws Exception {
        wait(2);
        try {
            if(Actual_Value.contains(Expected_Value) || (Actual_Value.equalsIgnoreCase(Expected_Value))) {
                wait(1);
                log("ActualResult is: "+Actual_Value+" and Expected Result is: "+Expected_Value+" as expected --- PASSED");
                assertTrue(true);
            }else {
                log("ActualResult is: "+Actual_Value+" and Expected Result is: "+Expected_Value+" Results are not as expected --- FAILED");
                fail();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


}

