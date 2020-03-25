package Pages;

import Steps.BaseStep;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    Actions builder;
    Wait<WebDriver> wait;
    JavascriptExecutor executor;
    Select select;

    public BasePage(){
        driver = BaseStep.getDriver();
        PageFactory.initElements(BaseStep.getDriver(), this);
        wait = new WebDriverWait(driver,10, 1000);
        builder = new Actions(driver);
        executor = (JavascriptExecutor)driver;
    }

    public void clickOnElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void fullfill(WebElement element, String value){
        clickOnElement(element);
        element.clear();
        //element.sendKeys("");
        element.sendKeys(value);
    }

}
