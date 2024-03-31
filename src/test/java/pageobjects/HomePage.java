package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reusable.WebReusableComponents;

import java.util.List;

public class HomePage extends WebReusableComponents {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    By searchDropDownDescription = By.id("searchDropdownBox");
    By searchBox = By.id("twotabsearchtextbox");
    By searchIcon = By.id("nav-search-submit-button");
    By captchaCharacters = By.id("captchacharacters");

    By searchSuggestionsList = By.xpath("//*[@id='nav-flyout-searchAjax']//div[1]/div[1]");

    By aboutUsLink = By.xpath("//*[text()='About Us']");
    By careerLink = By.xpath("//*[text()='Careers']");
    By helpLink = By.xpath("//*[text()='Help']");


    public void selectSearchDropDown(String value){
        selectByVisibleText(searchDropDownDescription,value);
    }

    public void searchProduct(String prod){
        enterText(searchBox,prod);
    }

    public void clickSearchIcon(){
        clickElement(searchIcon);
    }

    public boolean isCaptchaCharactersPageVisible(){
        return elementVisible(captchaCharacters);
    }

    public boolean getSearchSuggestionsStatus(String product){
        waitUntil(2);
        List<WebElement> suggestionLists = getWebElementList(searchSuggestionsList);

        for(WebElement list : suggestionLists){
            String text = list.getAttribute("aria-label");
            if(text != null && text.contains(product)){
                return false;
            }
        }

        return true;
    }

    public boolean isAboutUsVisible(){
        return elementVisible(aboutUsLink);
    }
    public boolean isAboutUsClickable(){
        return elementClickable(aboutUsLink);
    }


    public boolean isCareersLinkVisible(){
        return elementVisible(careerLink);
    }
    public boolean isCareersLinkClickable(){
        return elementClickable(careerLink);
    }

    public boolean isHelpLinkVisible(){
        return elementVisible(helpLink);
    }

    public boolean isHelpLinkClickable(){
        return elementClickable(helpLink);
    }

}
