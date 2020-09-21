package bank;

public class DebitAccount extends Account{
    DebitAccount(String id){
        super(id);
    }
    DebitAccount(String id, long balance){
        super(id, balance);
    }
    DebitAccount(String id, long balance, int fee){
        super(id, balance, fee);
    }
    DebitAccount(String id, long balance, int fee, Currency currency){
        super(id, balance, fee, currency);
    }
}
