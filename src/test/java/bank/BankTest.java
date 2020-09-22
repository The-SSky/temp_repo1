package bank;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTest {

    private String expectedFirstName = "John";
    private String expectedSecondName = "Doe";
    private String expectedId = "44 44 123456";

    @Test
    public void account() {
        String expectedId = "000-000";
        String actualId = new DebitAccount(expectedId).getId();
        assertEquals(actualId, expectedId);

        long expectedBalance = 15677;
        long actualBalance = new DebitAccount(expectedId, expectedBalance).getBalance();
        assertEquals(actualBalance, expectedBalance);
    }

    @Test
    public void client() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        assertEquals(expectedFirstName, clientInstance.getFirstName());
        assertEquals(expectedSecondName, clientInstance.getSecondName());
        assertEquals(expectedId, clientInstance.getId());
        assertEquals(expectedAccounts, clientInstance.getAccounts());
    }

    @Test
    public void getAccountById() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        String expectedAccountId = "012544";
        DebitAccount expectedAccount = new DebitAccount(expectedAccountId);
        clientInstance.getAccounts().add(expectedAccount);
        assertEquals(expectedAccount, clientInstance.getAccountById(expectedAccountId));
    }

    @Test
    public void getBalanceTotal() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new DebitAccount("001", 200));
        expectedAccounts.add(new DebitAccount("002", 500));
        expectedAccounts.add(new DebitAccount("003", 500));
        expectedAccounts.add(new DebitAccount("004", -200));
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        assertEquals(1000, clientInstance.getBalanceTotal());

    }

    @Test
    public void getPositiveBalanceAccounts() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new DebitAccount("001", 200));
        expectedAccounts.add(new DebitAccount("002", 500));
        expectedAccounts.add(new DebitAccount("003", 0));
        expectedAccounts.add(new DebitAccount("004", -200));
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        int actualSize = clientInstance.getPositiveBalanceAccounts().size();
        assertEquals(2, actualSize);
    }

    @Test
    public void deleteAccount() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new DebitAccount("001", 200));
        expectedAccounts.add(new DebitAccount("002", 500));
        expectedAccounts.add(new DebitAccount("003", 0));
        expectedAccounts.add(new DebitAccount("004", -200));
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        clientInstance.deleteAccount("004");
        assertEquals(3, clientInstance.getAccounts().size());
    }

    @Test
    public void addAccount() {
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId);
        DebitAccount expectedAccount = new DebitAccount("456-765");
        clientInstance.addAccount(expectedAccount);
        int last = clientInstance.getAccounts().size() - 1;
        assertEquals(expectedAccount, clientInstance.getAccounts().get(last));
    }

    @Test
    public void makeDeposit() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new DebitAccount("001", 200));
        expectedAccounts.add(new DebitAccount("002", 500));
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        clientInstance.makeDeposit(clientInstance.getAccountById("002"), 500);
        assertEquals(1200, clientInstance.getBalanceTotal());
        clientInstance.makeDeposit(clientInstance.getAccountById("006"), 500);
        assertEquals(1200, clientInstance.getBalanceTotal());
    }

    @Test
    public void makeWithdrawal() {
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new DebitAccount("001", 200));
        expectedAccounts.add(new DebitAccount("002", 500));
        NaturalClient clientInstance = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        clientInstance.makeWithdrawal(clientInstance.getAccountById("002"), 400);
        assertEquals(300, clientInstance.getBalanceTotal());
        clientInstance.makeWithdrawal(clientInstance.getAccountById("006"), 500);
        assertEquals(300, clientInstance.getBalanceTotal());
    }

    @Test
    public void accountNumberGenerator(){
        assertEquals(0, AccountNumberGenerator.getCurrent());
        assertEquals(1, AccountNumberGenerator.getNext());
        AccountNumberGenerator.reset();
        assertEquals(1, AccountNumberGenerator.getNext());
        assertEquals("000002", AccountNumberGenerator.formatInteger(2));
    }

    @Test
    public void debitAccount(){
        assertEquals(Currency.RUB, new DebitAccount("id", 0, 0).getCurrency());
        assertEquals(0, new DebitAccount("id", 50, 50).getBalance());
        assertEquals(50, new DebitAccount("id", 0, 50, Currency.USD).getFee());
    }

    @Test
    public void accountBalance(){
        DebitAccount expectedAccount = new DebitAccount("id", 0, 0);
        expectedAccount.makeDeposit(100);
        assertEquals(100, expectedAccount.getBalance());
        expectedAccount.makeWithdrawal(50);
        assertEquals(50, expectedAccount.getBalance());
    }

    @Test
    public void creditAccount(){
        long expectedBalance = 1000;
        int expectedFee = 60;
        Currency expectedCurrency = Currency.JPY;
        int expectedInterestRate = 7;
        long expectedCardLimit = 100;
        CreditAccount testedCreditAccount = new CreditAccount(expectedId, expectedBalance,
                expectedFee, expectedCurrency, expectedInterestRate, expectedCardLimit);

        assertEquals(expectedId, testedCreditAccount.getId());
        assertEquals(expectedBalance, testedCreditAccount.getBalance());
        assertEquals(expectedFee, testedCreditAccount.getFee());
        assertEquals(expectedCurrency, testedCreditAccount.getCurrency());
        assertEquals(expectedInterestRate, testedCreditAccount.getInterestRate());
        assertEquals(expectedCardLimit, testedCreditAccount.getCardLimit());
    }

    @Test
    public void interestRate(){
        CreditAccount testedCreditAccount = new CreditAccount(expectedId);
        testedCreditAccount.setInterestRate(11);

        assertEquals(11, testedCreditAccount.getInterestRate());
    }

    @Test
    public void cardLimit(){
        CreditAccount testedCreditAccount = new CreditAccount(expectedId);
        testedCreditAccount.setCardLimit(200);

        assertEquals(200, testedCreditAccount.getCardLimit());
    }

    @Test
    public void processInterestPayment(){
        long expectedBalance = 1000;
        int expectedFee = 60;
        Currency expectedCurrency = Currency.JPY;
        int expectedInterestRate = 7;
        long expectedCardLimit = 20000;
        CreditAccount testedCreditAccount = new CreditAccount(expectedId, expectedBalance,
                expectedFee, expectedCurrency, expectedInterestRate, expectedCardLimit);

        testedCreditAccount.processInterestPayment();
        assertTrue(testedCreditAccount.getInterestPayments() > 0);
    }

    @Test
    public void creditCardFee(){
        long expectedBalance = 1000;
        int expectedFee = 60;
        Currency expectedCurrency = Currency.JPY;
        int expectedInterestRate = 7;
        long expectedCardLimit = 20000;
        CreditAccount testedCreditAccount = new CreditAccount(expectedId, expectedBalance,
                expectedFee, expectedCurrency, expectedInterestRate, expectedCardLimit);

        testedCreditAccount.takeFee();
        testedCreditAccount.takeFee();
        assertEquals(120, testedCreditAccount.getFeePayments());
    }

    @Test
    public void creditCardMakeDeposit(){
        long expectedBalance = -1000;
        int expectedFee = 60;
        Currency expectedCurrency = Currency.JPY;
        int expectedInterestRate = 7;
        long expectedCardLimit = 20000;
        CreditAccount testedCreditAccount = new CreditAccount(expectedId, expectedBalance,
                expectedFee, expectedCurrency, expectedInterestRate, expectedCardLimit);


        testedCreditAccount.makeDeposit(1000);
        assertEquals(0, testedCreditAccount.getBalance());
    }

    @Test
    public void getDebitCreditAccounts(){
        ArrayList<Account> expectedList = new ArrayList<>();
        expectedList.add(new DebitAccount("000"));
        expectedList.add(new DebitAccount("001"));
        expectedList.add(new DebitAccount("002"));
        expectedList.add(new CreditAccount("003"));
        expectedList.add(new CreditAccount("004"));

        NaturalClient testedClient = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedList);

        assertEquals(3, testedClient.getDebitAccounts().size());
        assertEquals(2, testedClient.getCreditAccounts().size());
    }

    @Test
    public void getPaymentsTotal(){
        ArrayList<Account> expectedList = new ArrayList<>();
        expectedList.add(new DebitAccount("000", -100));
        expectedList.add(new DebitAccount("001", 1000));
        expectedList.add(new DebitAccount("002", -1500));
        expectedList.add(new CreditAccount("003", 100));
        expectedList.add(new CreditAccount("004", -500));

        NaturalClient testedClient = new NaturalClient(expectedFirstName, expectedSecondName,
                expectedId, expectedList);

        assertEquals(2100, testedClient.getPaymentsTotal());
        assertEquals(2, testedClient.getPositiveBalanceAccounts().size());
    }
}