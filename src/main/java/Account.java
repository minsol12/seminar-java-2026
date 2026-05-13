public abstract class Account {
    // 필드
    protected long balance;

    // 생성자
    protected Account() {
        this.balance = 0;
    }

    // 메서드
    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0보다 큰 금액을 입력해주세요.");
        }

        this.balance += amount;

        printResult(amount);
    }

    public void printResult(long amount) {
        System.out.println("[입금] " + amount + "원 입금 → 잔액: " + this.balance);
    }

    public abstract void inquiry();
}