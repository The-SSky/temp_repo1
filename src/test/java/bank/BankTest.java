package bank;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BankTest {

    private String expectedFirstName = "John";
    private String expectedSecondName = "Doe";
    private String expectedId = "44 44 123456";

    @Test
    public void account() {
        String expectedId = "000-000";
        String actualId = new Account(expectedId).getId();
        assertEquals(actualId, expectedId);

        long expectedBalance = 15677;
        long actualBalance = new Account(expectedId, expectedBalance).getBalance();
        assertEquals(actualBalance, expectedBalance);
    }

    @Test
    public void client(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        assertEquals(expectedFirstName, clientInstance.getFirstName());
        assertEquals(expectedSecondName, clientInstance.getSecondName());
        assertEquals(expectedId, clientInstance.getId());
        assertEquals(expectedAccounts, clientInstance.getAccounts());
    }

    @Test
    public void getAccountById(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        String expectedAccountId = "012544";
        Account expectedAccount = new Account(expectedAccountId);
        clientInstance.getAccounts().add(expectedAccount);
        assertEquals(expectedAccount, clientInstance.getAccountById(expectedAccountId));
    }

    @Test
    public void getBalanceTotal(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new Account("001", 200));
        expectedAccounts.add(new Account("002", 500));
        expectedAccounts.add(new Account("003", 500));
        expectedAccounts.add(new Account("004", -200));
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        assertEquals(1000, clientInstance.getBalanceTotal());

    }

    @Test
    public void getPositiveBalanceAccounts(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new Account("001", 200));
        expectedAccounts.add(new Account("002", 500));
        expectedAccounts.add(new Account("003", 0));
        expectedAccounts.add(new Account("004", -200));
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        int actualSize = clientInstance.getPositiveBalanceAccounts().size();
        assertEquals(2, actualSize);
    }

    @Test
    public void deleteAccount(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new Account("001", 200));
        expectedAccounts.add(new Account("002", 500));
        expectedAccounts.add(new Account("003", 0));
        expectedAccounts.add(new Account("004", -200));
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        clientInstance.deleteAccount("004");
        assertEquals(3, clientInstance.getAccounts().size());
    }

    @Test
    public void addAccount(){
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId);
        Account expectedAccount = new Account("456-765");
        clientInstance.addAccount(expectedAccount);
        int last = clientInstance.getAccounts().size() - 1;
        assertEquals(expectedAccount, clientInstance.getAccounts().get(last));
    }

    @Test
    public void makeDeposit(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new Account("001", 200));
        expectedAccounts.add(new Account("002", 500));
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        clientInstance.makeDeposit(clientInstance.getAccountById("002"), 500);
        assertEquals(1200, clientInstance.getBalanceTotal());
        clientInstance.makeDeposit(clientInstance.getAccountById("006"), 500);
        assertEquals(1200, clientInstance.getBalanceTotal());
    }

    @Test
    public void makeWithdrawal(){
        ArrayList<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new Account("001", 200));
        expectedAccounts.add(new Account("002", 500));
        Client clientInstance = new Client(expectedFirstName, expectedSecondName,
                expectedId, expectedAccounts);
        clientInstance.makeWithdrawal(clientInstance.getAccountById("002"), 400);
        assertEquals(300, clientInstance.getBalanceTotal());
        clientInstance.makeWithdrawal(clientInstance.getAccountById("006"), 500);
        assertEquals(300, clientInstance.getBalanceTotal());
    }
}