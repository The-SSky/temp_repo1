package bank;

import java.util.Calendar;
import java.util.Date;

public class CreditAccount extends Account{

    // приватное поле — процентная ставка (годовая, в процентах)
    private int interestRate = 0;

    // приватное поле лимит по кредитной карте
    private long cardLimit = 0;

    // приватное поле — начисленные проценты
    private long interestPayments = 0;

    // приватное поле — начисленные комисснонные
    private long feePayments = 0;

    CreditAccount(String id){
        super(id);
    }
    CreditAccount(String id, long balance){
        super(id, balance);
    }
    CreditAccount(String id, long balance, int fee){
        super(id, balance, fee);
    }
    CreditAccount(String id, long balance, int fee, Currency currency){
        super(id, balance, fee, currency);
    }
    CreditAccount(String id, long balance, int fee, Currency currency, int interestRate, long cardLimit){
        super(id, balance, fee, currency);
        this.interestRate = interestRate;
        this.cardLimit = cardLimit;
    }

    //тетеры и сеттеры процентной ставки и лимита по карте.
    public int getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(int interestRate){
        this.interestRate = interestRate;
    }

    public long getCardLimit() {
        return cardLimit;
    }
    public void setCardLimit(long cardLimit){
        this.cardLimit = cardLimit;
    }

    // геттеры для начисленных процентов и комисснонных за обсл
    public long getFeePayments() {
        return feePayments;
    }
    public long getInterestPayments() {
        return interestPayments;
    }

    //метод начисления процентов
    public void processInterestPayment(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        if (this.getBalance() < this.getCardLimit()){
            this.interestPayments = (this.getCardLimit() - this.getBalance()) * (this.getInterestRate() /
                    numberOfDays) / 100;
        }
    }

    //Переопределить: метод, вычитающий комиссию из остатка
    @Override
    public void takeFee() {
        this.feePayments += this.getFee();
    }

    //Переопределить: метод пополнения счета
    @Override
    public void makeDeposit(long amount) {
        //средства сначала идут на погашения начисленных комиссионных
        if(amount >= this.feePayments){
            amount -= this.feePayments;
            this.feePayments = 0;
        }
        else {
            this.feePayments -= amount;
            return;
        }
        // затем начисленных процентов,
        if(amount >= this.interestPayments){
            amount -= this.interestPayments;
            this.interestPayments = 0;
        }
        else {
            this.interestPayments -= amount;
            return;
        }
        // затем на пополнение остатка.
        super.makeDeposit(amount);
    }
}
