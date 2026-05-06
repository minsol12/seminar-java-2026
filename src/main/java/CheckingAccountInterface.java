public interface CheckingAccountInterface {
    abstract void deposit(double balance, double depositAmount);
    abstract void withdraw(double balance, double withdrawAmount);
}
