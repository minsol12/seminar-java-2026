package com.example.java_seminar.account;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 적금 계좌
        SavingsAccount savingsAccount = new SavingsAccount();

        // 입출금 계좌
        CheckingAccount checkingAccount = new CheckingAccount();

        while (true) {
            try {
                System.out.println();
                System.out.println("1. 입출금 계좌, 2. 적금 계좌, 3. 종료");
                System.out.print("번호를 입력해 주세요: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("1. 입금, 2. 출금, 3. 이체, 4. 조회, 5. 종료");
                        System.out.print("번호를 입력해 주세요: ");
                        int number = scanner.nextInt();

                        long amount;
                        String transferName;

                        switch (number) {

                            case 1:
                                System.out.print("입금할 금액을 입력하세요: ");
                                amount = scanner.nextLong();
                                checkingAccount.deposit(amount);
                                break;

                            case 2:
                                System.out.print("출금할 금액을 입력하세요: ");
                                amount = scanner.nextLong();
                                checkingAccount.withdraw(amount);
                                break;

                            case 3:
                                System.out.print("이체할 금액을 입력하세요: ");
                                amount = scanner.nextLong();

                                scanner.nextLine();

                                System.out.print("이체받을 사람의 성함을 입력하세요: ");
                                transferName = scanner.nextLine();

                                checkingAccount.transfer(amount, transferName);
                                break;

                            case 4:
                                checkingAccount.inquiry();
                                break;

                            case 5:
                                System.out.println("프로그램을 종료합니다.");
                                return;

                            default:
                                System.out.println("잘못된 번호입니다.");

                        }
                        break;

                    case 2:
                        System.out.println("1. 입금, 2. 조회, 3. 종료");
                        System.out.print("번호를 입력해 주세요: ");
                        int num = scanner.nextInt();

                        switch (num) {
                            case 1:
                                System.out.print("입금할 금액을 입력하세요: ");
                                amount = scanner.nextLong();
                                savingsAccount.deposit(amount);
                                break;

                            case 2:
                                savingsAccount.inquiry();
                                break;

                            case 3:
                                System.out.println("프로그램을 종료합니다.");
                                return;

                            default:
                                System.out.println("잘못된 번호입니다.");
                        }
                        break;

                    case 3:
                        System.out.println("프로그램을 종료합니다.");
                        return;

                    default:
                        System.out.println("잘못된 번호입니다.");
                }
                //break;
            } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해 주세요.");
                scanner.nextLine();
            }
        }
    }
}