package com.example.java_seminar.scheduleProgram.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingSchedule extends ScheduleItem {
    private String location;
//    private String participants;
    private List<Integer> participantUserIds;
    private String agenda;
    private int host;

    public MeetingSchedule(String title, String description,
                           LocalDate startDate, LocalDate endDate,
                           LocalTime startTime, LocalTime endTime,
                           Priority priority, int userId,
                           String location, List<Integer> participantUserIds,
                           String agenda, int host) {
        super(title, description, startDate, endDate, startTime, endTime, priority, userId);
        this.location = location;
        this.participantUserIds = new ArrayList<>(participantUserIds);
        this.agenda = agenda;
        this.host = host;
    }

    public MeetingSchedule(int id, String title, String description,
                           LocalDate startDate, LocalDate endDate,
                           LocalTime startTime, LocalTime endTime,
                           Priority priority, int userId, boolean isCompleted,
                           String location, List<Integer> participantUserIds,
                           String agenda, int host) {
        super(id, title, description, startDate, endDate, startTime, endTime, priority, userId, isCompleted);
        this.location = location;
        this.participantUserIds = new ArrayList<>(participantUserIds);
        this.agenda = agenda;
        this.host = host;
    }

    public String getLocation() {
        return location;
    }

    public List<Integer> getParticipantUserIds() {
        return participantUserIds;
    }

    public String getAgenda() {
        return agenda;
    }

    public int getHost() {
        return host;
    }

    public void setLocation(String location) {
        this.location = location;
        touchUpdatedAt();
    }

    public void setParticipantUserIds(List<Integer> participantUserIds) {
        // 그대로 참조하지 않고 새 리스트로 복사
        this.participantUserIds = new ArrayList<>(participantUserIds);
        touchUpdatedAt();
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
        touchUpdatedAt();
    }

    public void setHost(int host) {
        this.host = host;
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
        System.out.println("userId: " + getUserId());
        System.out.println("createdAt: " + getCreatedAt());
        System.out.println("updatedAt: " + getUpdatedAt());
        System.out.println("location: " + getLocation());
        System.out.println("participantUserIds: " + getParticipantUserIds());
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