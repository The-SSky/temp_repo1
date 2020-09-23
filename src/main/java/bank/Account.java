package bank;

public abstract class Account {
    //номер счета банка
    private String id = null;

    //остаток счета банка
    private long balance;

    //приватное поле, содержащее комиссию за обслуживание.
    private int fee = 0;

    //приватное поле, содержащее валюту счета
    private Currency currency = null;

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

    //DELETED
    //public void setBalance(long balance) {
    //    this.balance = balance;
    //}

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("private String id = %s", this.id));
        result.append("\n");
        result.append(String.format("private long balance = %d", this.balance));
        result.append("\n");
        result.append(String.format("private int fee = %d", this.fee));
        result.append("\n");
        result.append(String.format("private Currency currency = %s", this.currency));

        return result.toString();
    }
}
