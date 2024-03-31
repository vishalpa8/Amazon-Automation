package com.steps;

import dataproviders.YamlDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.HomePage;
import reusable.TestConfiguration;

import java.util.Map;

public class TestId_05_VerifyFooterLinksTest extends TestConfiguration {
    HomePage homePage;

    @Test(dataProvider = "testData-dataSource", dataProviderClass = YamlDataProvider.class)
    public void testId_05_FooterLinksTest(Map<String,String> data){
        launchAppUrl();
        removeCaptchaPage();
        scrollDownToFooter();
        verifyNecessaryFooterLinks();
        verifyNecessaryLinksClickable();
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

    private void scrollDownToFooter(){
        homePage.scrollDownToFooter();
    }

    private void verifyNecessaryFooterLinks(){
        SoftAssert sf = new SoftAssert();

        sf.assertTrue(homePage.isAboutUsVisible(),"Error: About us not found on page.");
        sf.assertTrue(homePage.isCareersLinkVisible(),"Error: Career Link is not visible on page ");
        sf.assertTrue(homePage.isHelpLinkVisible(),"Error: Help Link is not visible on page ");

        sf.assertAll();
    }

    private void verifyNecessaryLinksClickable(){
        SoftAssert sf = new SoftAssert();

        sf.assertTrue(homePage.isAboutUsClickable(),"Error: About us is not clickable.");
        sf.assertTrue(homePage.isCareersLinkClickable(),"Error: Career Link is not clickable.");
        sf.assertTrue(homePage.isHelpLinkClickable(),"Error: Help Link is not clickable");

        sf.assertAll();
    }
}
