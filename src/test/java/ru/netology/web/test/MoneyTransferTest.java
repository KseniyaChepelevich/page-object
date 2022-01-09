package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
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
    void setup() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV2();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

    }

    @AfterEach
    void  asserting() {
        val dashboardPage = new DashboardPage();
        int a = DashboardPage.getFirstCardBalance();
        int b = DashboardPage.getSecondCardBalance();
        if (a - b > 0) {
            int amountToTransf = (a - b) / 2;
            val cardInfo = DataHelper.getFirstCardInfo();
            val cardReplenishmentPage = dashboardPage.chooseSecondCardToRecharge();
            cardReplenishmentPage.transferMany(cardInfo, amountToTransf);


        }
        if (b - a > 0) {
            int amountToTransf = (b - a) / 2;
            val cardInfo = DataHelper.getSecondCardInfo();
            val cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
            cardReplenishmentPage.transferMany(cardInfo, amountToTransf);

        }

    }



    int amountToTransfer = 500;
    int newAmountToTransfer = 10500;

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        val dashboardPage = new DashboardPage();
        int balanceFirstCardBefore = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBefore = DashboardPage.getSecondCardBalance();
        val cardReplenishmentPage = dashboardPage.chooseSecondCardToRecharge();
        val cardInfo = DataHelper.getFirstCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 500);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceSecondCardBefore, amountToTransfer);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceFirstCardBefore, amountToTransfer);
        int balanceFirstCardAfter = DashboardPage.getFirstCardBalance();
        int balanceSecondCardAfter = DashboardPage.getSecondCardBalance();
        assertEquals(balanceAfterTransactionOnRecharged, balanceSecondCardAfter);
        assertEquals(balanceAfterTransaction, balanceFirstCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        val dashboardPage = new DashboardPage();
        int balanceFirstCardBefore = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBefore = DashboardPage.getSecondCardBalance();
        val cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 500);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceFirstCardBefore, amountToTransfer);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceSecondCardBefore, amountToTransfer);
        int balanceFirstCardAfter = DashboardPage.getFirstCardBalance();
        int balanceSecondCardAfter = DashboardPage.getSecondCardBalance();
        assertEquals(balanceAfterTransactionOnRecharged, balanceFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceSecondCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCardIfTransferIsGreaterThanBalance() {
        val dashboardPage = new DashboardPage();
        int balanceFirstCardBefore = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBefore = DashboardPage.getSecondCardBalance();
        val cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 10500);
        val dashboardPageWithError = new DashboardPage();
        dashboardPageWithError.getNotificationVisible();

    }


}
