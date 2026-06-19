package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class GeneralSchedule extends ScheduleItem {
    private String category;
    private String place;
    private String memo;

    public GeneralSchedule(String title, String description,
                           LocalDate startDate, LocalDate endDate,
                           LocalTime startTime, LocalTime endTime,
                           Priority priority,
                           String category, String place, String memo) {
        super(title, description, startDate, endDate, startTime, endTime, priority);
        this.category = category;
        this.place = place;
        this.memo = memo;
    }

    public String getCategory() { return category; }
    public String getPlace() { return place; }
    public String getMemo() { return memo; }

    public void setCategory(String category) {
        this.category = category;
        touchUpdatedAt();
    }
    public void setPlace(String place) {
        this.place = place;
        touchUpdatedAt();
    }
    public void setMemo(String memo) {
        this.memo = memo;
        touchUpdatedAt();
    }

    @Override
    public void displayInfo() {
        System.out.println("=== 일정 정보 ===");
        System.out.println("id: " + getId());
        System.out.println("title: " + getTitle());
        System.out.println("description: " + getDescription());
        System.out.println("startDate: " + getStartDate());
        System.out.println("endDate: " + getEndDate());
        System.out.println("startTime: " + getStartTime());
        System.out.println("endTime: " + getEndTime());
        System.out.println("priority: " + getPriority());
        System.out.println("createdAt: " + getCreatedAt());
        System.out.println("updatedAt: " + getUpdatedAt());
        System.out.println("category: " + getCategory());
        System.out.println("place: " + getPlace());
        System.out.println("memo: " + getMemo());
    }

    @Override
    public String getScheduleType() {
        return "General Schedule";
    }

    @Override
    public void markAsCompleted() {
        // 오늘이 EndDate 하루 후가 되면 -> 완료 처리
        if (LocalDate.now().equals(getEndDate().plusDays(1))) {
            setCompleted(true);
        }
    }

    @Override
    public void notifyUser() {
        System.out.println("알림 대상이 아닙니다!");
    }
}