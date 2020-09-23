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

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DebitAccount) {
            if(this == obj) return true;
            if(this.getId().equals(((DebitAccount) obj).getId())
                    && this.getBalance() == ((DebitAccount) obj).getBalance()
                    && this.getFee() == ((DebitAccount) obj).getFee()
                    && this.getCurrency().equals(((DebitAccount) obj).getCurrency())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
       int hash = 100103 ^ (this.getId().hashCode() + Long.hashCode(this.getBalance())
                + this.getFee() + this.getCurrency().hashCode());
        return hash;
    }
}
