package com.steps;

import dataproviders.YamlDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LanderPage;
import reusable.TestConfiguration;

import java.util.Map;

public class TestId_01_VerifyUserSearchForGivenProductTest extends TestConfiguration {
    HomePage homePage;
    LanderPage landerPage;


    @Test(dataProvider = "testData-dataSource", dataProviderClass = YamlDataProvider.class)
    public void testId_01_UserSearchForExistingProduct(Map<String,String> data){
        String product = data.get("ProductName");
        String category = data.get("Category");
        String searchProduct = data.get("SearchProduct");
        String ram = data.get("Ram");
        String operatingType = data.get("OperatingSystem");
        String screenSize = data.get("ScreenSize");
        String batteryCapacity = data.get("BatteryCapacity");

        launchAppUrl();
        removeCaptchaPage();
        selectCategoryFromDropDown(category);
        searchAProduct(searchProduct);
        clickSearchIcon();
        selectRam(ram);
        selectOperatingSystem(operatingType);
        selectScreenSize(screenSize);
        selectBatteryCapacity(batteryCapacity);

        verifyMentionedProductIsVisible(product);
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

    private void selectRam(String ram){
        landerPage = new LanderPage(driver);
        landerPage.selectFilter(ram);
    }

    private void selectBatteryCapacity(String cap){
        landerPage.selectFilter(cap);
    }
    private void selectOperatingSystem(String type){
        landerPage.selectFilter(type);
    }
    private void selectScreenSize(String size){
        landerPage.selectFilter(size);
    }

    private void verifyMentionedProductIsVisible(String product){
        boolean status = landerPage.getProductVisibleStatus(product);

        Assert.assertTrue(false,"Error: Product Not Found");
    }


}
