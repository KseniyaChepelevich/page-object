package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardReplenishmentPage;
import ru.netology.web.page.DashboardPage;
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
        val balance1 = dashboardPage.getFirstCardBalance();
        val balance2 = dashboardPage.getSecondCardBalance();
        if (balance1 - balance2 > 0) {
            val amountToTransf = (balance1 - balance2)  / 2;
            val cardInfo = DataHelper.getFirstCardInfo();
            val cardReplenishmentPage = dashboardPage.chooseSecondCardToRecharge();
            cardReplenishmentPage.transferMany(cardInfo, amountToTransf);


        }
        if (balance2 - balance1 > 0) {
            val amountToTransf = (balance2 - balance1) / 2;
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
        val balanceFirstCardBefore = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBefore = dashboardPage.getSecondCardBalance();
        val cardReplenishmentPage = dashboardPage.chooseSecondCardToRecharge();
        val cardInfo = DataHelper.getFirstCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 500);
        val balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceSecondCardBefore, amountToTransfer);
        val balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceFirstCardBefore, amountToTransfer);
        val balanceFirstCardAfter = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfter = dashboardPage.getSecondCardBalance();
        assertEquals(balanceAfterTransactionOnRecharged, balanceSecondCardAfter);
        assertEquals(balanceAfterTransaction, balanceFirstCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        val dashboardPage = new DashboardPage();
        val balanceFirstCardBefore = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBefore = dashboardPage.getSecondCardBalance();
        val cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, 500);
        val balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceFirstCardBefore, amountToTransfer);
        val balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceSecondCardBefore, amountToTransfer);
        val balanceFirstCardAfter = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfter = dashboardPage.getSecondCardBalance();
        assertEquals(balanceAfterTransactionOnRecharged, balanceFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceSecondCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCardIfTransferIsGreaterThanBalance() {
        val dashboardPage = new DashboardPage();
        val balanceFirstCardBefore = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBefore = dashboardPage.getSecondCardBalance();
        val cardReplenishmentPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInfo();
        cardReplenishmentPage.transferMany(cardInfo, newAmountToTransfer);
        val dashboardPageWithError = new DashboardPage();
        dashboardPageWithError.getNotificationVisible();

    }


}
