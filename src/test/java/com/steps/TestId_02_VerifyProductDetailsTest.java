package com.steps;

import dataproviders.YamlDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LanderPage;
import pageobjects.ProductPage;
import reusable.TestConfiguration;

import java.util.Map;

public class TestId_02_VerifyProductDetailsTest extends TestConfiguration {
    HomePage homePage;

    LanderPage landerPage;

    ProductPage productPage;

    @Test(dataProvider = "testData-dataSource", dataProviderClass = YamlDataProvider.class)
    public void testId02_productDetailsTest(Map<String,String> data){
        String product = data.get("ProductName");
        String category = data.get("Category");


        launchAppUrl();
        removeCaptchaPage();
        selectCategoryFromDropDown(category);
        searchAProduct(product);
        clickSearchIcon();
        verifyMentionedProductIsVisible(product);
        clickRequiredProduct(product);
        verifyProductName(product);
        verifyAddToCartButtonVisible();
        verifyBuyNowBtnVisible();
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

    private void verifyMentionedProductIsVisible(String product){
        landerPage = new LanderPage(driver);
        boolean status = landerPage.getProductVisibleStatus(product);
        Assert.assertTrue(status,"Error: Product Not Found");
    }

    private void clickRequiredProduct(String prod){
        landerPage.selectProduct(prod);
    }

    private void verifyProductName(String productName){
        productPage = new ProductPage(driver);
        String prodName = productPage.getProductName();

        Assert.assertEquals(productName,prodName,"Error: Product name is not matched");
    }

    private void verifyAddToCartButtonVisible(){
        Assert.assertTrue(productPage.getAddToCartBtnStatus(),"Error: Add to cart btn is missing");
    }

    private void verifyBuyNowBtnVisible(){
        Assert.assertTrue(productPage.getBuyNowBtnStatus(),"Error: Add to cart btn is missing");
    }
}
