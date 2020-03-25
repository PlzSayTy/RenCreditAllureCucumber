package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContributionsPage extends BasePage{
    @FindBy(xpath = "//span[contains(text(), 'Рубли')]")
    WebElement rubles;
    @FindBy(xpath = "//input[contains(@name, 'amount')]")
    WebElement amountOfContribution;
    @FindBy(xpath = "//input[contains(@name, 'replenish')]")
    WebElement replenish;
    @FindBy(xpath = "//span[contains(@class, 'js-calc-result js-calc-result-noanim')]")
    WebElement incomeWillBe;
    @FindBy(xpath = "//span[contains(@class, 'js-calc-rate')]")
    WebElement precent;
    @FindBy(xpath = "//select[contains(@class, 'calculator__slide-input js-slide-value')]")
    WebElement forSelect;
    @FindBy(xpath = "//input[contains(@name, 'capitalization')]//parent::div")
    WebElement capitalization;
    public void fillField(String element, String value){
        String s = rememberCurrentPrecent();
        switch (element){
            case "Сумма вклада":
                fullfill(amountOfContribution, value);
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//span[contains(@class, 'js-calc-rate')]"), s)));
                if (amountOfContribution.getAttribute("value").equals(value)){
                    break;
                }else{
                    fullfill(amountOfContribution, value);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "Ежемесячное пополнение":
                fullfill(replenish, value);
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//span[contains(@class, 'js-calc-result js-calc-result-noanim')]"), s)));
                if (replenish.getAttribute("value").equals(value)){
                    break;
                }else{
                    fullfill(replenish, value);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public String rememberCurrentPrecent(){
        String currentPrecent = precent.getText();
        return currentPrecent;
    }
    public void selectChosen(String text){
        select = new Select(forSelect);
        select.selectByVisibleText(text);
    }

    public void clickOnChosen(String element){
        switch (element){
            case "Eжемесячная капитализация":
                clickOnElement(driver.findElement(By.xpath("//input[contains(@name, 'capitalization')]//parent::div")));
                break;
        }
    }

    public String assertAll(String value, String text){
        String s = null;
        switch (value){
            case "Ставка":
                s = precent.getText();
                break;
            case "К снятию":
                s = driver.findElement(By.xpath("//div[contains(@class, 'calculator__dep-result')]//span[contains(@class, 'js-calc-result')]")).getText();
                break;
        }
        return s;
    }

}
