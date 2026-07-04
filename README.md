# Java Seminar

자바를 공부하면서 작은 주제를 직접 세부 설계하고 구현해 보는 개인 실습 저장소입니다.  
문법 정리에 그치지 않고, 객체지향 구조, 예외 처리, 콘솔 입력 흐름, 파일 저장 같은 기본기를 코드로 쌓는 데 초점을 두고 있습니다.

## Overview

이 저장소는 주제별로 작은 프로그램을 하나씩 만들면서 자바 핵심 개념을 반복 연습하는 프로젝트입니다.

- 객체지향 설계
- 상속과 다형성
- 예외 처리
- 컬렉션과 데이터 관리
- 파일 입출력
- 콘솔 기반 사용자 인터페이스

## Projects

### Account

계좌 유형에 따라 가능한 동작이 달라지는 구조를 구현한 간단한 계좌 관리 프로그램입니다.

- `Account` 추상 클래스를 기반으로 계좌 공통 동작 분리
- `SavingsAccount`, `CheckingAccount`로 기능 차이 구현
- 입금, 출금, 이체, 잔액 조회 기능 처리
- 잘못된 금액 입력과 잔액 부족 상황에 대한 예외 처리

상세 보기: [account README](/abs/C:/Users/moonl/development/java_seminar/src/main/java/com/example/java_seminar/account/README.md)

### Schedule

여러 종류의 일정을 등록하고 검색, 수정, 정렬, 저장할 수 있는 콘솔 일정 관리 프로그램입니다.

- 일정 공통 모델과 유형별 일정 클래스 분리
- 등록, 조회, 수정, 삭제, 완료 처리 기능 구현
- 제목, 날짜, 우선순위 기준 검색 지원
- 날짜순, 우선순위순, 완료 여부 기준 정렬 지원
- 파일 저장 및 불러오기 기능 구현
- 사용자 정의 예외로 일정 관련 오류 분리

상세 보기: [schedule README](/abs/C:/Users/moonl/development/java_seminar/src/main/java/com/example/java_seminar/schedule/README.md)

## Run

각 주제는 별도의 `Main` 클래스로 실행할 수 있습니다.

- `com.example.java_seminar.account.Main`
- `com.example.java_seminar.schedule.Main`

## Goal

작게라도 직접 설계하고 구현하는 과정을 반복하면서, 자바 기본기를 코드 수준에서 정리하는 것이 이 프로젝트의 목적입니다.