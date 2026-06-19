package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

// 날짜 잘못된 입력 했을 때 오류 메시지 출력 - 프로그램 안꺼지게
// 예외 처리 꼭 해두기!
// item이랑 manager이랑 나누기
// createAt이랑 updatedAt 둘다 어케 나오는지 비교해보기. create 처음 할 때 createAt이랑 updatedAt이랑 같게 하고 나중에 수정할때 updatedAt만 수정되게 하기.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScheduleManager manager = new ScheduleManager();

        while (true) {
            try {
                System.out.println();
                System.out.println("1. 등록, 2. 전체 조회, 3. 상세 조회, 4. 수정, 5. 삭제, 6. 완료, " +
                                   "7. 제목 검색, 8. 날짜 검색, 9. 중요도 검색, " +
                                   "10. 날짜순 정렬, 11. 중요도순 정렬, 12. 완료 여부순 정렬, 13. 종료");
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

                            System.out.print("시작 날짜 입력: ");
                            LocalDate startDate = LocalDate.parse(scanner.nextLine());

                            System.out.print("마감 날짜 입력: ");
                            LocalDate endDate = LocalDate.parse(scanner.nextLine());

                            System.out.print("시작 시간 입력: ");
                            LocalTime startTime = LocalTime.parse(scanner.nextLine());

                            System.out.print("마감 시간 입력: ");
                            LocalTime endTime = LocalTime.parse(scanner.nextLine());

                            System.out.print("우선 순위 입력: ");
                            ScheduleItem.Priority priority = ScheduleItem.Priority.valueOf(scanner.nextLine().toUpperCase()); // 대문자로 바뀌게

                            switch (type) {
                                case 1:
                                    System.out.print("카테고리 입력: ");
                                    String category = scanner.nextLine();

                                    System.out.print("장소 입력: ");
                                    String place = scanner.nextLine();

                                    System.out.print("메모 입력: ");
                                    String memo = scanner.nextLine();

                                    GeneralSchedule generalSchedule = new GeneralSchedule(title, description, startDate, endDate, startTime, endTime, priority, category, place, memo);
                                    manager.addSchedule(generalSchedule);
                                    System.out.println("일정이 등록되었습니다!");
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
                                    manager.addSchedule(meetingSchedule);
                                    System.out.println("일정이 등록되었습니다!");
                                    break;

                                case 3:
                                    System.out.print("마감일 입력: ");
                                    LocalDate deadline = LocalDate.parse(scanner.nextLine());

                                    System.out.print("담당자 입력: ");
                                    String assignedTo = scanner.nextLine();

                                    TaskSchedule taskSchedule = new TaskSchedule(title, description, startDate, endDate, startTime, endTime, priority, deadline, assignedTo);
                                    manager.addSchedule(taskSchedule);
                                    System.out.println("일정이 등록되었습니다!");
                                    break;

                                case 4:
                                    // 알림 일정
                                    System.out.print("알림 시간 입력: ");
                                    LocalTime reminderTime = LocalTime.parse(scanner.nextLine());

                                    System.out.print("알림 메시지 입력: ");
                                    String reminderMessage = scanner.nextLine();

                                    System.out.print("알림 Type 입력: ");
                                    ReminderSchedule.NotificationType notificationType = ReminderSchedule.NotificationType.valueOf(scanner.nextLine().toUpperCase());

                                    ReminderSchedule reminderSchedule = new ReminderSchedule(title, description, startDate, endDate, startTime, endTime, priority, reminderTime, reminderMessage, notificationType);
                                    manager.addSchedule(reminderSchedule);
                                    System.out.println("일정이 등록되었습니다!");
                                    break;
                            }

                        } catch (IllegalArgumentException e) {
                            System.out.println("시작 날짜는 마감 날짜보다 빨라야 합니다.");
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

                        manager.displayScheduleById(idSearch);
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

                            System.out.print("새 제목 입력: ");
                            String newTitle = scanner.nextLine();

                            System.out.print("새 내용 입력: ");
                            String newDescription = scanner.nextLine();

                            System.out.print("새 시작 날짜 입력: ");
                            LocalDate newStartDate = LocalDate.parse(scanner.nextLine());

                            System.out.print("새 마감 날짜 입력: ");
                            LocalDate newEndDate = LocalDate.parse(scanner.nextLine());

                            System.out.print("새 시작 시간 입력: ");
                            LocalTime newStartTime = LocalTime.parse(scanner.nextLine());

                            System.out.print("새 마감 시간 입력: ");
                            LocalTime newEndTime = LocalTime.parse(scanner.nextLine());

                            System.out.print("새 우선순위 입력: ");
                            ScheduleItem.Priority newPriority = ScheduleItem.Priority.valueOf(scanner.nextLine().toUpperCase());

                            manager.updateSchedule(updateId, newTitle, newDescription, newStartDate, newEndDate, newStartTime, newEndTime, newPriority);


                            // 고유 필드도 수정되게 해야댐..
                            // 타입별로
                            // instanceof?
                            // if문 써서 비교하면서


                            System.out.println("일정이 수정되었습니다!");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("시작 날짜는 마감 날짜보다 빨라야 합니다.");
                        }

                        break;

                    case 5:
                        // 삭제
                        System.out.println("=== 일정 삭제 ===");
                        System.out.print("삭제할 일정 ID: ");

                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        manager.deleteSchedule(deleteId);
                        System.out.println("일정이 삭제되었습니당!");

                        break;

                    case 6:
                        // 완료
                        System.out.println("=== 일정 완료 처리 ===");
                        System.out.print("완료할 일정 ID: ");

                        int completeId = scanner.nextInt();
                        scanner.nextLine();

                        manager.completeSchedule(completeId);
                        System.out.println("일정이 완료되었습니다!");
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
                        System.out.print("검색할 날짜: ");

                        LocalDate searchDate = LocalDate.parse(scanner.nextLine());

                        ScheduleItem[] dateResults = manager.searchByDate(searchDate);

                        for (ScheduleItem scheduleItem : dateResults) {
                            scheduleItem.displayInfo();
                            System.out.println();
                        }
                        break;

                    case 9:
                        // 중요도 검색
                        System.out.println("=== 중요도 검색 ===");
                        System.out.print("검색할 중요도 입력 (HIGH/MEDIUM/LOW): ");

                        ScheduleItem.Priority searchPriority = ScheduleItem.Priority.valueOf(scanner.nextLine().toUpperCase());

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
}