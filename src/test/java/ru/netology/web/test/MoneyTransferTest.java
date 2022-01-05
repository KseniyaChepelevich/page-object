package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardReplenishmentPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.DashboardPageNew;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


    int amountToTransfer = 500;

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
        cardReplenishmentPage.transferMany(cardInfo);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargeableCard(balanceFirstCardBefore, amountToTransfer);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereTransfer(balanceSecondCardBefore, amountToTransfer);
        int balanceFirstCardAfter = DashboardPage.getCurrentBalanceFirstCard();
        int balanceSecondCardAfter = DashboardPage.getCurrentBalanceSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceSecondCardAfter);
        assertEquals(balanceAfterTransaction, balanceFirstCardAfter);


    }


}
