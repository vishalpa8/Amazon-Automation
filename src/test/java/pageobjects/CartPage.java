package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import reusable.WebReusableComponents;

public class CartPage extends WebReusableComponents {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    By activeItem = By.xpath("//*[@data-name = 'Active Items']");

    public boolean getActiveItemStatus(){
        waitUntilPageToBeReady();
        return elementVisible(activeItem);
    }
}
