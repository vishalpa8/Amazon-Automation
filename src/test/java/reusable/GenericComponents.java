package reusable;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;

public class GenericComponents {

    protected static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static String folderName;

    public void setupDriver(String browserType) {
        WebDriver driver = null;

        if(browserType.equalsIgnoreCase("")) browserType = "chrome";

        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            // Add support for other browsers if needed
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
        driver.manage().window().maximize();
        setDriver(driver);
    }

    public WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }

    public String getScreenshot(String testCaseName, String directoryName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot)getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        folderName = directoryName;
        File file = new File(directoryName + File.separator + testCaseName + ".png");

        try {
            FileUtils.copyFile(source,file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }
}
