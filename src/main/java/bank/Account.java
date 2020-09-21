package bank;

public class Account {
    //номер счета банка
    private String id;
    //остаток счета банка
    private long balance;

    public Account(String id, long balance) {
        setId(id);
        setBalance(balance);
    }

    public Account(String id) {
        setId(id);
        setBalance(0);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
