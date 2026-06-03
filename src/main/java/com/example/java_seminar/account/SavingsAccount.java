package com.example.java_seminar.account;

public class SavingsAccount extends Account {
    // 입금, 잔액 조회 기능
    @Override
    public void printResult(long amount) {
        System.out.println("[적금 입금] " + amount + "원 입금 → 잔액: " + this.balance);
    }

    @Override
    public void inquiry() {
        System.out.println("[적금 잔액] " + this.balance + "원");
    }
}