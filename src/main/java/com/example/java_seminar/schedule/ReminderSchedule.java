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
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public void setReminderSent(boolean reminderSent) {
        isReminderSent = reminderSent;
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
