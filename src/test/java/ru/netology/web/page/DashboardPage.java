package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static ElementsCollection cards = $$(".list__item");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    private static ElementsCollection buttons = $$("button");

    private SelenideElement heading = $("[data-test-id=dashboard]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static int getCardBalance(int index) {
        int i = index;
        val text = cards.get(i).text();
        return extractBalance(text);
    }

//    public static int getCardsBalanse() {
//        int balanceFirstCard = getCardBalance(1);
//        int balanceSecondCard = getCardBalance(2);
//        return balanceFirstCard, balanceSecondCard;
//    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return  Integer.parseInt(value);
    }

    public static CardReplenishmentPage topUpBalance(int index) {
        int i = index;
        buttons.get(i).click();
        return new CardReplenishmentPage();

    }




}
