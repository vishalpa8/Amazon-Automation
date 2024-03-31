package reusable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebReusableComponents {
    protected WebDriver driver;

    public WebReusableComponents(WebDriver driver){
        this.driver = driver;
    }


    public void selectByVisibleText(By by, String text){
        WebElement ele = driver.findElement(by);
        Select select = new Select(ele);
        select.selectByVisibleText(text);
    }

    public void enterText(By by, String text){
        waitElementUntilVisible(by,10);
        driver.findElement(by).sendKeys(text);
    }

    public void clickElement(By by){
        waitElementUntilClickable(by,10);
        driver.findElement(by).click();
    }

    public boolean elementVisible(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean elementClickable(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getWebElementList(By by){
        waitElementUntilVisible(by,10);
        return driver.findElements(by);
    }


    public void waitElementUntilVisible(By by, long timeOutInSeconds){
        (new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitElementUntilClickable(By by, long timeOutInSeconds){
        (new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)))
                .until(ExpectedConditions.elementToBeClickable(by));
    }


    public void waitUntilPageToBeReady(){
        ((JavascriptExecutor)driver).executeScript
                ("return document.readyState").equals("complete");
    }

    public void switchToLatestWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String getTextFromElement(By by){
        waitElementUntilVisible(by,10);
        return driver.findElement(by).getText().trim();
    }

    public void waitUntil(int timeInSeconds) {
        long endTime = System.currentTimeMillis()+ (timeInSeconds * 1500L);
        while (System.currentTimeMillis() < endTime) {
        }
    }

    public void scrollDownToFooter(){
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void returnToOriginalWindow(){
        String originalHandle = driver.getWindowHandle();
        switchToLatestWindow();
        driver.close();
        driver.switchTo().window(originalHandle);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
