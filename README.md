### 문제

당신은 소규모 은행의 계좌 관리 시스템을 개발하는 개발자입니다.
아래 요구사항을 만족하는 시스템을 설계하고 구현하시오.

### 1단계 - 기본 입출금 시스템

```java
Account (추상 클래스)
├── SavingsAccount  → 입금만 가능
└── CheckingAccount → 입금 / 출금 / 이체 가능

예외 처리
├── InvalidAmountException     (0 이하 금액)
└── InsufficientBalanceException (잔액 부족)
```

### 요구사항

**1. Account (추상 클래스)**

- 계좌번호, 소유자, 잔액을 필드로 가진다
- `deposit()`, `withdraw()` 는 추상 메서드로 선언한다

**2. SavingsAccount / CheckingAccount**

- `SavingsAccount` 는 입금만 가능하다
- `CheckingAccount` 는 입금, 출금, 이체가 가능하다

**3. 예외 처리**

- `InvalidAmountException` : 0 이하 금액 입력 시
- `InsufficientBalanceException` : 잔액 부족 시
- 예외 발생 시 프로그램이 중단되지 않아야 한다