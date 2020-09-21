package bank;

public abstract class Account {
    //номер счета банка
    private String id;

    //остаток счета банка
    private long balance;

    //приватное поле, содержащее комиссию за обслуживание.
    private int fee = 50;

    //приватное поле, содержащее валюту счета
    private Currency currency;

    public Account(String id, long balance, int fee){
        setId(id);
        this.balance = balance;
        setFee(fee);
        setCurrency(Currency.RUB);
    }

    public Account(String id, long balance, int fee, Currency currency){
        setId(id);
        this.balance = balance;
        setFee(fee);
        setCurrency(currency);
    }

    public Account(String id, long balance) {
        setId(id);
        this.balance = balance;
    }

    public Account(String id) {
        setId(id);
        this.balance = 0;
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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Currency getCurrency() {
        return currency;
    }

    //сеттер, помимо установки валюты, пересчитывает комиссию и остаток на счете
    public void setCurrency(Currency currency) {
        this.currency = currency;
        takeFee();
    }

    //метод, вычитающий комиссию из остатка
    public void takeFee(){
        if (this.fee > 0){
            this.balance -= this.fee;
        }
    }

    //метод пополнения счета
    public void makeDeposit(long amount){
        this.balance += amount;
    }

    //метод списывания суммы со счета
    public void makeWithdrawal(long amount){
        this.balance -= amount;
    }
}
