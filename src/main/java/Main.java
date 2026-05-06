import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            Account ac = new Account();
            int select = scanner.nextInt();

            System.out.println("------------");
            System.out.println("입금: 1, 출금: 2, 이체: 3, 잔액: 4을 입력하세요: " + select);

            switch (select) {
                case 1 -> {
                    int depositAmount = scanner.nextInt();
                    SavingsAccount savingsAccount = new SavingsAccount();
                    savingsAccount.deposit(ac.getBalance(), depositAmount);
                }

                case 2 -> {
                    int withdrawAmount = scanner.nextInt();
                    CheckingAccount checkingAccount = new CheckingAccount();
                    checkingAccount.withdraw(ac.getBalance(), withdrawAmount);
                }

                case 3 -> {

                }

                case 4 -> {

                }
            }



            Account SA = new SavingsAccount();
            SA.deposit();
        }
    }
}
