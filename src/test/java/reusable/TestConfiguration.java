package reusable;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import dataproviders.YamlDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestConfiguration extends GenericComponents {
    public final String appUrl = "https://www.amazon.in";
    static public YamlDataReader yamlDataReader = null;

    public WebDriver driver;

    @BeforeSuite
    public void setupData(){
        yamlDataReader = new YamlDataReader("details.yml");
    }


    @BeforeTest
    public void tearUp(){
        setupDriver("");
        driver = getDriver();
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }



    @AfterSuite
    public void openReportInBrowser() {
        String reportPath = folderName + File.separator + "index.html";
        try {
            File htmlFile = new File(reportPath);
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
