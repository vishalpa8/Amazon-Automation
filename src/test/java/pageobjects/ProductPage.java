package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import reusable.WebReusableComponents;

public class ProductPage extends WebReusableComponents {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By productName = By.id("productTitle");
    By addToCartBtn = By.xpath("(//input[@id='add-to-cart-button'])[2]");
    By cartBtn = By.id("attach-sidesheet-view-cart-button");
    By buyNowBtn = By.id("buy-now-button");

    public String getProductName(){
        return getTextFromElement(productName);
    }

    public boolean getAddToCartBtnStatus(){
        waitElementUntilVisible(addToCartBtn,10);
        return elementVisible(addToCartBtn);
    }

    public boolean getBuyNowBtnStatus(){
        return elementVisible(buyNowBtn);
    }

    public void clickAddToCart(){
        clickElement(addToCartBtn);
    }

    public void clickCartBtn(){
        waitUntil(2);
        clickElement(cartBtn);
    }

}
