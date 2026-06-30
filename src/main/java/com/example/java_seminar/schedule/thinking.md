## 일정 관리 프로그램 요구사항

-----
### 클래스
- ScheduleItem (모든 일정이 공통으로 따름)
- GeneralSchedule - 일반 일정
- MeetingSchedule - 회의 일정
- TaskSchedule - 할일 일정
- ReminderSchedule - 알림 일정

### 필드
- id, title, description, startDate, endDate, startTime, endTime, priority, createdAt, updatedAt, isCompleted
- category, place, memo
- location, participants, agenda, host
- deadline, progress, taskStatus, assignedTo
- reminderTime, reminderMessage, notificationType, isReminderSent


### 추상 메서드
- displayInfo - 모든 일정은 자신의 정보를 출력하는 기능이 있어야됨.
  - 전체 일정 조회 시 프로그램은 목록에 들어 있는 각 일정의 displayInfo를 호출 (호출 방식은 모든 일정에 대해 동일)

- getScheduleType - 모든 일정은 자신의 일정 종류를 반환하는 기능을 가져야 함.
  - 일정 종류에 따라 각각 다른 값 반환

- markAsCompleted - 모든 일정은 완료 처리 기능을 가져야 함.
  - 일반 일정, 회의 일정, 알림 일정은 완료 처리 시 isCompleted를 true로 변경
  - 할 일 일정은 완료 처리 시 isCompleted를 true로 변경, progress를 100으로 변경, taskStatus를 DONE으로 변경

- notifyUser - 모든 일정은 알림 실행 기능을 가져야 함.
  - 알림 일정은 notify가 실행되면 reminderMessage 출력하고 isReminderSent를 true로 변경
  - 알림 일정이 아닌 일정에서 notifyUser가 실행되면 알림 대상이 아니라는 안내 메시지 출력

### 필수 기능
- addSchedule
- displayAllSchedules
- displayScheduleById
- updateSchedule
- deleteSchedule
- completeSchedule
- searchByTitle
- searchByDate
- searchByPriority
- sortByDate
- sortByPriority
- sortByCompletion
- checkConflict
- runNotification


---
### memo
- 시작일/종료일, 시작시간/종료시간 관계
- 생성 시 createdAt == updatedAt 되게 만들기. 수정 시 updatedAt만 바뀌게 만들기

- 날짜, 시간 검증 메서드 따로 만들기
  - 생성자, 수정 기능
  - ScheduleItem에 만들기
  - startDate, endDate, startTime, endTime
  - 일정 시간 범위 유효 검사
    1. 시작 날짜가 종료 날짜보다 늦으면 예외
    2. 시작 날짜와 종료 날짜가 같을 경우
       - 시작 시간이 종료 시간보다 늦으면 예외
       - 시작 시간과 종료 시간이 같으면 예외

- 수정 시 타입별 고유 필드도 수정 가능하게 만들기
  - 수정 대상 일정의 타입
  - 각각 일정에 맞는 입력 다시 받기
  - 수정할 일정 객체 먼저 찾고, 그 객체 타입에 따라 instanceof로 분기하기
    - findById(int id)로 수정할 객체 먼저 찾기
    - 없으면 메시지 출력 -> 종료
    - 있으면 공통 필드 수정
      - 그 다음 instanceof로 타입 판별
      - 타입별 고유 필드 입력받아서 setter 호출해서 수정


### 다음 계획
1. 지금까지 작업을 모두 컬렉션으로 바꾸기
   - List<ScheduleItem>
2. 입력이 잘못된 경우 화면에 출력하고 return 되도록 만들어져 있다면 exception으로 바꾸기.
   - 예외를 throw해서 올리고
   - Main에서 try-catch로 잡아 사용자에게 출력.
   - 단, readDate, readTime, readPriority처럼 재입력 받는 메서드는 그 안에서 잡아도 됨.
   - Exception 클래스 새로 만들기 (ScheduleException, ScheduleNotFoundException, ScheduleConflictException, ScheduleStorageException)
3. 파일 저장하는 기능 만들기 - db 아님