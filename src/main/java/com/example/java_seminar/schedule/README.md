# Schedule Project

여러 종류의 일정을 관리할 수 있도록 만든 콘솔 기반 일정 관리 프로젝트입니다.  
단순 CRUD를 넘어서 일정 유형 분리, 검색과 정렬, 파일 저장, 사용자 정의 예외 처리까지 포함해 조금 더 넓은 범위의 자바 실습을 목표로 했습니다.

## What I Practiced

- 추상 클래스를 중심으로 한 일정 모델링
- 일정 타입별 세부 정보 확장
- 검색, 정렬, 완료 처리 같은 상태 관리 로직 구성
- 파일 저장 및 불러오기 처리
- 도메인 예외를 구분한 예외 설계

## Structure

- `ScheduleItem`
  - 일정의 공통 속성과 우선순위 관리
- `GeneralSchedule`
  - 일반 일정
- `MeetingSchedule`
  - 회의 일정
- `TaskSchedule`
  - 작업 일정
- `ReminderSchedule`
  - 알림 일정
- `ScheduleManager`
  - 일정 등록, 조회, 수정, 삭제, 검색, 정렬, 저장 담당
- `User`, `UserManager`
  - 사용자 정보 및 사용자 검증 처리
- `Main`
  - 콘솔 메뉴 기반 실행 진입점

## Key Features

- 일정 등록, 전체 조회, 상세 조회, 수정, 삭제, 완료 처리
- 제목, 날짜, 우선순위 기준 검색
- 날짜순, 우선순위순, 완료 여부 기준 정렬
- 파일 저장 및 불러오기
- `ScheduleException` 계열 사용자 정의 예외 분리

## Path

`src/main/java/com/example/java_seminar/schedule`