package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class ScheduleItem {
    // 수정 해야 함: 클래스를 manager랑 나누기
    // ScheduleItem.java에 남길 것 - 일정 1개가 가져야 하는 데이터와 공통 동작
    // 필드, getter/setter, 생성자, displayInfo, getScheduleType, markAsCompleted, notifyUser
    // ScheduleManager - 일정 목록 관리

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
    private int userId;

    private static int idCounter = 1;

//    public ScheduleItem() {
//        this.id = idCounter++;
//        LocalDateTime now = LocalDateTime.now();
//        this.createdAt = now;
//        this.updatedAt = now;
//        this.isCompleted = false;
//    }

    public ScheduleItem(String title, String description, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Priority priority, int userId) {
//        this(); // id, createdAt, updatedAt, isCompleted 초기화
        this.id = idCounter++;
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.isCompleted = false;

        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.userId = userId;
        
        // 예외 처리 - startDate와 endDate 관계
//        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
//            throw new IllegalArgumentException("시작 날짜는 마감 날짜보다 빨라야 합니다.");
//        }

        validateDateTime(startDate, endDate, startTime, endTime);
    }

    // 복원용 생성자
    public ScheduleItem(int id, String title, String description,
                        LocalDate startDate, LocalDate endDate,
                        LocalTime startTime, LocalTime endTime,
                        Priority priority, int userId, boolean isCompleted) {
        this.id = id;
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.isCompleted = isCompleted;

        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.userId = userId;

        validateDateTime(startDate, endDate, startTime, endTime);

        if (id >= idCounter) {
            idCounter = id + 1;
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
    public int getUserId() { return userId; }

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

    public void setUserId(int userId) {
        this.userId = userId;
        this.updatedAt = LocalDateTime.now();
    }

    // updatedAt 갱신 메서드
    protected void touchUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
    
    public abstract void displayInfo();

    public abstract String getScheduleType();

    public abstract void markAsCompleted();

    public abstract void notifyUser();

    // 검증 메서드
    // 입력 - startDate, endDate, startTime, endTime
    // 출력 - 잘못된 범위일 때만
    //  - 일정 시간 범위 유효 검사
    //    1. 시작 날짜가 종료 날짜보다 늦으면 예외
    //    2. 시작 날짜와 종료 날짜가 같을 경우
    //       - 시작 시간이 종료 시간보다 늦으면 예외
    //       - 시작 시간과 종료 시간이 같으면 예외

    public static void validateDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 날짜는 종료 날짜보다 늦을 수 없습니다.");
        } else if (startDate.isEqual(endDate)) {
            if (!startTime.isBefore(endTime)) {
                throw new IllegalArgumentException("시작 시간이 종료 시간보다 빨라야 합니다.");
            }
        }
    }
}