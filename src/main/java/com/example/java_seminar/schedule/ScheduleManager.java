package com.example.java_seminar.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleManager {
//  private ScheduleItem[] schedules = new ScheduleItem[100];
//  private int size = 0;

  private List<ScheduleItem> schedules = new ArrayList<>();

  public void addSchedule(ScheduleItem item) {
    // 1. 충돌 확인 (checkConflict 호출)
    int targetIndex = -1;

    if (checkConflict(targetIndex, item.getStartDate(), item.getStartTime(), item.getEndDate(), item.getEndTime())) {
      // 충돌이 발생하면 함수를 여기서 종료 (등록 안 함)
      System.out.println("일정이 겹쳐서 등록할 수 없습니다.");
      return;
    }

//    schedules[size++] = item;
    schedules.add(item);
  }

  public void displayAllSchedules() {
    // 등록된 모든 일정 출력

    // 일정 없을 때
//    if (size == 0) {
    if (schedules.isEmpty()) {
      System.out.println("등록된 일정이 없습니다.");
      return;
    }

//    for (int i = 0; i < size; i++) {
//      schedules[i].displayInfo();
//      System.out.println();
//    }
    for (ScheduleItem item : schedules) {
      item.displayInfo();
      System.out.println();
    }
  }

  public void displayScheduleById(int id) {
    // id 입력받아 특정 일정의 상세 정보 출력

    boolean isFound = false;

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
    for (ScheduleItem item : schedules) {
      if (item.getId() == id) {
        isFound = true;
//        schedules[i].displayInfo();
        item.displayInfo();
        return;
      }
    }

    if (isFound == false) {
      System.out.println("해당 id가 존재하지 않습니다.");
    }
  }
  
  // id 찾기
  public ScheduleItem findById(int id) {
//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        return schedules[i];
//      }
//    }

    for (ScheduleItem item : schedules) {
      if (item.getId() == id) {
        return item;
      }
    }
    
    return null;
  }

  public void updateSchedule(int id, String title, String description,
                                    LocalDate startDate, LocalDate endDate,
                                    LocalTime startTime, LocalTime endTime,
                                    ScheduleItem.Priority priority) {

    // 수정 대상 id를 가진 일정 인덱스 찾기
    // 못 찾으면 -> "해당 id가 존재하지 않습니다." 출력 -> 종료
    // 찾으면 -> 그 인덱스가 targetIndex
    // 날짜/시간 검증
    // checkConflict(targetIndex, ...) 호출
    // 충돌 없으면 setter로 수정

    int targetIndex = -1;

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        targetIndex = i;
//        break;
//      }
//    }

    // 인덱스가 필요하므로 for-each 말고 schedules.size()와 schedules.get(i) 사용
    for (int i = 0; i < schedules.size(); i++) {
      if (schedules.get(i).getId() == id) {
        targetIndex = i;
        break;
      }
    }
    
    if (targetIndex == -1) {
      System.out.println("해당 id가 존재하지 않습니다.");
      return;
    }

    ScheduleItem.validateDateTime(startDate, endDate, startTime, endTime);

    if (checkConflict(targetIndex, startDate, startTime, endDate, endTime)) {
      System.out.println("일정이 겹쳐서 수정할 수 없습니다.");
      return;
    }

//    schedules[targetIndex].setTitle(title);
//    schedules[targetIndex].setDescription(description);
//    schedules[targetIndex].setStartDate(startDate);
//    schedules[targetIndex].setEndDate(endDate);
//    schedules[targetIndex].setStartTime(startTime);
//    schedules[targetIndex].setEndTime(endTime);
//    schedules[targetIndex].setPriority(priority);

    schedules.get(targetIndex).setTitle(title);
    schedules.get(targetIndex).setDescription(description);
    schedules.get(targetIndex).setStartDate(startDate);
    schedules.get(targetIndex).setEndDate(endDate);
    schedules.get(targetIndex).setStartTime(startTime);
    schedules.get(targetIndex).setEndTime(endTime);
    schedules.get(targetIndex).setPriority(priority);
  }

  public void deleteSchedule(int id) {
    // id 입력받아 해당 일정 삭제

    boolean isFound = false;

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        isFound = true;
//
//        for (int j = i; j < size - 1; j++) {
//          schedules[j] = schedules[j + 1];
//        }
//        schedules[--size] = null;
//        return;
//      }
//    }

    for (int i = 0; i < schedules.size(); i++) {
      if (schedules.get(i).getId() == id) {
        isFound = true;
        schedules.remove(i);
        break;
      }
    }

    if (isFound == false) {
      System.out.println("해당 id가 존재하지 않습니다.");
    }
  }

  public void completeSchedule(int id) {

    boolean isFound = false;

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        isFound = true;
//
//        schedules[i].markAsCompleted();
//        return;
//      }
//    }

    for (ScheduleItem item : schedules) {
      if (item.getId() == id) {
        isFound = true;
        item.markAsCompleted();
        return;
      }
    }

    if (isFound == false) {
      System.out.println("해당 id가 존재하지 않습니다.");
    }
  }

  public ScheduleItem[] searchByTitle(String title) {
    // title을 기준으로 일정을 검색

//    int count = 0;
//
//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getTitle() != null && schedules[i].getTitle().contains(title)) {
//        count++;
//      }
//    }
//
//    // result에 추가해서 return?
//    ScheduleItem[] result = new ScheduleItem[count];
//
//    int idx = 0;
//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getTitle() != null && schedules[i].getTitle().contains(title)) {
//        result[idx++] = schedules[i];
//      }
//    }
//
//    return result;

    // 결과를 List에 담았다가 마지막에 배열로 바꾸기
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getTitle() != null && item.getTitle().contains(title)) {
        result.add(item);
      }
    }

    return result.toArray(new ScheduleItem[0]);
  }

  public ScheduleItem[] searchByDate(LocalDate startDate) {
    // startDate를 기준으로 일정 검색
    // 수정 해야 함: 같은 일 한번만 하도록

//    int count = 0;
//
//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getStartDate() != null && schedules[i].getStartDate().equals(startDate)) {
//        count++;
//      }
//    }
//
//    // result
//    ScheduleItem[] result = new ScheduleItem[count];
//
//    int idx = 0;
//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getStartDate() != null && schedules[i].getStartDate().equals(startDate)) {
//        result[idx++] = schedules[i];
//      }
//    }
//
//    return result;

    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getStartDate() != null && item.getStartDate().equals(startDate)) {
        result.add(item);
      }
    }

    return result.toArray(new ScheduleItem[0]);
  }

  public ScheduleItem[] searchByPriority(ScheduleItem.Priority priority) {
    // priority를 기준으로 일정 검색

//    int count = 0;
//
//    for (int i = 0; i < size; i++) {
//      if (priority == schedules[i].getPriority()) {
//        count++;
//      }
//    }
//
//    ScheduleItem[] result = new ScheduleItem[count];
//
//    int idx = 0;
//    for (int i = 0; i < size; i++) {
//      if (priority == schedules[i].getPriority()) {
//        result[idx++] = schedules[i];
//      }
//    }
//
//    return result;

    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (priority == item.getPriority()) {
        result.add(item);
      }
    }

    return result.toArray(new ScheduleItem[0]);
  }

    public void sortByDate() {
    // startDate와 startTime을 기준으로 일정 정렬
//    Arrays.sort(schedules, 0, size, (a, b) -> {
//      // compareTo는 a가 b보다 이후이면 양수, 같으면 0, 이전이면 음수
//      int cmp = a.getStartDate().compareTo(b.getStartDate());
//
//      if (cmp != 0) {
//        return cmp;
//      }
//
//      // 날짜 같으면 시간 비교 해야함.
//      return a.getStartTime().compareTo(b.getStartTime());
//    });

      schedules.sort((a, b) -> {
        int cmp = a.getStartDate().compareTo(b.getStartDate());

        if (cmp != 0) {
          return cmp;
        }

        return a.getStartTime().compareTo(b.getStartTime());
      });
  }

  public void sortByPriority() {
    // HIGH, MEDIUM, LOW 순서로 일정 정렬
    // sort / a랑 b랑 비교해서

//    Arrays.sort(schedules, 0, size, (a, b) -> a.getPriority().compareTo(b.getPriority()));
    schedules.sort((a, b) -> a.getPriority().compareTo(b.getPriority()));
  }

  public void sortByCompletion() {
    // 완료되지 않은 일정이 먼저 나오도록 정렬
    // Boolean 비교
//    Arrays.sort(schedules, 0, size, (a, b) -> Boolean.compare(a.isCompleted(), b.isCompleted()));
    schedules.sort((a, b) -> Boolean.compare(a.isCompleted(), b.isCompleted()));
  }

  public boolean checkConflict(int targetIndex, LocalDate newStartDate, LocalTime newStartTime, LocalDate newEndDate, LocalTime newEndTime) {
    // 등록 또는 수정 시 기존 일정과 시간이 겹치는지 확인

//    for (int i = 0; i < size; i++) {
    for (int i = 0; i < schedules.size(); i++) {
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
//      LocalDateTime sStart = LocalDateTime.of(schedules[i].getStartDate(), schedules[i].getStartTime());
//      LocalDateTime sEnd = LocalDateTime.of(schedules[i].getEndDate(), schedules[i].getEndTime());
      LocalDateTime sStart = LocalDateTime.of(schedules.get(i).getStartDate(), schedules.get(i).getStartTime());
      LocalDateTime sEnd = LocalDateTime.of(schedules.get(i).getEndDate(), schedules.get(i).getEndTime());

      // 2. 겹침 조건 확인: (새시작 < 기존종료) && (새종료 > 기존시작)
      if (newStart.isBefore(sEnd) && newEnd.isAfter(sStart)) {
        System.out.println("충돌 발생! 다음 일정과 시간이 겹칩니다:");
//        schedules[i].getId();
//        schedules[i].getTitle();
//        schedules[i].getStartDate();
//        schedules[i].getStartTime();
//        schedules[i].getEndDate();
//        schedules[i].getEndTime();

        schedules.get(i).getId();
        schedules.get(i).getTitle();
        schedules.get(i).getStartDate();
        schedules.get(i).getStartTime();
        schedules.get(i).getEndDate();
        schedules.get(i).getEndTime();

        return true;
      }
    }
    return false;
  }

  public void runNotification(int id) {
    // id를 입력받아 해당 일정의 notifyUser를 실행

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        schedules[i].notifyUser();
//        return;
//      }
//    }

    for (ScheduleItem item : schedules) {
      if (item.getId() == id) {
        item.notifyUser();
        return;
      }
    }
  }
}