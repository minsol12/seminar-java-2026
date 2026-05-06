public class SavingsAccount implements SavingsAccountInterface {

    @Override
    public void deposit(double balance, double depositAmount) {
        balance += depositAmount;
    }



}