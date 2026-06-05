package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public abstract class ScheduleItem {
    // 수정 해야 함: 클래스를 manager랑 나누기

    private static ScheduleItem[] schedules = new ScheduleItem[100];
    private static int size = 0;

    // HIGH, MEDIUM, LOW
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    private final int id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isCompleted;

    private static int idCounter = 1;

    public ScheduleItem() {
        this.id = idCounter++;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isCompleted = false;
    }

    public ScheduleItem(String title, String description, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Priority priority) {
        this(); // id, createdAt, updatedAt, isCompleted 초기화
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        
        // 예외 처리 - startDate와 endDate 관계
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("시작 날짜는 마감 날짜보다 빨라야 합니다.");
        }
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isCompleted() { return isCompleted; }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        this.updatedAt = LocalDateTime.now();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        this.updatedAt = LocalDateTime.now();
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        this.updatedAt = LocalDateTime.now();
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
        this.updatedAt = LocalDateTime.now();
    }

    public static void addSchedule(ScheduleItem item) {
        // 1. 충돌 확인 (checkConflict 호출)
        int targetIndex = -1;

        if (checkConflict(targetIndex, item.getStartDate(), item.getStartTime(), item.getEndDate(), item.getEndTime())) {
            // 충돌이 발생하면 함수를 여기서 종료 (등록 안 함)
            System.out.println("일정이 겹쳐서 등록할 수 없습니다.");
            return;
        }

        schedules[size++] = item;
    }

    public static void displayAllSchedules() {
        // 등록된 모든 일정 출력

        // 일정 없을 때
        if (size == 0) {
            System.out.println("등록된 일정이 없습니다.");
            return;
        }

        for (int i = 0; i < size; i++) {
            schedules[i].displayInfo();
            System.out.println();
        }
    }

    public static void displayScheduleById(int id) {
        // id 입력받아 특정 일정의 상세 정보 출력

        boolean isFound = false;

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                isFound = true;
                schedules[i].displayInfo();
                return;
            }
        }

        if (isFound == false) {
            System.out.println("해당 id가 존재하지 않습니다.");
        }
    }

    public static void updateSchedule(int id, String title, String description,
                                      LocalDate startDate, LocalDate endDate,
                                      LocalTime startTime, LocalTime endTime,
                                      Priority priority) {

        int targetIndex = -1;

        boolean isFound = false;

        if (checkConflict(targetIndex, startDate, startTime, endDate, endTime)) {
            System.out.println("일정이 겹쳐서 수정할 수 없습니다.");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                isFound = true;

                schedules[i].setTitle(title);
                schedules[i].setDescription(description);
                schedules[i].setStartDate(startDate);
                schedules[i].setEndDate(endDate);
                schedules[i].setStartTime(startTime);
                schedules[i].setEndTime(endTime);
                schedules[i].setPriority(priority);
                return;
            }
        }

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("시작 날짜는 마감 날짜보다 빨라야 합니다.");
        }

        if (isFound == false) {
            System.out.println("해당 id가 존재하지 않습니다.");
        }
    }

    public static void deleteSchedule(int id) {
        // id 입력받아 해당 일정 삭제

        boolean isFound = false;

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                isFound = true;

                for (int j = i; j < size - 1; j++) {
                    schedules[j] = schedules[j + 1];
                }
                schedules[--size] = null;
                return;
            }
        }

        if (isFound == false) {
            System.out.println("해당 id가 존재하지 않습니다.");
        }
    }

    public static void completeSchedule(int id) {

        boolean isFound = false;

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                isFound = true;

                schedules[i].markAsCompleted();
                return;
            }
        }

        if (isFound == false) {
            System.out.println("해당 id가 존재하지 않습니다.");
        }
    }

    public static ScheduleItem[] searchByTitle(String title) {
        // title을 기준으로 일정을 검색

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (schedules[i].getTitle() != null && schedules[i].getTitle().contains(title)) {
                count++;
            }
        }

        // result에 추가해서 return?
        ScheduleItem[] result = new ScheduleItem[count];

        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (schedules[i].getTitle() != null && schedules[i].getTitle().contains(title)) {
                result[idx++] = schedules[i];
            }
        }

        return result;
    }

    public static ScheduleItem[] searchByDate(LocalDate startDate) {
        // startDate를 기준으로 일정 검색
        // 수정 해야 함: 같은 일 한번만 하도록

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (schedules[i].getStartDate() != null && schedules[i].getStartDate().equals(startDate)) {
                count++;
            }
        }

        // result
        ScheduleItem[] result = new ScheduleItem[count];

        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (schedules[i].getStartDate() != null && schedules[i].getStartDate().equals(startDate)) {
                result[idx++] = schedules[i];
            }
        }

        return result;
    }

    public static ScheduleItem[] searchByPriority(Priority priority) {
        // priority를 기준으로 일정 검색

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (priority == schedules[i].getPriority()) {
                count++;
            }
        }

        ScheduleItem[] result = new ScheduleItem[count];

        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (priority == schedules[i].getPriority()) {
                result[idx++] = schedules[i];
            }
        }

        return result;
    }

    public static void sortByDate() {
        // startDate와 startTime을 기준으로 일정 정렬
        Arrays.sort(schedules, 0, size, (a, b) -> {
            // compareTo는 a가 b보다 이후이면 양수, 같으면 0, 이전이면 음수
            int cmp = a.getStartDate().compareTo(b.getStartDate());

            if (cmp != 0) {
                return cmp;
            }

            // 날짜 같으면 시간 비교 해야함.
            return a.getStartTime().compareTo(b.getStartTime());
        });
    }

    public static void sortByPriority() {
        // HIGH, MEDIUM, LOW 순서로 일정 정렬
        // sort / a랑 b랑 비교해서

        Arrays.sort(schedules, 0, size, (a, b) -> a.getPriority().compareTo(b.getPriority()));
    }

    public static void sortByCompletion() {
        // 완료되지 않은 일정이 먼저 나오도록 정렬
        // Boolean 비교
        Arrays.sort(schedules, 0, size, (a, b) -> Boolean.compare(a.isCompleted(), b.isCompleted()));
    }

    public static boolean checkConflict(int targetIndex, LocalDate newStartDate, LocalTime newStartTime, LocalDate newEndDate, LocalTime newEndTime) {
        // 등록 또는 수정 시 기존 일정과 시간이 겹치는지 확인

        for (int i = 0; i < size; i++) {
//            ScheduleItem s = schedules[i];

            if (i == targetIndex) {
                continue;
            }
            // 같은지 - equals / 기존 일정과 시간 / isBefore, isAfter

            // 날짜 데이터와 시간 데이터를 합쳐서 하나의 시간 객체로
            // 새로운거
            LocalDateTime newStart = LocalDateTime.of(newStartDate, newStartTime);
            LocalDateTime newEnd = LocalDateTime.of(newEndDate, newEndTime);

            // 기존
            LocalDateTime sStart = LocalDateTime.of(schedules[i].getStartDate(), schedules[i].getStartTime());
            LocalDateTime sEnd = LocalDateTime.of(schedules[i].getEndDate(), schedules[i].getEndTime());

            // 2. 겹침 조건 확인: (새시작 < 기존종료) && (새종료 > 기존시작)
            if (newStart.isBefore(sEnd) && newEnd.isAfter(sStart)) {
                System.out.println("충돌 발생! 다음 일정과 시간이 겹칩니다:");
                schedules[i].getId();
                schedules[i].getTitle();
                schedules[i].getStartDate();
                schedules[i].getStartTime();
                schedules[i].getEndDate();
                schedules[i].getEndTime();

                return true;
            }
        }
        return false;
    }

    public static void runNotification(int id) {
        // id를 입력받아 해당 일정의 notifyUser를 실행

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                schedules[i].notifyUser();
                return;
            }
        }
    }

    public abstract void displayInfo();

    public abstract String getScheduleType();

    public abstract void markAsCompleted();

    public abstract void notifyUser();

}