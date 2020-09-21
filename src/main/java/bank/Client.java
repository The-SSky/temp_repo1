package bank;

import java.util.ArrayList;

public class Client {
    //имя клиента
    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //фамилия клиента
    private String secondName;
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    //паспорт
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //лист счетов
    private ArrayList<Account> accounts = new ArrayList<>();
    //создайте метод, возвращающий массив всех счетов:
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    //создайте метод. возвращающий ссылку на счет по его уникальному номеру;
    public Account getAccountById(String id){
        Account result = null;
        for (Account acc: this.accounts) {
            if(acc.getId().equals(id)){
                result = acc;
                break;
            }
        }
        return result;
    }

    //создайте метод, возвращающий суммарный остаток на всех счетах;
    public long getBalanceTotal(){
        long result = 0;
        for (Account acc: this.accounts){
            result += acc.getBalance();
        }
        return result;
    }

    //создайте метод, возвращающий массив счетов с положительным остатком на счете;
    public ArrayList<Account> getPositiveBalanceAccounts(){
        ArrayList<Account> result = new ArrayList<>();
        for (Account acc: this.accounts) {
            if (acc.getBalance() > 0){
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
    public void addAccount(Account account){
        this.accounts.add(account);
    }

    //создайте метод увеличения размера остатка счета (принимает ссылку на счет и размер суммы);
    public void makeDeposit(Account account, long amount){
        if(amount > 0){
            int accIndex = this.accounts.indexOf(account);
            if (accIndex > -1){
                long current = this.accounts.get(accIndex).getBalance();
                this.accounts.get(accIndex).setBalance(current + amount);
            }
        }
    }
    //создайте метод уменьшения размера остатка счета (принимает ссылку на счет и размер суммы);
    public void makeWithdrawal(Account account, long amount){
        if(amount > 0){
            int accIndex = this.accounts.indexOf(account);
            if (accIndex > -1){
                long current = this.accounts.get(accIndex).getBalance();
                this.accounts.get(accIndex).setBalance(current - amount);
            }
        }
    }

    public Client(String firstName, String secondName, String id){
    setFirstName(firstName);
    setSecondName(secondName);
    setId(id);
    }
    public Client(String firstName, String secondName, String id, ArrayList<Account> accounts){
    setFirstName(firstName);
    setSecondName(secondName);
    setId(id);
    setAccounts(accounts);
    }
}
