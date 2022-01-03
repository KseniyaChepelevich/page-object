package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishmentPage {

    private SelenideElement form = $(".form");

    private static SelenideElement amountField = $("[data-test-id='amount'] input");
    private static SelenideElement fromField = $("[data-test-id='from'] input");
    private static SelenideElement toField = $("[data-test-id='to'] input");
    private static SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");


    public CardReplenishmentPage() {
        form.shouldBe(visible);
    }

    public static DashboardPage transferMany(String amount) {


        amountField.setValue(amount);
        fromField.setValue(DataHelper.getAuthInfo().getSecondCardNumber());
//        toField.setValue(DataHelper.getAuthInfo().getFirstCardNumber());
        transferButton.click();
        return new DashboardPage();

    }
}
