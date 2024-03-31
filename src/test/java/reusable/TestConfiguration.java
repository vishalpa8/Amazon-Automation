package reusable;

import dataproviders.YamlDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestConfiguration extends WebDriverFactory {
    public final String appUrl = "https://www.amazon.in";
    static public YamlDataReader yamlDataReader = null;

    public WebDriver driver;

    @BeforeSuite
    public void setupData(){
        yamlDataReader = new YamlDataReader("details.yml");
    }


    @BeforeTest
    public void tearUp() {
        driver = setupDriver(getBrowser());
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
