package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskSchedule extends ScheduleItem {

    public enum TaskStatus {
        TODO, IN_PROGRESS, DONE
    }

    private LocalDate deadline;
    private int progress;
    private TaskStatus taskStatus;
    private String assignedTo;

    public TaskSchedule(String title, String description,
                        LocalDate startDate, LocalDate endDate,
                        LocalTime startTime, LocalTime endTime,
                        Priority priority, int userId,
                        LocalDate deadline, String assignedTo) {
        super(title, description, startDate, endDate, startTime, endTime, priority, userId);
        this.deadline = deadline;
        this.progress = 0; // 새로 등록이니까 진행률 0
        this.taskStatus = TaskStatus.TODO; // 새로 등록이니까
        this.assignedTo = assignedTo;
    }

    public TaskSchedule(int id, String title, String description,
                        LocalDate startDate, LocalDate endDate,
                        LocalTime startTime, LocalTime endTime,
                        Priority priority, int userId,
                        LocalDate deadline, String assignedTo) {
        super(id, title, description, startDate, endDate, startTime, endTime, priority, userId);
        this.deadline = deadline;
        this.progress = 0;
        this.taskStatus = TaskStatus.TODO;
        this.assignedTo = assignedTo;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getProgress() {
        return progress;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        touchUpdatedAt();
    }

    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
//            System.err.println("progress는 0 이상 100 이하의 정수여야 한다.");
            throw new IllegalArgumentException("progress는 0 이상 100 이하여야 합니다.");
        }

        this.progress = progress;
        touchUpdatedAt();
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        touchUpdatedAt();
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
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
        System.out.println("deadline: " + getDeadline());
        System.out.println("progress: " + getProgress());
        System.out.println("status: " + getTaskStatus());
        System.out.println("assignedTo: " + getAssignedTo());
    }

    @Override
    public String getScheduleType() {
        return "Task Schedule";
    }

    // 수동 완료
    @Override
    public void markAsCompleted() {
        setCompleted(true);
        setProgress(100);
        setTaskStatus(TaskStatus.DONE);
    }

    public void autoComplete() {
        if (LocalDate.now().equals(getEndDate().plusDays(1))) {
            setCompleted(true);
            setProgress(100);
            setTaskStatus(TaskStatus.DONE);
        }
    }

    @Override
    public void notifyUser() {
        System.out.println("알림 대상이 아닙니다!");
    }
}