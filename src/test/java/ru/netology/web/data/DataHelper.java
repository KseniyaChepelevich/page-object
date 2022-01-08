package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.CardReplenishmentPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String cardBalance;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559000000000001", "10000");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559000000000002", "10000");
    }

    public static int checkBalanceWhereTransfer(int balance, int amountForTransfer) {
        int finalBalance = balance - amountForTransfer;
        return finalBalance;
    }

    public static int checkBalanceOfRechargeableCard(int balance, int amountForTransfer) {
        int finalBalance = balance + amountForTransfer;
        return finalBalance;
    }

}
