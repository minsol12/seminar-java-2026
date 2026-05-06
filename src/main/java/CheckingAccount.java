public class CheckingAccount implements CheckingAccountInterface {

    @Override
    public void deposit(double balance, double depositAmount) {
        balance += depositAmount;
    }

    @Override
    public void withdraw(double balance, double withdrawAmount) {

        if(balance >= withdrawAmount) {
            balance -= withdrawAmount;
        } else {
            System.out.println("Insufficient balance");
        }
    }
}