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
        schedules[size++] = item;
    }

    public static void displayAllSchedules() {
        // 등록된 모든 일정 출력

        // 일정 없을 때
        if (size == 0) {
            System.out.println("등록된 일정ㅇ이 없습니다.");
            return;
        }

        for (int i = 0; i < size; i++) {
            schedules[i].displayInfo();
            System.out.println();
        }
    }

    public static void displayScheduleById(int id) {
        // id 입력받아 특정 일정의 상세 정보 출력

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                schedules[i].displayInfo();
                return;
            }
        }
    }

    public static void updateSchedule(int id, String title, String description,
                                      LocalDate startDate, LocalDate endDate,
                                      LocalTime startTime, LocalTime endTime,
                                      Priority priority) {
        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
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
    }

    public static void deleteSchedule(int id) {
        // id 입력받아 해당 일정 삭제

        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                for (int j = i; j < size - 1; j++) {
                    schedules[j] = schedules[j + 1];
                }
                schedules[--size] = null;
                return;
            }
        }
    }

    public static void completeSchedule(int id) {
        for (int i = 0; i < size; i++) {
            if (schedules[i].getId() == id) {
                schedules[i].markAsCompleted();
                return;
            }
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

    public static boolean checkConflict(ScheduleItem newItem) {
        // 등록 또는 수정 시 기존 일정과 시간이 겹치는지 확인

        for (int i = 0; i < size; i++) {
            ScheduleItem s = schedules[i];
            // 같은지 - equals / 기존 일정과 시간 / isBefore, isAfter
            if (newItem.getStartDate().equals(s.getStartDate())
                    && newItem.getStartTime().isBefore(s.getEndTime())
                    && newItem.getEndTime().isAfter(s.getStartTime())) {
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