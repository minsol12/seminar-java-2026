package com.example.java_seminar.scheduleProgram.user;

import com.example.java_seminar.scheduleProgram.exception.ScheduleException;
import com.example.java_seminar.scheduleProgram.schedule.ScheduleManager;
import com.example.java_seminar.scheduleProgram.exception.ScheduleStorageException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
  private List<User> users = new ArrayList<>();

//- addUser
//  같은 이메일 가진 사용자 중복 등록 불가
//  중복이면 예외 던짐
  public void addUser(User user) throws ScheduleException {
    if (isDuplicateName(user.getName())) {
      throw new ScheduleException("이미 사용 중인 사용자 이름입니다.");
    }

    if (isDuplicateEmail(user.getEmail())) {
      throw new ScheduleException("이미 사용 중인 이메일입니다.");
    }

    users.add(user);
  }

  // isDuplicateEmail
  // 중복 검사 메서드
  private boolean isDuplicateEmail(String email) {
    for (User user : users) {
      if (user.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

  private boolean isDuplicateName(String name) {
    for (User user : users) {
      if (user.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  //- displayAllUsers
  public void displayAllUsers() {
    if (users.isEmpty()) {
      System.out.println("등록된 사용자가 없습니다..");
      return;
    }

    for (User user : users) {
      user.displayInfo();
      System.out.println();
    }
  }

  // id 찾기
  public User findById(int id) throws ScheduleException {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }

    throw new ScheduleException("해당 id의 사용자가 존재하지 않습니다..");
  }

  public User findByName(String name) throws ScheduleException {
    for (User user : users) {
      if (user.getName().equals(name)) {
        return user;
      }
    }

    throw new ScheduleException("해당 이름의 사용자가 존재하지 않습니다.");
  }

//- displayUserById
  public void displayUserById(int id) throws ScheduleException {
    // findById() -> displayInfo();
    User user = findById(id);
    user.displayInfo();
  }

//- updateUser
  // id 입력 받아
  // name, email 수정
  // 사용자 존재 여부
  // 바꾸려는 이메일이 다른 사용자와 중복되는지 - 만들어야댐. 내 이메일 그대로 다시 입력하는 건 허용해야 하기 때문에
  public void updateUser(int id, String name, String email) throws ScheduleException {
    User user = findById(id);

    if (isDuplicateNameExceptUser(name, id)) {
      throw new ScheduleException("이미 사용 중인 사용자 이름입니다.");
    }

    if (isDuplicateEmailExceptUser(email, id)) {
      throw new ScheduleException("이미 사용 중인 이메일입니다.");
    }

    user.setName(name);
    user.setEmail(email);
  }

  // deleteUser 메서드
  // findById(id)로 사용자 존재 확인 -> scheduleManager.hasSchedulesByUserId(id) 호출 -> 일정 있다면 예외, 없으면 users 리스트에서 제거
  public void deleteUser(int id, ScheduleManager scheduleManager) throws ScheduleException {
    findById(id);

    if (scheduleManager.hasSchedulesByUserId(id)) {
      throw new ScheduleException("해당 사용자에게 등록된 일정이 있어 삭제할 수 없습니당...");
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId() == id) {
        users.remove(i);
        return;
      }
    }
  }

  private boolean isDuplicateEmailExceptUser(String email, int userId) {
    for (User user : users) {
      if (user.getId() != userId && user.getEmail().equals(email)) {
        return true;
      }
    }

    return false;
  }

  // 저장
  private boolean isDuplicateNameExceptUser(String name, int userId) {
    for (User user : users) {
      if (user.getId() != userId && user.getName().equals(name)) {
        return true;
      }
    }

    return false;
  }

  public void saveToFile(String filePath) throws ScheduleStorageException {
    try {
      Path path = Paths.get(filePath);

      if (path.getParent() != null) {
        Files.createDirectories(path.getParent());
      }

      List<String> lines = new ArrayList<>();

      for (User user : users) {
        lines.add(toDataString(user));
      }

      Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new ScheduleStorageException("사용자 저장에 실패했습니다.", e);
    }
  }

  // toDataString(User user)
  // User 객체 하나를 파일에 쓸 한 줄 문자열로 바꿈
  private String toDataString(User user) {
    return user.getId() + "|" + user.getName() + "|" + user.getEmail();
  }

  // 불러오기
  // 파일 존재 확인 - 모든 줄 읽기 - 기존 users 비우기 - 각 줄을 User 객체로 복원 - 복원 실패 시 예외 처리
  public void loadFromFile(String filePath) throws ScheduleStorageException {
    try {
      Path path = Path.of(filePath);

      if (!Files.exists(path)) {
        throw new ScheduleStorageException("사용자 저장 파일이 존재하지 않습니다.");
      }

      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      List<User> loadedUsers = new ArrayList<>();

      for (String line : lines) {
        if (line.isBlank()) {
          continue;
        }

        loadedUsers.add(fromDataString(line));
      }

      users.clear();
      users.addAll(loadedUsers);
    } catch (IOException e) {
      throw new ScheduleStorageException("사용자 불러오기에 실패했습니다.", e);
    }
  }

  private User fromDataString(String line) throws ScheduleStorageException {
    String[] parts = line.split("\\|");

    if (parts.length != 3) {
      throw new ScheduleStorageException("사용자 저장 파일 형식이 올바르지 않습니다.");
    }

    // parts로 나누기
    try {
      int id = Integer.parseInt(parts[0]);
      String name = parts[1];
      String email = parts[2];

      return new User(id, name, email);
    } catch (Exception e) {
      throw new ScheduleStorageException("사용자 저장 파일 형식이 올바르지 않습니다.");
    }
  }
}
