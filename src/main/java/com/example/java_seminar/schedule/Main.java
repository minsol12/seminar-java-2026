package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                        ScheduleItem.Priority priority = ScheduleItem.Priority.valueOf(scanner.nextLine()); // 대문자만 받게 해야하나? toUpperCase()

                        switch (type) {
                            case 1:
                                System.out.print("카테고리 입력: ");
                                String category = scanner.nextLine();

                                System.out.print("장소 입력: ");
                                String place = scanner.nextLine();

                                System.out.print("메모 입력: ");
                                String memo = scanner.nextLine();

                                GeneralSchedule generalSchedule = new GeneralSchedule(title, description, startDate, endDate, startTime, endTime, priority, category, place, memo);
                                ScheduleItem.addSchedule(generalSchedule);
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
                                ScheduleItem.addSchedule(meetingSchedule);
                                System.out.println("일정이 등록되었습니다!");
                                break;

                            case 3:
                                System.out.print("마감일 입력: ");
                                LocalDate deadline = LocalDate.parse(scanner.nextLine());

                                System.out.print("담당자 입력: ");
                                String assignedTo = scanner.nextLine();

                                TaskSchedule taskSchedule = new TaskSchedule(title, description, startDate, endDate, startTime, endTime, priority, deadline, assignedTo);
                                ScheduleItem.addSchedule(taskSchedule);
                                System.out.println("일정이 등록되었습니다!");
                                break;

                            case 4:
                                // 알림 일정
                                System.out.print("알림 시간 입력: ");
                                LocalTime reminderTime = LocalTime.parse(scanner.nextLine());

                                System.out.print("알림 메시지 입력: ");
                                String reminderMessage = scanner.nextLine();

                                System.out.print("알림 Type 입력: ");
                                ReminderSchedule.NotificationType notificationType = ReminderSchedule.NotificationType.valueOf(scanner.nextLine());

                                ReminderSchedule reminderSchedule = new ReminderSchedule(title, description, startDate, endDate, startTime, endTime, priority, reminderTime, reminderMessage, notificationType);
                                ScheduleItem.addSchedule(reminderSchedule);
                                System.out.println("일정이 등록되었습니다!");
                                break;
                        }
                        break;

                    case 2:
                        System.out.println("=== 전체 일정 목록 ===");
                        ScheduleItem.displayAllSchedules();
                        break;

                    case 3:
                        // 상세 조회
                        // id 받아야됨. scanner
                        System.out.println("=== 일정 상세 조회 ===");
                        System.out.print("조회할 일정 ID: ");

                        int idSearch = scanner.nextInt();
                        scanner.nextLine();

                        ScheduleItem.displayScheduleById(idSearch);
                        break;

                    case 4:
                        // 수정
                        // 새로 다 받아야댐
                        // String title, String description,
                        // LocalDate startDate, LocalDate endDate,
                        // LocalTime startTime, LocalTime endTime, Priority priority

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
                        ScheduleItem.Priority newPriority = ScheduleItem.Priority.valueOf(scanner.nextLine());

                        ScheduleItem.updateSchedule(updateId, newTitle, newDescription, newStartDate, newEndDate, newStartTime, newEndTime, newPriority);


                        // 고유 필드도 수정되게 해야댐..
                        // 타입별로
                        // instanceof?
                        // if문 써서 비교하면서


                        System.out.println("일정이 수정되었습니다!");
                        break;

                    case 5:
                        // 삭제
                        break;

                    case 6:
                        // 완료
                        break;

                    case 7:
                        // 제목 검색
                        break;

                    case 8:
                        // 날짜 검색
                        break;

                    case 9:
                        // 중요도 검색
                        break;

                    case 10:
                        // 날짜순 정렬
                        break;

                    case 11:
                        // 중요도순 정렬
                        break;

                    case 12:
                        // 완료 여부순 정렬
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