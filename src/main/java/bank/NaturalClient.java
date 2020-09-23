package bank;

import java.util.ArrayList;

public class NaturalClient implements Client {
    //имя клиента
    private String firstName;
    //фамилия клиента
    private String secondName;
    //паспорт
    private String id;
    //лист счетов
    private ArrayList<Account> accounts = new ArrayList<>();

    public NaturalClient(String firstName, String secondName, String id) {
        setFirstName(firstName);
        setSecondName(secondName);
        setId(id);
    }

    public NaturalClient(String firstName, String secondName, String id, ArrayList<Account> accounts) {
        setFirstName(firstName);
        setSecondName(secondName);
        setId(id);
        setAccounts(accounts);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //создайте метод, возвращающий массив всех счетов:
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов дебетовых карт;
    public ArrayList<Account> getDebitAccounts() {
        ArrayList<Account> result = new ArrayList<>();
        for (Account acc : this.accounts) {
            if (acc instanceof DebitAccount) {
                result.add(acc);
            }
        }
        return result;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт;
    public ArrayList<Account> getCreditAccounts() {
        ArrayList<Account> result = new ArrayList<>();
        for (Account acc : this.accounts) {
            if (acc instanceof CreditAccount) {
                result.add(acc);
            }
        }
        return result;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    //создайте метод. возвращающий ссылку на счет по его уникальному номеру;
    public Account getAccountById(String id) {
        Account result = null;
        for (Account acc : this.accounts) {
            if (acc.getId().equals(id)) {
                result = acc;
                break;
            }
        }
        return result;
    }

    //создайте метод, возвращающий суммарный остаток на всех счетах;
    public long getBalanceTotal() {
        long result = 0;
        for (Account acc : this.accounts) {
            result += acc.getBalance();
        }
        return result;
    }

    // метод, возвращающий сумму долга клиента
    public long getPaymentsTotal() {
        long result = 0;
        for (Account acc : this.accounts) {
            if (acc.getBalance() < 0) {
                result -= acc.getBalance();
            }
            if (acc instanceof CreditAccount){
                result += ((CreditAccount) acc).getFeePayments();
                result += ((CreditAccount) acc).getInterestPayments();
            }
        }
        return result;
    }

    //создайте метод, возвращающий массив счетов с положительным остатком на счете;
    public ArrayList<Account> getPositiveBalanceAccounts() {
        ArrayList<Account> result = new ArrayList<>();
        for (Account acc : this.accounts) {
            if (acc.getBalance() > 0) {
                result.add(acc);
            }
        }
        return result;
    }

    //создайте метод удаления счета по его номеру;
    public void deleteAccount(String id) {
        for (Account acc : this.accounts) {
            if (acc.getId().equals(id)) {
                this.accounts.remove(acc);
                break;
            }
        }
    }

    //создайте метод добавления счета (принимает в качестве входного параметра ссылку на счет,
    //                                     расширяет массив счетов путем добавления нового счета в конец массива)
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    //создайте метод увеличения размера остатка счета (принимает ссылку на счет и размер суммы);
    public void makeDeposit(Account account, long amount) {
        if (amount > 0) {
            int accIndex = this.accounts.indexOf(account);
            if (accIndex > -1) {
                this.accounts.get(accIndex).makeDeposit(amount);
            }
        }
    }

    //создайте метод уменьшения размера остатка счета (принимает ссылку на счет и размер суммы);
    public void makeWithdrawal(Account account, long amount) {
        if (amount > 0) {
            int accIndex = this.accounts.indexOf(account);
            if (accIndex > -1) {
                this.accounts.get(accIndex).makeWithdrawal(amount);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        result.append("private String firstName = ");
        result.append(this.firstName);
        result.append("\n");
        result.append("private String secondName = ");
        result.append(this.secondName);
        result.append("\n");
        result.append("private String id = ");
        result.append(this.id);
        result.append("\n");
        result.append("private ArrayList<Account> accounts: ");
        for(Account acc : this.accounts){
            result.append("\n");
            result.append(acc.getId());
        }

        return result.toString();
    }
}
