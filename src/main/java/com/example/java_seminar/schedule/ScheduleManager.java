package com.example.java_seminar.schedule;

import jakarta.annotation.Priority;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

  public void addSchedule(ScheduleItem item) throws ScheduleConflictException{
    // 1. 충돌 확인 (checkConflict 호출)
    int targetIndex = -1;

    if (checkConflict(targetIndex, item.getUserId(), item.getStartDate(), item.getStartTime(), item.getEndDate(), item.getEndTime())) {
      // 충돌이 발생하면 함수를 여기서 종료 (등록 안 함)
//      System.out.println("일정이 겹쳐서 등록할 수 없습니다.");
//      return;
      throw new ScheduleConflictException("일정이 겹쳐서 등록할 수 없습니다.");
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

  public void displayScheduleById(int id) throws ScheduleNotFoundException {
    // id 입력받아 특정 일정의 상세 정보 출력

//    boolean isFound = false;
//
////    for (int i = 0; i < size; i++) {
////      if (schedules[i].getId() == id) {
//    for (ScheduleItem item : schedules) {
//      if (item.getId() == id) {
//        isFound = true;
////        schedules[i].displayInfo();
//        item.displayInfo();
//        return;
//      }
//    }
//
//    if (isFound == false) {
//      System.out.println("해당 id가 존재하지 않습니다.");
//    }

    ScheduleItem item = findById(id);
    item.displayInfo();
  }
  
  // id 찾기
  public ScheduleItem findById(int id) throws ScheduleNotFoundException {
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
    
//    return null;
    throw new ScheduleNotFoundException(id);
  }

  public void updateSchedule(int id, String title, String description,
                                    LocalDate startDate, LocalDate endDate,
                                    LocalTime startTime, LocalTime endTime,
                                    ScheduleItem.Priority priority) throws ScheduleException {

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
//      System.out.println("해당 id가 존재하지 않습니다.");
//      return;
      throw new ScheduleNotFoundException(id);
    }

    ScheduleItem.validateDateTime(startDate, endDate, startTime, endTime);

    if (checkConflict(targetIndex, schedules.get(targetIndex).getUserId(), startDate, startTime, endDate, endTime)) {
//      System.out.println("일정이 겹쳐서 수정할 수 없습니다.");
//      return;
      throw new ScheduleConflictException("일정이 겹쳐서 수정할 수 없습니다.");
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

  public void deleteSchedule(int id) throws ScheduleNotFoundException {
    // id 입력받아 해당 일정 삭제

//    boolean isFound = false;

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
//        isFound = true;
        schedules.remove(i);
//        break;
        return;
      }
    }

//    if (isFound == false) {
//      System.out.println("해당 id가 존재하지 않습니다.");
//    }
    throw new ScheduleNotFoundException(id);
  }

  public void completeSchedule(int id) throws ScheduleNotFoundException {

//    boolean isFound = false;

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        isFound = true;
//
//        schedules[i].markAsCompleted();
//        return;
//      }
//    }

//    for (ScheduleItem item : schedules) {
//      if (item.getId() == id) {
//        isFound = true;
//        item.markAsCompleted();
//        return;
//      }
//    }
//
//    if (isFound == false) {
//      System.out.println("해당 id가 존재하지 않습니다.");
//    }

    ScheduleItem item = findById(id);
    item.markAsCompleted();
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

  public boolean checkConflict(int targetIndex, int newUserId, LocalDate newStartDate, LocalTime newStartTime, LocalDate newEndDate, LocalTime newEndTime) {
    // 등록 또는 수정 시 기존 일정과 시간이 겹치는지 확인

//    for (int i = 0; i < size; i++) {
    for (int i = 0; i < schedules.size(); i++) {
//            ScheduleItem s = schedules[i];

      if (i == targetIndex) {
        continue;
      }

      if (schedules.get(i).getUserId() != newUserId) {
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

//        schedules.get(i).getId();
//        schedules.get(i).getTitle();
//        schedules.get(i).getStartDate();
//        schedules.get(i).getStartTime();
//        schedules.get(i).getEndDate();
//        schedules.get(i).getEndTime();

        schedules.get(i).displayInfo();

        return true;
      }
    }
    return false;
  }

  public void runNotification(int id) throws ScheduleNotFoundException {
    // id를 입력받아 해당 일정의 notifyUser를 실행

//    for (int i = 0; i < size; i++) {
//      if (schedules[i].getId() == id) {
//        schedules[i].notifyUser();
//        return;
//      }
//    }

//    for (ScheduleItem item : schedules) {
//      if (item.getId() == id) {
//        item.notifyUser();
//        return;
//      }
//    }

    ScheduleItem item = findById(id);
    item.notifyUser();
  }

  // 저장 메서드 추가
  public void saveToFile(String filePath) throws ScheduleStorageException {
    try {
      Path path = Path.of(filePath);  // Path.of("경로") - 문자열로 된 파일 경로를 Java가 이해하고 다룰 수 있는 객체로 바꾸는 변환기

      // 경로의 부모 디렉토리가 존재하는지 확인
      if (path.getParent() != null) {
        // 부모 디렉토리가 없으면 경로를 포함한 모든 디렉토리를 생성
        Files.createDirectories(path.getParent());
      }

      // 파일에 쓸 내용을 저장할 리스트 생성
      List<String> lines = new ArrayList<>();

      // 현재 가지고 있는 일정들을 순회하며 데이터 문자열로 변환 후 리스트에 추가
      for (ScheduleItem item : schedules) {
        lines.add(toDataString(item));
      }

      // 리스트의 모든 데이터를 파일로 쓰기 (UTF-8 인코딩 사용)
      Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
      // 입출력 오류 발생 시 사용자 정의 예외로 감싸서 던짐
      throw new ScheduleStorageException("일정 저장에 실패했습니다.", e);
    }
  }

  // 불러오기 메서드 추가
  public void loadFromFile(String filePath) throws ScheduleStorageException {
    try {
      Path path = Path.of(filePath);

      // 파일이 실제로 존재하는지 확인, 없으면 예외 발생
      if (!Files.exists(path)) {
        throw new ScheduleStorageException("저장 파일이 존재하지 않습니다.");
      }

      // 파일의 모든 줄을 읽어와 리스트로 저장
      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      List<ScheduleItem> loadedSchedules = new ArrayList<>();

      // 읽어온 각 줄을 객체로 복원하여 리스트에 추가
      for (String line : lines) {
        // 빈 줄은 건너뛰기
        if (line.isBlank()) {
          continue;
        }
//        schedules.add(fromDataString(line));
        loadedSchedules.add(fromDataString(line));
      }

      // 현재 일정 리스트를 초기화하여 중복 방지
      schedules.clear();
      schedules.addAll(loadedSchedules);
    } catch (IOException e) {
      throw new ScheduleStorageException("일정 불러오기에 실패했습니다.", e);
    }
  }

  // 저장용 문자열 변환 메서드
  // 구분자 |
  private String toDataString(ScheduleItem item) {
    // 인스턴스 타입 확인 (General, Meeting, Task, Reminder)
    if (item instanceof GeneralSchedule) {
      GeneralSchedule g = (GeneralSchedule) item;
      return "GENERAL|" + g.getId() + "|" + g.getTitle() + "|" + g.getDescription() + "|" +
              g.getStartDate() + "|" + g.getEndDate() + "|" +
              g.getStartTime() + "|" + g.getEndTime() + "|" +
              g.getPriority() + "|" + g.getUserId() + "|" + g.isCompleted() + "|" +
              g.getCategory() + "|" + g.getPlace() + "|" + g.getMemo();

    } else if (item instanceof MeetingSchedule) {
      MeetingSchedule m = (MeetingSchedule) item;
      return "MEETING|" + m.getId() + "|" + m.getTitle() + "|" + m.getDescription() + "|" +
              m.getStartDate() + "|" + m.getEndDate() + "|" +
              m.getStartTime() + "|" + m.getEndTime() + "|" +
              m.getPriority() + "|" + m.getUserId() + "|" + m.isCompleted() + "|" +
              m.getLocation() + "|" + m.getParticipants() + "|" +
              m.getAgenda() + "|" + m.getHost();

    } else if (item instanceof TaskSchedule) {
      TaskSchedule t = (TaskSchedule) item;
      return "TASK|" + t.getId() + "|" + t.getTitle() + "|" + t.getDescription() + "|" +
              t.getStartDate() + "|" + t.getEndDate() + "|" +
              t.getStartTime() + "|" + t.getEndTime() + "|" +
              t.getPriority() + "|" + t.getUserId() + "|" + t.isCompleted() + "|" +
              t.getDeadline() + "|" + t.getAssignedTo();

    } else if (item instanceof ReminderSchedule) {
      ReminderSchedule r = (ReminderSchedule) item;
      return "REMINDER|" + r.getId() + "|" + r.getTitle() + "|" + r.getDescription() + "|" +
              r.getStartDate() + "|" + r.getEndDate() + "|" +
              r.getStartTime() + "|" + r.getEndTime() + "|" +
              r.getPriority() + "|" + r.getUserId() + "|" + r.isCompleted() + "|" +
              r.getReminderTime() + "|" + r.getReminderMessage() + "|" +
              r.getNotificationType();
    }

    throw new IllegalArgumentException("지원하지 않는 일정 타입입니다.");
  }

  // 불러오기용 파싱 메서드
  private ScheduleItem fromDataString(String line) throws ScheduleStorageException {
    // 구분자(|)를 기준으로 문자열을 쪼개어 배열 생성
    String[] parts = line.split("\\|");

    try {
      // 첫 번째 요소(인덱스 0)를 보고 타입 결정
      switch (parts[0]) {
        case "GENERAL":
          return new GeneralSchedule(
                  Integer.parseInt(parts[1]),
                  parts[2], parts[3],
                  LocalDate.parse(parts[4]), LocalDate.parse(parts[5]),
                  LocalTime.parse(parts[6]), LocalTime.parse(parts[7]),
                  ScheduleItem.Priority.valueOf(parts[8]),
                  Integer.parseInt(parts[9]),
                  Boolean.parseBoolean(parts[10]),
                  parts[11], parts[12], parts[13]
          );

        case "MEETING":
          return new MeetingSchedule(
                  Integer.parseInt(parts[1]),
                  parts[2], parts[3],
                  LocalDate.parse(parts[4]), LocalDate.parse(parts[5]),
                  LocalTime.parse(parts[6]), LocalTime.parse(parts[7]),
                  ScheduleItem.Priority.valueOf(parts[8]),
                  Integer.parseInt(parts[9]),
                  Boolean.parseBoolean(parts[10]),
                  parts[11], parts[12], parts[13], parts[14]
          );

        case "TASK":
          return new TaskSchedule(
                  Integer.parseInt(parts[1]),
                  parts[2], parts[3],
                  LocalDate.parse(parts[4]), LocalDate.parse(parts[5]),
                  LocalTime.parse(parts[6]), LocalTime.parse(parts[7]),
                  ScheduleItem.Priority.valueOf(parts[8]),
                  Integer.parseInt(parts[9]),
                  Boolean.parseBoolean(parts[10]),
                  LocalDate.parse(parts[11]), parts[12]
          );

        case "REMINDER":
          return new ReminderSchedule(
                  Integer.parseInt(parts[1]),
                  parts[2], parts[3],
                  LocalDate.parse(parts[4]), LocalDate.parse(parts[5]),
                  LocalTime.parse(parts[6]), LocalTime.parse(parts[7]),
                  ScheduleItem.Priority.valueOf(parts[8]),
                  Integer.parseInt(parts[9]),
                  Boolean.parseBoolean(parts[10]),
                  LocalTime.parse(parts[11]), parts[12],
                  ReminderSchedule.NotificationType.valueOf(parts[13])
          );

        default:
          throw new ScheduleStorageException("알 수 없는 일정 타입입니다.");
      }
    } catch (Exception e) {
      throw new ScheduleStorageException("저장 파일 형식이 올바르지 않습니다.");
    }
  }

  //- displaySchedulesByUserId
  // 특정 사용자 ID 입력 -> 그 사용자에게 속한 일정만 출력
  // userId 받기 -> schedules 순회 -> item.getUserId() == userId만 출력

  public void displaySchedulesByUserId(int userId) {
    boolean found = false;

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId) {
        item.displayInfo();
        System.out.println();
        found = true;
      }
    }

    if (!found) {
      System.out.println("해당 사용자에게 등록된 일정이 없습니다.");
    }
  }

  //- searchByUserAndTitle
  public ScheduleItem[] searchByUserAndTitle(int userId, String title) {
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId && item.getTitle() != null && item.getTitle().contains(title)) {
        result.add(item);
      }
    }

    return result.toArray(new ScheduleItem[0]);
  }

  //- searchByUserAndDate
  public ScheduleItem[] searchByUserAndDate(int userId, LocalDate date) {
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId && item.getStartDate() != null && item.getStartDate().equals(date)) {
        result.add(item);
      }
    }

    return result.toArray(new ScheduleItem[0]);
  }

  //- searchByUserAndPriority
  public ScheduleItem[] searchByUserAndPriority(int userId, ScheduleItem.Priority priority) {
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId && item.getPriority() == priority) {
        result.add(item);
      }
    }

    return result.toArray(new ScheduleItem[0]);
  }

  //- sortByUserAndDate
  // 특정 사용자의 일정만 따로 모아서 정렬하고 반환
  public ScheduleItem[] sortByUserAndDate(int userId) {
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId) {
        result.add(item);
      }
    }

    result.sort((a, b) -> {
      int cmp = a.getStartDate().compareTo(b.getStartDate());

      if (cmp != 0) {
        return cmp;
      }

      return a.getStartTime().compareTo(b.getStartTime());
    });

    return result.toArray(new ScheduleItem[0]);
  }

  //- sortByUserAndPriority
  public ScheduleItem[] sortByUserAndPriority(int userId) {
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId) {
        result.add(item);
      }
    }

    result.sort((a, b) -> a.getPriority().compareTo(b.getPriority()));

    return result.toArray(new ScheduleItem[0]);
  }

  //- sortByUserAndCompletion
  public ScheduleItem[] sortByUserAndCompletion(int userId) {
    List<ScheduleItem> result = new ArrayList<>();

    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId) {
        result.add(item);
      }
    }

    result.sort((a, b) -> Boolean.compare(a.isCompleted(), b.isCompleted()));

    return result.toArray(new ScheduleItem[0]);
  }

  // deleteUser 위해 필요함. 이 사용자의 일정이 있는지 확인하는 메서드
  // userId를 받기 -> schedules 순회 -> 그 userId를 가진 일정이 하나라도 있으면 true, 없으면 false
  public boolean hasSchedulesByUserId(int userId) {
    for (ScheduleItem item : schedules) {
      if (item.getUserId() == userId) {
        return true;
      }
    }

    return false;
  }
}