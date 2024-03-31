package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import reusable.WebReusableComponents;

import java.util.List;

public class LanderPage extends WebReusableComponents {
    public LanderPage(WebDriver driver) {
        super(driver);
    }

    By brandList = By.xpath("//*[@id='brandsRefinements']//li//a");
    By filtersList = By.xpath("//*[@id='filters']//li//a");
    By productList = By.xpath("//*[@id='search']//h2/a");


    public void selectBrand(String brand){
        List<WebElement> lists = getWebElementList(brandList);
        for (WebElement list : lists){
            String name = list.getText();
            if(brand.equalsIgnoreCase("Xiaomi")){
                if(name.equalsIgnoreCase(brand) ||
                        name.equalsIgnoreCase("Redmi")){
                    list.click();
                    return;
                }
            }
        }
        System.out.println("Defined Brand Won't Found");
    }

    public void selectFilter(String value){
        waitUntilPageToBeReady();
        List<WebElement> lists = getWebElementList(filtersList);
        for (WebElement list : lists){
            String name = list.getText();
            if(name.equalsIgnoreCase(value)){
                list.click();
                return;
            }
        }
        System.out.println("Defined filter => " + value + " Won't Found");
    }

    public boolean getProductVisibleStatus(String prod){
        waitUntilPageToBeReady();
        List<WebElement> productLists = getWebElementList(productList);
        for (WebElement product : productLists){
            if(product.getText().contains(prod)){
                return true;
            }
        }
        return false;
    }
    public void selectProduct(String prod){
        waitUntilPageToBeReady();
        List<WebElement> productLists = getWebElementList(productList);
        for (WebElement product : productLists){
            if(product.getText().contains(prod)){
                product.click();
                switchToLatestWindow();
                return;
            }
        }
    }
}
