package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GeneralSchedule[] schedules = new GeneralSchedule[100];

        int size = 0;

        int idCounter = 1;

        while (true) {
            try {
                System.out.println();
                System.out.println("1. 일반 일정, 2. 회의 일정, 3. 할일 일정, 4. 알림 일정, 5. 종료");
                System.out.print("원하는 일정의 번호를 입력해 주세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("1. 일정 등록, 2. 전체 일정 조회, 3. 일정 상세 조회, 4. 일정 수정, 5. 일정 삭제, 6. 일정 완료, " +
                                "7. 제목 검색, 8. 날짜 검색, 9. 중요도 검색, 10. 날짜순 정렬, 11. 중요도순 정렬, 12. 완료 여부순 정렬, 13. 종료");
                        System.out.print("번호를 입력해 주세요: ");
                        int number = scanner.nextInt();
                        scanner.nextLine();

//                        String item;

                        switch (number) {

                            case 1:

                                System.out.println("=== 일반 일정 등록 ===");

                                System.out.print("ID(숫자) 입력: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();

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
                                ScheduleItem.Priority priority = ;

                                System.out.print("카테고리 입력: ");
                                String category = scanner.nextLine();

                                System.out.print("장소 입력: ");
                                String place = scanner.nextLine();

                                System.out.print("메모 입력: ");
                                String memo = scanner.nextLine();

                                GeneralSchedule generalSchedule = new GeneralSchedule( id, title, description, startDate, endDate, startTime, endTime, priority, category, place, memo );

                                ScheduleItem.addSchedule(generalSchedule);

                                System.out.println("일정이 성공적으로 등록되었습니다!");
                                break;

                            case 2:
                                System.out.println("=== 전체 일정 목록 ===");

                                for (int i = 0; i < schedules.size; i++) {
                                    schedules.displayInfo();
                                    System.out.println(ScheduleItem.schedules[i].getTitle());
                                }
                                break;

                        }
                        break;

                            case 3:
                                System.out.print("이체할 금액을 입력하세요: ");
                                amount = scanner.nextLong();

                                scanner.nextLine();

                                System.out.print("이체받을 사람의 성함을 입력하세요: ");
                                transferName = scanner.nextLine();

                                checkingAccount.transfer(amount, transferName);
                                break;

                            case 4:
                                checkingAccount.inquiry();
                                break;

                            case 5:
                                System.out.println("프로그램을 종료합니다.");
                                return;

                            default:
                                System.out.println("잘못된 번호입니다.");

                        }
                        break;

                    case 2:
                        System.out.println("1. 입금, 2. 조회, 3. 종료");
                        System.out.print("번호를 입력해 주세요: ");
                        int num = scanner.nextInt();

                        switch (num) {
                            case 1:
                                System.out.print("입금할 금액을 입력하세요: ");
                                amount = scanner.nextLong();
                                savingsAccount.deposit(amount);
                                break;

                            case 2:
                                savingsAccount.inquiry();
                                break;

                            case 3:
                                System.out.println("프로그램을 종료합니다.");
                                return;

                            default:
                                System.out.println("잘못된 번호입니다.");
                        }
                        break;

                    case 5:
                        System.out.println("프로그램을 종료합니다.");
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

        scanner.close();

    }
}