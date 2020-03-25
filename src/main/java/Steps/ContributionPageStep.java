package Steps;

import Pages.ContributionsPage;
import io.qameta.allure.Step;

import java.util.HashMap;
import static org.junit.Assert.assertTrue;

public class ContributionPageStep {
    @Step("Поле {0} заполняется значением {1}")
    public void fullfill(String field, String value){
        new ContributionsPage().fillField(field, value);
    }
    @Step("Выбирается период {0}")
    public void select(String text){
        new ContributionsPage().selectChosen(text);
    }
    @Step("Нажимаю {0}")
    public void clickChosen(String text){
        new ContributionsPage().clickOnChosen(text);
    }
    @Step("Поле заполнено значением {0}")
    public void checkFillField(String field, String value){
        String actual = new ContributionsPage().assertAll(field,value);
        assertTrue(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, actual, value),
                actual.equals(value));
    }
    @Step("поля заполнены верно")
    public void assertAll(HashMap<String, String> fields){
        fields.forEach(this::checkFillField);
    }
}
