package com.example.java_seminar.account;

public class CheckingAccount extends Account {

    @Override
    public void printResult(long amount) {
        System.out.println("[입출금 입금] " + amount + "원 입금 → 잔액: " + this.balance + "원");
    }

    public void withdraw(long amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("0보다 큰 금액을 입력해주세요.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        this.balance -= amount;
        System.out.println("[입출금 출금] " + amount + "원 출금 → 잔액: " + this.balance + "원");
    }

    public void transfer(long amount, String transferName) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0보다 큰 금액을 입력해주세요.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        this.balance -= amount;
        System.out.println("[입출금 이체] " + transferName + "님에게 " + amount + "원 이체 → 잔액: " + this.balance + "원");
    }

    @Override
    public void inquiry() {
        System.out.println("[입출금 잔액] " + this.balance + "원");
    }
}