package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishmentPage {

    private SelenideElement form = $(".form");

    private static SelenideElement amountField = $("[data-test-id='amount'] input");
    private static SelenideElement fromField = $("[data-test-id='from'] input");

    private static SelenideElement transferButton = $("[data-test-id='action-transfer']");


    public CardReplenishmentPage() {
        form.shouldBe(visible);
    }

    public void transferMany(DataHelper.CardInfo fromCardInfo) {
        String amountToTransferForTest = "500";
        amountField.setValue(amountToTransferForTest);
        fromField.setValue(fromCardInfo.getCardNumber());

        transferButton.click();
    }

}
