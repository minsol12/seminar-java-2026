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


### 다음 계획 - 완료
1. 지금까지 작업을 모두 컬렉션으로 바꾸기
   - List<ScheduleItem>
2. 입력이 잘못된 경우 화면에 출력하고 return 되도록 만들어져 있다면 exception으로 바꾸기.
   - 예외를 throw해서 올리고
   - Main에서 try-catch로 잡아 사용자에게 출력.
   - 단, readDate, readTime, readPriority처럼 재입력 받는 메서드는 그 안에서 잡아도 됨.
   - Exception 클래스 새로 만들기 (ScheduleException, ScheduleNotFoundException, ScheduleConflictException, ScheduleStorageException)
3. 파일 저장하는 기능 만들기 - db 아님
   - txt 파일
   - 일정 1개 = 파일 1줄

---
## 일정관리 프로그램 개선 - 사용자 추가
### 1. 사용자 도메인
- 프로그램에는 일정을 등록하고 관리하는 사용자 User가 존재
- 하나의 사용자는 여러 개의 일정을 가질 수 있음.
- 하나의 일정은 반드시 하나의 사용자에게 속해야 함.
- 일정 등록 시 사용자를 먼저 선택한 뒤 해당 사용자에게 일정을 등록해야 함.
- 존재하지 않는 사용자에게 일정을 등록할 수 없음.
- 일정 목록은 기존과 동일하게 하나의 목록에서 관리.
- 사용자별 일정을 조회하거나 검색할 때에도 일정 종류별 목록을 따로 만들지 않고, 하나의 일정 목록에서 사용자 정보를 기준으로 필터링 해야 함.

### 2. 사용자 구조
- User 클래스 - 사용자 정보 표현
  - 사용자의 공통 속성과 기능 포함
  - 일정의 소유자를 표현하는 도메인
- ScheduleItem은 해당 일정이 어떤 사용자에게 속하는지 식별 가능해야 함.
  - userId 속성 추가 - 사용자 식별 위해
- GeneralSchedule, MeetingSchedule, TaskSchedule, ReminderSchedule - ScheduleItem의 userId를 다시 작성하지 않고 사용할 수 있어야 함.

### 3. 사용자 속성
- id - 사용자 고유 번호 - 사용자 생성 시 자동 부여. 중복 불가
- name - 사용자 이름 - not null
- email - 사용자 이메일 - not null. 이메일 형식 따라야 함. 동일한 email을 가진 사용자는 중복 등록 불가
- createdAt - 사용자 생성 시각 - 사용자 생성 시 자동 기록
- updatedAt - 사용자 수정 시각 - 사용자 정보 수정 시 변경되어야 함.

---

- ScheduleItem에 userId(해당 일정을 소유한 사용자 고유 번호) 추가
- 일정 등록 시 입력된 userId에 해당하는 사용자 존재해야 함.
- 존재하지 않는 userId를 가진 일정은 등록 불가
- 일정 수정 시 일정의 소유 사용자를 변경하는 경우, 변경하려는 userId에 해당하는 사용자가 존재해야 함.

### 4. 사용자 목록 관리
- 등록된 모든 사용자를 하나의 목록에서 관리 해야 함.
- 사용자 목록은 User를 기준으로 관리.
- 사용자 목록과 일정 목록은 서로 분리하여 관리하되, 일정은 userId를 통해 사용자 참조해야 함.
- 사용자별 일정 조회, 사용자별 검색, 사용자별 정렬은 일정 목록에서 userId를 기준으로 피러링하여 처리.
- 사용자별로 일정 종류별 목록 따로 만들어 관리 X
- 사용자별로 별도의 일정 목록을 과도하게 중복 관리하지 않는다.

### 5. 사용자별 일정 관리
- 일정 등록 시 사용자 id를 먼저 입력받아야 함.
- 입력받은 사용자 id가 존재하는 경우에만 일정 등록을 진행.
- 등록된 일정은 해당 사용자의 일정으로 관리되어야 함.
- 일정 충돌 확인은 기본적으로 같은 사용자에게 속한 일정끼리 수행해야 함.

### 6. 회의 일정과 사용자 관계 확장
- MeetingSchedule은 회의 일정이므로 여러 사용자가 참여 가능해야 함.
- MeetingSchedule의 host는 회의 주최자
- 사용자 도메인이 추가된 이후 host는 존재하는 사용자 id를 기준으로 관리할 수 있어야 함.
- 사용자 도메인이 추가된 이후 participants는 존재하는 사용자 id 목록을 기준으로 관리할 수 있어야 함.
- 회의 일정 등록 시 host에 해당하는 사용자가 존재해야 함. participants에 포함된 사용자가 모두 존재해야 함.
- 존재하지 않는 사용자를 회의 주최자 또는 회의 참여자로 등록할 수 없음.
- 회의 일정 등록 또는 수정 시 회의 주최자와 참여자의 기존 일정이 겹치는지 확인해야 함.
- 회의 일정의 시간이 기존 일정과 충돌하는 경우 충돌된 사용자와 충돌된 일정 정보를 출력해야 함.

### 7. 사용자 삭제 정책
- 사용자 삭제 시 해당 사용자가 소유한 일정이 존재하는지 확인해야 함.
- 사용자를 삭제하려면 먼저 해당 사용자의 일정을 모두 삭제해야 함.
- 회의 일정의 host 또는 participants에 포함된 사용자는 삭제 불가
- 사용자 삭제가 불가능한 경우 삭제할 수 없는 이유를 안내 메시지로 출력해야 함.

### 8. 사용자 기능
- addUser
- displayAllUsers
- displayUserById
- updateUser
- deleteUser
- displaySchedulesByUserId
- searchByUserAndTitle
- searchByUserAndDate
- searchByUserAndPriority
- sortByUserAndDate
- sortByUserAndPriority
- sortByUserAndCompletion - 특정 사용자 일정 중 완료되지 않은 일정이 먼저 나오도록 정렬

---
## User, UserManager

- User 검증 규칙 정리
- UserManager에 사용자 목록 관리 기능 추가
- 그 다음에 ScheduleItem.userId 연결


---
## 2026.07.15까지
파일 저장까지 하기! - 사용자 정보, 일정 정보
- 일정만 저장, 사용자만 불러오기 등 어긋난 상태를 만들면 안됨.
- 항상 사용자와 일정을 같이 관리해야 일관성이 조을듯
- 메뉴에는 전체 저장/전체 불러오기만 두고
- 내부적으로 일정은 data/schedules.txt, 사용자는 data/users.txt 이렇게 각각 저장하기 - 각각 메소드 같이 호출하면 됨.
user는 user끼리, schedule은 schedule끼리, exception은 exception끼리 패키지 또 만들어서 묶기


---
- User는 id가 final이고 생성자에서 자동 증가만 됨.
- 파일에서 원래 id를 복원하기 어려움.
  - 파일에서 id=7인 사용자를 읽어도, 생성자를 쓰면 새 ID가 자동 발급되기 때문. 그러면 저장된 일정의 userId와 안 맞을 수 있음.
- 복원용 생성자 하나 더 만들기
  - 파일에서 읽은 id를 그대로 넣고, name, email도 세팅하고 this.id = id;

### 불러오기 흐름
- Main에서 case 실행
- userManager.loadFromFile("data/users.txt") 호출
- users.txt를 한 줄씩 읽음
- 각 줄의 사용자 정보를 보고 User 객체를 다시 만듦
  - 이때 기존 생성자를 이용하면 새 ID를 만들어 버림 (일정이 userId를 들고 있으므로 꼬임)
  - 복원용 생성자 - 파일에 있던 저장되어 있던 id 그대로 쓰기

---
### 현재 문제 (0714)
- 불러오기 시 파일에 완료 여부가 있었다 해도, 생성자에서 무조건 false로 시작함.
- 복원용 생성자에 isCompleted를 받을 수 있게 해야 함.