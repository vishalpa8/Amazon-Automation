package reusable;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static String folderName;

    public WebDriver setupDriver(String browserType) {
        WebDriver driver = null;

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
        return getDriver();
    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver){
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

    public String getBrowser(){
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") +
                    File.separator + "src//test//resources" + File.separator + "globalSettings.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return System.getProperty("browser") == null ?
                properties.getProperty("browser") : System.getProperty("browser");
    }
}
