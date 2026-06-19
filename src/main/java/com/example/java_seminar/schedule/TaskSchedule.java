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
                        Priority priority,
                        LocalDate deadline, String assignedTo) {
        super(title, description, startDate, endDate, startTime, endTime, priority);
        this.deadline = deadline;
        this.progress = 0; // 새로 등록이니까 진행률 0
        this.taskStatus = TaskStatus.TODO; // 새로 등록이니까
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
        this.progress = progress;
        touchUpdatedAt();

        if (progress < 0 || progress > 100) {
            System.err.println("progress는 0 이상 100 이하의 정수여야 한다.");
        }
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

    @Override
    public void markAsCompleted() {
        // 오늘이 EndDate 하루 후가 되면 -> 완료 처리 -> 이게 여기에 있는게 맞나?
        // 호출되면 완료처리
        if (LocalDate.now().equals(getEndDate().plusDays(1))) {
            setCompleted(true);
            setProgress(100);
            setTaskStatus(TaskStatus.DONE);
        }

        setCompleted(true);
        setProgress(100);
        setTaskStatus(TaskStatus.DONE);
    }

    @Override
    public void notifyUser() {
        System.out.println("알림 대상이 아닙니다!");
    }
}