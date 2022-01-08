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

    String amountToTransfer = "500";
    String newAmountToTransfer = "10500";


    public void transferMany(DataHelper.CardInfo fromCardInfo, int amountToTransf) {

        String amount = Integer.toString(amountToTransf);
        amountField.setValue(amount);
        fromField.setValue(fromCardInfo.getCardNumber());

        transferButton.click();
    }


}
