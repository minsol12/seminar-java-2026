package com.example.java_seminar.schedule;

import java.time.LocalDateTime;

public class User {
  private final int id;
  private String name;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private static int idCounter = 1;

//  public User() {
//    this.id = idCounter++;
//    LocalDateTime now = LocalDateTime.now();
//    this.createdAt = now;
//    this.updatedAt = now;
//  }

  // 생성자에서 바로 setter 쓰기
  public User(String name, String email) {
//    this();
    this.id = idCounter++;
    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;

    setName(name);
    setEmail(email);
  }

  // 파일 복원용 생성자
  public User(int id, String name, String email) {
    this.id = id;
    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;

    setName(name);
    setEmail(email);

    if (id >= idCounter) {
      idCounter = id + 1;
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setName(String name) {
    // null 금지, 공백만 있는 문자열 금지
    if (name == null || name.isBlank()) {  // isBlank() - 문자열이 비어 있거나, 빈 공백으로만 이루어져 있으면, true를 리턴
      throw new IllegalArgumentException("사용자 이름은 비어 있을 수 없슨..");
    }

    this.name = name;
//    this.updatedAt = LocalDateTime.now();
    touchUpdatedAt();
  }

  public void setEmail(String email) {
    // null 금지, 공백 금지, @ 포함
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("이메일은 비어 있을 수 없슨..");
    }

    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다..");
    }

    this.email = email;
//    this.updatedAt = LocalDateTime.now();
    touchUpdatedAt();
  }

  // 이메일 검증 메서드
  private boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(emailRegex);
  }

  // touchUpdatedAt()
  private void touchUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  // 사용자 목록 조회, 상세 조회 - 출력 메서드 - displayInfo()
  public void displayInfo() {
    System.out.println("=== 사용자 정보 ===");
    System.out.println("id: " + id);
    System.out.println("name: " + name);
    System.out.println("email: " + email);
    System.out.println("createdAt: " + createdAt);
    System.out.println("updatedAt: " + updatedAt);
  }
}