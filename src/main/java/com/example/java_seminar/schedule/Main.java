package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

// 마감 시간이 시작 시간보다 빠를 때 예외 처리가 입력이 다 끝나고 나오게 됨. 입력 즉시 판단해서 예외처리 되도록 수정하기

public class Main {
    private static final String SAVE_FILE = "data/schedules.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScheduleManager manager = new ScheduleManager();

        while (true) {
            try {
                System.out.println();
                System.out.println("1. 등록, 2. 전체 조회, 3. 상세 조회, 4. 수정, 5. 삭제, 6. 완료, " +
                                   "7. 제목 검색, 8. 날짜 검색, 9. 중요도 검색, " +
                                   "10. 날짜순 정렬, 11. 중요도순 정렬, 12. 완료 여부순 정렬, " +
                                   "13. 저장, 14. 불러오기, 15. 종료");

                System.out.print("번호를 입력해 주세요: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        try {
                            // 일정 등록
                            System.out.println();
                            System.out.println("=== 일정 등록 ===");
                            System.out.println("1. 일반 일정, 2. 회의 일정, 3. 할일 일정, 4. 알림 일정");
                            System.out.print("등록할 일정을 선택해 주세요: ");
                            int type = scanner.nextInt();
                            scanner.nextLine();

                            // 잘못된 번호 입력했을 때
                            if (type < 1 || type > 4) {
                                System.out.println("잘못된 번호입니다.");
                                break;
                            }

                            // 공통
                            System.out.print("제목 입력: ");
                            String title = scanner.nextLine();

                            System.out.print("내용 입력: ");
                            String description = scanner.nextLine();

//                            System.out.print("시작 날짜 입력: ");
                            LocalDate startDate = readDate(scanner, "시작 날짜 입력: ");

//                            System.out.print("마감 날짜 입력: ");
                            LocalDate endDate = readDate(scanner, "마감 날짜 입력: ");

//                            System.out.print("시작 시간 입력: ");
                            LocalTime startTime = readTime(scanner, "시작 시간 입력: ");

//                            System.out.print("마감 시간 입력: ");
                            LocalTime endTime = readTime(scanner, "마감 시간 입력: ");

//                            System.out.print("우선 순위 입력: ");
                            ScheduleItem.Priority priority = readPriority(scanner, "우선 순위 입력: ");

                            switch (type) {
                                case 1:
                                    System.out.print("카테고리 입력: ");
                                    String category = scanner.nextLine();

                                    System.out.print("장소 입력: ");
                                    String place = scanner.nextLine();

                                    System.out.print("메모 입력: ");
                                    String memo = scanner.nextLine();

                                    GeneralSchedule generalSchedule = new GeneralSchedule(title, description, startDate, endDate, startTime, endTime, priority, category, place, memo);
//                                    manager.addSchedule(generalSchedule);
//                                    System.out.println("일정이 등록되었습니다!");
                                    try {
                                        manager.addSchedule(generalSchedule);
                                        System.out.println("일정을 등록했습니다.");
                                    } catch (ScheduleException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 2:
                                    System.out.print("장소 입력: ");
                                    String location = scanner.nextLine();

                                    System.out.print("참가자 입력: ");
                                    String participants = scanner.nextLine();

                                    System.out.print("안건 입력: ");
                                    String agenda = scanner.nextLine();

                                    System.out.print("호스트 입력: ");
                                    String host = scanner.nextLine();

                                    MeetingSchedule meetingSchedule = new MeetingSchedule(title, description, startDate, endDate, startTime, endTime, priority, location, participants, agenda, host);
//                                    manager.addSchedule(meetingSchedule);
//                                    System.out.println("일정이 등록되었습니다!");
                                    try {
                                        manager.addSchedule(meetingSchedule);
                                        System.out.println("일정을 등록했습니다.");
                                    } catch (ScheduleException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 3:
//                                    System.out.print("마감일 입력: ");
                                    LocalDate deadline = readDate(scanner, "마감일 입력: ");

                                    System.out.print("담당자 입력: ");
                                    String assignedTo = scanner.nextLine();

                                    TaskSchedule taskSchedule = new TaskSchedule(title, description, startDate, endDate, startTime, endTime, priority, deadline, assignedTo);
//                                    manager.addSchedule(taskSchedule);
//                                    System.out.println("일정이 등록되었습니다!");
                                    try {
                                        manager.addSchedule(taskSchedule);
                                        System.out.println("일정을 등록했습니다.");
                                    } catch (ScheduleException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 4:
                                    // 알림 일정
//                                    System.out.print("알림 시간 입력: ");
                                    LocalTime reminderTime = readTime(scanner, "알림 시간 입력: ");

                                    System.out.print("알림 메시지 입력: ");
                                    String reminderMessage = scanner.nextLine();

//                                    System.out.print("알림 Type 입력: ");
                                    ReminderSchedule.NotificationType notificationType = readNotificationType(scanner, "알림 Type 입력: ");

                                    ReminderSchedule reminderSchedule = new ReminderSchedule(title, description, startDate, endDate, startTime, endTime, priority, reminderTime, reminderMessage, notificationType);
//                                    manager.addSchedule(reminderSchedule);
//                                    System.out.println("일정이 등록되었습니다!");
                                    try {
                                        manager.addSchedule(reminderSchedule);
                                        System.out.println("일정을 등록했습니다.");
                                    } catch (ScheduleException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                            }

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("=== 전체 일정 목록 ===");
                        manager.displayAllSchedules();
                        break;

                    case 3:
                        // 상세 조회
                        // id 받아야됨. scanner
                        System.out.println("=== 일정 상세 조회 ===");
                        System.out.print("조회할 일정 ID: ");

                        int idSearch = scanner.nextInt();
                        scanner.nextLine();

//                        manager.displayScheduleById(idSearch);
                        try {
                            manager.displayScheduleById(idSearch);
                        } catch (ScheduleException e) {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 4:
                        // 수정
                        // 새로 다 받아야댐
                        // String title, String description,
                        // LocalDate startDate, LocalDate endDate,
                        // LocalTime startTime, LocalTime endTime, Priority priority
                        try {
                            System.out.println("===일정 수정 ===");
                            System.out.print("수정할 일정 ID: ");

                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            // 수정 대상 찾기
                            ScheduleItem item = manager.findById(updateId);

//                            if (item == null) {
//                                System.out.println("수정하려는 일정이 없습니다.");
//                                break;
//                            }

                            System.out.print("새 제목 입력: ");
                            String newTitle = scanner.nextLine();

                            System.out.print("새 내용 입력: ");
                            String newDescription = scanner.nextLine();

//                            System.out.print("새 시작 날짜 입력: ");
                            LocalDate newStartDate = readDate(scanner, "새 시작 날짜 입력: ");

//                            System.out.print("새 마감 날짜 입력: ");
                            LocalDate newEndDate = readDate(scanner, "새 마감 날짜 입력: ");

//                            System.out.print("새 시작 시간 입력: ");
                            LocalTime newStartTime = readTime(scanner, "새 시작 시간 입력: ");

//                            System.out.print("새 마감 시간 입력: ");
                            LocalTime newEndTime = readTime(scanner, "새 마감 시간 입력: ");

//                            System.out.print("새 우선순위 입력: ");
                            ScheduleItem.Priority newPriority = readPriority(scanner, "새 우선순위 입력: ");

                            manager.updateSchedule(updateId, newTitle, newDescription, newStartDate, newEndDate, newStartTime, newEndTime, newPriority);

                            // 고유 필드도 수정 가능하게 -> 타입별로. instanceof 사용. if문 써서 비교하면서
                            if (item instanceof GeneralSchedule) {
                                // category, place, memo
                                GeneralSchedule generalSchedule = (GeneralSchedule) item;

                                System.out.print("새 카테고리 입력: ");
                                String newCategory = scanner.nextLine();

                                System.out.print("새 장소 입력: ");
                                String newPlace = scanner.nextLine();

                                System.out.print("새 메모 입력: ");
                                String newMemo = scanner.nextLine();

                                generalSchedule.setCategory(newCategory);
                                generalSchedule.setPlace(newPlace);
                                generalSchedule.setMemo(newMemo);

                            } else if (item instanceof MeetingSchedule) {
                                // location, participants, agenda, host
                                MeetingSchedule meetingSchedule = (MeetingSchedule) item;

                                System.out.print("새 위치 입력: ");
                                String newLocation = scanner.nextLine();

                                System.out.print("새 참가자 입력: ");
                                String newParticipants = scanner.nextLine();

                                System.out.print("새 안건 입력: ");
                                String newAgenda = scanner.nextLine();

                                System.out.print("새 호스트 입력: ");
                                String newHost = scanner.nextLine();

                                meetingSchedule.setLocation(newLocation);
                                meetingSchedule.setParticipants(newParticipants);
                                meetingSchedule.setAgenda(newAgenda);
                                meetingSchedule.setHost(newHost);

                            } else if (item instanceof TaskSchedule) {
                                // deadline, assignedTo
                                TaskSchedule taskSchedule = (TaskSchedule) item;

                                LocalDate newDeadline = readDate(scanner, "마감일 입력: ");

                                System.out.print("새 담당자 입력: ");
                                String newAssignedTo = scanner.nextLine();

                                taskSchedule.setDeadline(newDeadline);
                                taskSchedule.setAssignedTo(newAssignedTo);

                            } else if (item instanceof ReminderSchedule) {
                                // reminderTime, reminderMessage, notificationType
                                ReminderSchedule reminderSchedule = (ReminderSchedule) item;

                                LocalTime newReminderTime = readTime(scanner, "새 알림 시간 입력: ");

                                System.out.print("새 알림 메시지 입력: ");
                                String newReminderMessage = scanner.nextLine();

                                ReminderSchedule.NotificationType newNotificationType = readNotificationType(scanner, "새 알림 Type 입력: ");

                                reminderSchedule.setReminderTime(newReminderTime);
                                reminderSchedule.setReminderMessage(newReminderMessage);
                                reminderSchedule.setNotificationType(newNotificationType);
                            }

                            System.out.println("일정이 수정되었습니다!");
                            break;
                        } catch (ScheduleException e) {
                            System.out.println(e.getMessage());
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                    case 5:
                        // 삭제
                        System.out.println("=== 일정 삭제 ===");
                        System.out.print("삭제할 일정 ID: ");

                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            manager.deleteSchedule(deleteId);
                            System.out.println("일정이 삭제되었습니당!");
                        } catch (ScheduleException e) {
                           System.out.println(e.getMessage());
                        }

                        break;

                    case 6:
                        // 완료
                        System.out.println("=== 일정 완료 처리 ===");
                        System.out.print("완료할 일정 ID: ");

                        int completeId = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            manager.completeSchedule(completeId);
                            System.out.println("일정이 완료되었습니다!");
                        } catch (ScheduleException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 7:
                        // 제목 검색
                        // searchByTitle
                        // displayInfo() 호출
                        System.out.println("=== 제목 검색 ===");
                        System.out.print("검색할 제목: ");

                        String searchTitle = scanner.nextLine();

                        ScheduleItem[] titleResults = manager.searchByTitle(searchTitle);

                        for (ScheduleItem scheduleItem : titleResults) {
                            scheduleItem.displayInfo();
                            System.out.println();
                        }

                        break;

                    case 8:
                        // 날짜 검색
                        System.out.println("=== 날짜 검색 ===");
//                        System.out.print("검색할 날짜: ");

                        LocalDate searchDate = readDate(scanner, "검색할 날짜: ");

                        ScheduleItem[] dateResults = manager.searchByDate(searchDate);

                        for (ScheduleItem scheduleItem : dateResults) {
                            scheduleItem.displayInfo();
                            System.out.println();
                        }
                        break;

                    case 9:
                        // 중요도 검색
                        System.out.println("=== 중요도 검색 ===");
//                        System.out.print("검색할 중요도 입력 (HIGH/MEDIUM/LOW): ");

                        ScheduleItem.Priority searchPriority = readPriority(scanner, "검색할 중요도 입력 (HIGH/MEDIUM/LOW): ");

                        ScheduleItem[] priorityResults = manager.searchByPriority(searchPriority);

                        for (ScheduleItem scheduleItem : priorityResults) {
                                scheduleItem.displayInfo();
                                System.out.println();
                        }

                        break;

                    case 10:
                        // 날짜순 정렬
                        System.out.println("=== 날짜순 정렬 ===");

                        manager.sortByDate();
                        manager.displayAllSchedules();

                        break;

                    case 11:
                        // 중요도순 정렬
                        System.out.println("=== 중요도순 정렬 ===");

                        manager.sortByPriority();
                        manager.displayAllSchedules();

                        break;

                    case 12:
                        // 완료 여부순 정렬
                        System.out.println("=== 완료 여부순 정렬 ===");

                        manager.sortByCompletion();
                        manager.displayAllSchedules();

                        break;

                    case 13:
                        try {
                            manager.saveToFile(SAVE_FILE);
                            System.out.println("일정을 저장했습니다.");
                        } catch (ScheduleStorageException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 14:
                        try {
                            manager.loadFromFile(SAVE_FILE);
                            System.out.println("일정을 불러왔습니다.");
                        } catch (ScheduleStorageException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 15:
                        System.out.println("프로그램을 종료합니다.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("잘못된 번호입니다.");
                }
                //break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해 주세요.");
                scanner.nextLine();
            }
        }
    }

    // 입력 예외 처리를 위한 메서드들
    // 날짜 반환 - 날짜 물어보기 - 틀리면 오류 메시지 출력 - 다시 물어보기 - 맞으면 LocalDate 돌려줌
    private static LocalDate readDate(Scanner scanner, String prompt) {
        // 1. 무한 반복 시작
        while (true) {
            // 2. 안내문 출력
//            System.out.println("날짜 입력: ");
            System.out.print(prompt);

            // 3. 한 줄 입력
            String input = scanner.nextLine();

            // 4. LocalDate.parse() 시도 (yyyy-MM-dd)
            // 5. 성공하면 return
            // 6. 실패하면 DateTimeParseException 잡기
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("날짜 형식이 올바르지 않습니다. 예: 2026-06-12");
            }
        }
    }

    // 시간 반환
    private static LocalTime readTime(Scanner scanner, String prompt) {
        while (true) {
//            System.out.println("시간 입력: ");
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                return LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("시간 형식이 올바르지 않습니다. 예: 14:30");
            }
        }
    }

    // 우선순위 반환
    private static ScheduleItem.Priority readPriority(Scanner scanner, String prompt) {
        while (true) {
//            System.out.println("우선순위 입력: ");
            System.out.print(prompt);
            // HIGH, MEDIUM, LOW
            String input = scanner.nextLine().toUpperCase();

            try {
                return ScheduleItem.Priority.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("우선순위는 HIGH, MEDIUM, LOW 중 하나만 입력하세요.");
            }
        }
    }

    // 알림 타입 반환
    private static ReminderSchedule.NotificationType readNotificationType(Scanner scanner, String prompt) {
        while (true) {
//            System.out.println("알림 타입 입력: ");
            System.out.print(prompt);
            String input = scanner.nextLine().toUpperCase();

            try {
                return ReminderSchedule.NotificationType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("알림 타입은 POPUP, SOUND, MESSAGE 중 하나만 입력하세요.");
            }
        }
    }
}