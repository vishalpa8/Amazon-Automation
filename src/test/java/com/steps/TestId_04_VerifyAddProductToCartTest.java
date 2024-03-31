package com.steps;

import dataproviders.YamlDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.HomePage;
import pageobjects.LanderPage;
import pageobjects.ProductPage;
import reusable.TestConfiguration;

import java.util.Map;

public class TestId_04_VerifyAddProductToCartTest extends TestConfiguration {
    HomePage homePage;

    LanderPage landerPage;

    ProductPage productPage;

    CartPage cartPage;

    @Test(dataProvider = "testData-dataSource", dataProviderClass = YamlDataProvider.class)
    public void testId_04_AddProductToCartTest(Map<String,String> data){
        String product = data.get("ProductName");
        String category = data.get("Category");


        launchAppUrl();
        removeCaptchaPage();
        selectCategoryFromDropDown(category);
        searchAProduct(product);
        clickSearchIcon();
        clickRequiredProduct(product);
        verifyProductName(product);
        clickAddToCart();
        clickCart();
        verifyProductAdded();

    }


    private void launchAppUrl(){
        driver.get(appUrl);
    }

    private void removeCaptchaPage(){
        homePage = new HomePage(driver);
        if(homePage.isCaptchaCharactersPageVisible()){
            driver.navigate().refresh();
        }
    }

    private void selectCategoryFromDropDown(String category){
        homePage.selectSearchDropDown(category);
    }

    private void searchAProduct(String prod){
        homePage.searchProduct(prod);
    }

    private void clickSearchIcon(){
        homePage.clickSearchIcon();
    }

    private void clickRequiredProduct(String prod){
        landerPage = new LanderPage(driver);
        landerPage.selectProduct(prod);
    }

    private void verifyProductName(String productName){
        productPage = new ProductPage(driver);
        String prodName = productPage.getProductName();

        Assert.assertEquals(productName,prodName,"Error: Product name is not matched");
    }

    private void clickAddToCart(){
        productPage.clickAddToCart();
    }

    private void clickCart(){
        productPage.clickCartBtn();
    }

    private void verifyProductAdded(){
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getActiveItemStatus(),"Error: Item is not added on cart");
    }
}
