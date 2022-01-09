package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static ElementsCollection cards = $$(".list__item");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public void Dashboard() {
    }

    private SelenideElement firstCardButton = $$("[data-test-id=action-deposit]").first();
    private SelenideElement secondCardButton = $$("[data-test-id=action-deposit]").last();
    private static SelenideElement balance0001 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static SelenideElement balance0002 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");


    public CardReplenishmentPage chooseFirstCardToRecharge() {
        firstCardButton.click();
        return new CardReplenishmentPage();
    }

    public CardReplenishmentPage chooseSecondCardToRecharge() {
        secondCardButton.click();
        return new CardReplenishmentPage();
    }

    public static int getFirstCardBalance() {
        val text = balance0001.text();
        return extractBalance(text);
    }

    public static int getSecondCardBalance() {
        val text = balance0002.text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }





}
