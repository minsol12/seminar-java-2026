package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeetingSchedule extends ScheduleItem {
    private String location;
    private String participants;
    private String agenda;
    private String host;

    public MeetingSchedule(String title, String description,
                           LocalDate startDate, LocalDate endDate,
                           LocalTime startTime, LocalTime endTime,
                           Priority priority,
                           String location, String participants,
                           String agenda, String host) {
        super(title, description, startDate, endDate, startTime, endTime, priority);
        this.location = location;
        this.participants = participants;
        this.agenda = agenda;
        this.host = host;
    }

    public String getLocation() {
        return location;
    }

    public String getParticipants() {
        return participants;
    }

    public String getAgenda() {
        return agenda;
    }

    public String getHost() {
        return host;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public void setHost(String host) {
        this.host = host;
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
        System.out.println("location: " + getLocation());
        System.out.println("participants: " + getParticipants());
        System.out.println("agenda: " + getAgenda());
        System.out.println("host: " + getHost());
    }

    @Override
    public String getScheduleType() {
        return "Meeting Schedule";
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
