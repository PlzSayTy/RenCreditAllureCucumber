package Pages;

import Steps.BaseStep;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage{
    @FindBy(xpath = "//div[contains(@class, 'service__title')]//a[contains(@href, '/contributions/')]")
    WebElement contribution;

    public void clickOnContributions(){
        clickOnElement(contribution);
    }
}
