package bank;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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


}