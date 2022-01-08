package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardReplenishmentPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.DashboardPageNew;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    public void equalizeTheBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int a = DashboardPage.getCurrentBalanceFirstCard();
        int b = DashboardPage.getCurrentBalanceSecondCard();
        //int c;
        if (a - b > 0) {
            int amountToTransf = (a - b)/2;
            var cardInfo = DataHelper.getFirstCardInfo();
            var cardReplenishmentPage = dashboardPage.chooseSecondCardToRecharge();
            cardReplenishmentPage.transferMany(cardInfo, amountToTransf);
//            int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(a, amountToTransf);
//            int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(b, amountToTransf);
            int balanceFirstCardAfter = DashboardPage.getCurrentBalanceFirstCard();
            int balanceSecondCardAfter = DashboardPage.getCurrentBalanceSecondCard();

        }
        if (b - a > 0) {
            int amountToTransf = (b - a)/2;
            var cardInfo = DataHelper.getSecondCardInfo();
            var cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
            cardReplenishmentPage.transferMany(cardInfo, amountToTransf);
//            int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(a, newAmountToTransfer);
//            int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(b, newAmountToTransfer);
            int balanceFirstCardAfter = DashboardPage.getCurrentBalanceFirstCard();
            int balanceSecondCardAfter = DashboardPage.getCurrentBalanceSecondCard();
        }
    }


    int amountToTransfer = 500;
    int newAmountToTransfer = 10500;

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBefore = DashboardPage.getCurrentBalanceFirstCard();
        int balanceSecondCardBefore = DashboardPage.getCurrentBalanceSecondCard();
        var cardReplenishmentPage = dashboardPage.chooseSecondCardToRecharge();
        var cardInfo = DataHelper.getFirstCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 500);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceSecondCardBefore, amountToTransfer);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceFirstCardBefore, amountToTransfer);
        int balanceFirstCardAfter = DashboardPage.getCurrentBalanceFirstCard();
        int balanceSecondCardAfter = DashboardPage.getCurrentBalanceSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceSecondCardAfter);
        assertEquals(balanceAfterTransaction, balanceFirstCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBefore = DashboardPage.getCurrentBalanceFirstCard();
        int balanceSecondCardBefore = DashboardPage.getCurrentBalanceSecondCard();
        var cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
        var cardInfo = DataHelper.getSecondCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 500);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceFirstCardBefore, amountToTransfer);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceSecondCardBefore, amountToTransfer);
        int balanceFirstCardAfter = DashboardPage.getCurrentBalanceSecondCard();
        int balanceSecondCardAfter = DashboardPage.getCurrentBalanceFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceSecondCardAfter);
        assertEquals(balanceAfterTransaction, balanceFirstCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCardIfTransferIsGreaterThanBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBefore = DashboardPage.getCurrentBalanceFirstCard();
        int balanceSecondCardBefore = DashboardPage.getCurrentBalanceSecondCard();
        var cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
        var cardInfo = DataHelper.getSecondCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 10500);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceFirstCardBefore, newAmountToTransfer);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceSecondCardBefore, newAmountToTransfer);
        int balanceFirstCardAfter = DashboardPage.getCurrentBalanceSecondCard();
        int balanceSecondCardAfter = DashboardPage.getCurrentBalanceFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceSecondCardAfter);
        assertEquals(balanceAfterTransaction, balanceFirstCardAfter);
    }


}
