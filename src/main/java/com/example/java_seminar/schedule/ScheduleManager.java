package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class ScheduleManager {
  private ScheduleItem[] schedules = new ScheduleItem[100];
  private int size = 0;

  public void addSchedule(ScheduleItem item) {
    // 1. 충돌 확인 (checkConflict 호출)
    int targetIndex = -1;

    if (checkConflict(targetIndex, item.getStartDate(), item.getStartTime(), item.getEndDate(), item.getEndTime())) {
      // 충돌이 발생하면 함수를 여기서 종료 (등록 안 함)
      System.out.println("일정이 겹쳐서 등록할 수 없습니다.");
      return;
    }

    schedules[size++] = item;
  }

  public void displayAllSchedules() {
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

  public void displayScheduleById(int id) {
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

  public void updateSchedule(int id, String title, String description,
                                    LocalDate startDate, LocalDate endDate,
                                    LocalTime startTime, LocalTime endTime,
                                    ScheduleItem.Priority priority) {

    int targetIndex = -1;

    boolean isFound = false;

    ScheduleItem.validateDateTime(startDate, endDate, startTime, endTime);

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

//    if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
//      throw new IllegalArgumentException("시작 날짜는 마감 날짜보다 빨라야 합니다.");
//    }

    if (isFound == false) {
      System.out.println("해당 id가 존재하지 않습니다.");
    }
  }

  public void deleteSchedule(int id) {
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

  public void completeSchedule(int id) {

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

  public ScheduleItem[] searchByTitle(String title) {
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

  public ScheduleItem[] searchByDate(LocalDate startDate) {
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

  public ScheduleItem[] searchByPriority(ScheduleItem.Priority priority) {
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

  public void sortByDate() {
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

  public void sortByPriority() {
    // HIGH, MEDIUM, LOW 순서로 일정 정렬
    // sort / a랑 b랑 비교해서

    Arrays.sort(schedules, 0, size, (a, b) -> a.getPriority().compareTo(b.getPriority()));
  }

  public void sortByCompletion() {
    // 완료되지 않은 일정이 먼저 나오도록 정렬
    // Boolean 비교
    Arrays.sort(schedules, 0, size, (a, b) -> Boolean.compare(a.isCompleted(), b.isCompleted()));
  }

  public boolean checkConflict(int targetIndex, LocalDate newStartDate, LocalTime newStartTime, LocalDate newEndDate, LocalTime newEndTime) {
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

  public void runNotification(int id) {
    // id를 입력받아 해당 일정의 notifyUser를 실행

    for (int i = 0; i < size; i++) {
      if (schedules[i].getId() == id) {
        schedules[i].notifyUser();
        return;
      }
    }
  }
}