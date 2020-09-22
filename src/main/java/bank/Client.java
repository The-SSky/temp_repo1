package bank;

import java.util.ArrayList;

public interface Client {
    // метод, возвращающий ссылку на счет по его уникальному номеру;
    Account getAccountById(String id);

    // метод, возвращающий список (класс ArrayList<Account>) всех счетов;
    ArrayList<Account> getAccounts();

    // метод, возвращающий список (класс ArrayList<Account>) счетов дебетовых карт;
    ArrayList<Account> getDebitAccounts();

    // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт;
    ArrayList<Account> getCreditAccounts();

    // метод, возвращающий суммарный остаток на всех дебетовых счетах;
    long getBalanceTotal();

    // метод, возвращающий сумму долга клиента (сумма начисленных процентов и комиссионных по
    //всем кредитным счетам, а также отрицательный остаток по картам)
    long getPaymentsTotal();

    // метод, возвращающий список (ArrayList<Account>) счетов с положительным остатком на счете
    ArrayList<Account> getPositiveBalanceAccounts();

    // метод удаления счета по его номеру;
    void deleteAccount(String id);

    // метод добавления счета (принимает в качестве входного параметра ссылку на счет)
    void addAccount(Account account);

    // метод списывания средств со счета (принимает номер счета и размер суммы):
    void makeWithdrawal(Account account, long amount);

    // метод пополнения счета (принимает номер счета и размер суммы).
    void makeDeposit(Account account, long amount);
}
