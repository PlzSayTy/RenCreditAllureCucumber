package Steps;

import Pages.MainPage;
import io.qameta.allure.Step;

public class MainPageStep {
    @Step("Перейти в меню – Вклады")
    public void selectMenuItem(){
        new MainPage().clickOnContributions();
    }
}
