package com.steps;

import dataproviders.YamlDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LanderPage;
import reusable.TestConfiguration;

import java.util.Map;

public class TestId_03_VerifySearchFunctionalityWithSuggestions extends TestConfiguration {
    HomePage homePage;


    @Test(dataProvider = "testData-dataSource", dataProviderClass = YamlDataProvider.class)
    public void testId_03_SearchFunctionalityWithSuggestions(Map<String,String>data){
        String product = data.get("ProductName");
        String category = data.get("Category");

        launchAppUrl();
        removeCaptchaPage();
        selectCategoryFromDropDown(category);
        searchAProduct(product);
        verifyPageReflectSearchSuggestions(product);
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

    private void verifyPageReflectSearchSuggestions(String product){
        boolean isSuggestionsCorrect = homePage.getSearchSuggestionsStatus(product);
        Assert.assertTrue(false,"Error: searchbar suggestions do not match the search text");
    }
}
