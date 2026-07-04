# Account Project

계좌 기능을 객체지향적으로 나누어 구현한 콘솔 기반 실습 프로젝트입니다.
계좌 타입별로 허용되는 동작이 다르다는 점을 상속 구조로 표현하고, 예외 상황을 프로그램 흐름 안에서 처리하는 연습에 초점을 두었습니다.

## What I Practiced

- 추상 클래스를 이용한 공통 기능 분리
- 하위 클래스별 기능 제한과 확장
- 금액 검증 및 잔액 검증
- 예외 발생 시 프로그램이 종료되지 않도록 입력 흐름 제어

## Structure

- `Account`
  - 공통 필드 `balance`와 입금 로직 정의
- `SavingsAccount`
  - 입금, 잔액 조회 기능 제공
- `CheckingAccount`
  - 입금, 출금, 이체, 잔액 조회 기능 제공
- `Main`
  - 콘솔 메뉴 기반 실행 진입점

## Key Points

- 적금 계좌와 입출금 계좌의 기능 차이를 클래스로 분리했습니다.
- 잘못된 금액 입력이나 잔액 부족 시 `IllegalArgumentException`으로 처리합니다.
- `try-catch`를 사용해 예외가 발생해도 프로그램이 바로 종료되지 않도록 구성했습니다.

## Path

`src/main/java/com/example/java_seminar/account`