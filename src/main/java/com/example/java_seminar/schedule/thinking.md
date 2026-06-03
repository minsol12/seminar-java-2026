## 일정 관리 프로그램 요구사항


-----
클래스
ScheduleItem (모든 일정이 공통으로 따름)
GeneralSchedule - 일반 일정
MeetingSchedule - 회의 일정
TaskSchedule - 할일 일정
ReminderSchedule - 알림 일정

필드
id, title, description, startDate, endDate, startTime, endTime, priority, createdAt, updatedAt, isCompleted
category, place, memo
location, participants, agenda, host
daedline, progress, taskStatus, assignedTo
reminderTime, reminderMessage, notificationType, isReminderSent


추상 메서드
displayInfo - 모든 일정은 자신의 정보를 출력하는 기능이 있어야됨.
- 전체 일정 조회 시 프로그램은 목록에 들어 있는 각 일정의 displayInfo를 호출 (호출 방식은 모든 일정에 대해 동일)

getScheduleType - 모든 일정은 자신의 일정 종류를 반환하는 기능을 가져야 함.
- 일정 종류에 따라 각각 다른 값 반환

markAsCompleted - 모든 일정은 완료 처리 기능을 가져야 함.
- 일반 일정, 회의 일정, 알림 일정은 완료 처리 시 isCompleted를 true로 변경
- 할 일 일정은 완료 처리 시 isCompleted를 true로 변경, progress를 100으로 변경, taskStatus를 DONE으로 변경

notifyUser - 모든 일정은 알림 실행 기능을 가져야 함.
- 알림 일정은 notify가 실행되면 reminderMessage 출력하고 isReminderSent를 true로 변경
- 알림 일정이 아닌 일정에서 notifyUser가 실행되면 알림 대상이 아니라는 안내 메시지 출력

필수 기능
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



ScheduleItem - 필수 기능
GeneralSchedule - 
MeetingSchedule - 
TaskSchedule - 
ReminderSchedule -