package Steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import java.util.Map;

public class ScenarioSteps {
    MainPageStep mainPageStep = new MainPageStep();
    ContributionPageStep contributionPageStep = new ContributionPageStep();

    @When("Перейти в меню – Вклады")
    public void selectMenuItem(){
        mainPageStep.selectMenuItem();
    }

    @Then("^заполняются поля")
    public void fullfill(DataTable dataTable){
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        dataMap.forEach((field, value)-> {contributionPageStep.fullfill(field, value); });
    }
    @Then("^Выбирается период \"(.+)\"$")
    public void select(String text){
        contributionPageStep.select(text);
    }
    @Then("^Кликаю на \"(.+)\"$")
    public void ckickOnChosen(String text){
        contributionPageStep.clickChosen(text);
    }

    @Then("^значения равны:$")
    public void assertAll(DataTable dataTable){
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        dataMap.forEach((field, value)-> {contributionPageStep.checkFillField(field, value); });
    }
}
