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

//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV1() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV1();
////    var loginPage = open("http://localhost:9999", LoginPageV1.class);
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
//    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var balanceFirst = DashboardPage.getCardBalance(0);
        var balanceSecond = DashboardPage.getCardBalance(1);
        var rechargeableCard = DashboardPage.topUpBalance(1);
        var amountForTransfer = (CardReplenishmentPage.transferMany("500"));
        //var valueAmountForTransfer = Integer.parseInt(String.valueOf(amountForTransfer));

        var actualBalanceFirst = DashboardPage.getCardBalance(0);
        var actualBalanceSecond = DashboardPage.getCardBalance(1);

        int expectedBalanceSecond = 9500;

        int expectedBalanceFirst = 10500;





        assertEquals(expectedBalanceFirst, actualBalanceFirst);

    }

   // @Test

//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV3() {
//        var loginPage = open("http://localhost:9999", LoginPageV3.class);
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
//    }
}
