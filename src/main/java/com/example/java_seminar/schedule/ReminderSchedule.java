package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReminderSchedule extends ScheduleItem {

    public enum NotificationType {
        POPUP, SOUND, MESSAGE
    }

    private LocalTime reminderTime;
    private String reminderMessage;
    private NotificationType notificationType;
    private boolean isReminderSent;

    public ReminderSchedule(String title, String description,
                            LocalDate startDate, LocalDate endDate,
                            LocalTime startTime, LocalTime endTime,
                            Priority priority,
                            LocalTime reminderTime, String reminderMessage,
                            NotificationType notificationType) {
        super(title, description, startDate, endDate, startTime, endTime, priority);
        this.reminderTime = reminderTime;
        this.reminderMessage = reminderMessage;
        this.notificationType = notificationType;
        this.isReminderSent = false; // 새로 등록한 알림이니까 아직 알림 발송 안한거니까 false로
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public boolean isReminderSent() {
        return isReminderSent;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
        touchUpdatedAt();
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
        touchUpdatedAt();
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        touchUpdatedAt();
    }

    public void setReminderSent(boolean reminderSent) {
        isReminderSent = reminderSent;
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
        System.out.println("reminderTime: " + getReminderTime());
        System.out.println("reminderMessage: " + getReminderMessage());
        System.out.println("notificationType: " + getNotificationType());
        System.out.println("isReminderSent: " + isReminderSent());
    }

    @Override
    public String getScheduleType() {
        return "Reminder Schedule";
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
        System.out.println("Reminder Message: " + getReminderMessage());
        setReminderSent(true);
    }
}