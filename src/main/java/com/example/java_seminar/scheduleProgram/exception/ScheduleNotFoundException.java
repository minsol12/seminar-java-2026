package com.example.java_seminar.scheduleProgram.exception;

public class ScheduleNotFoundException extends ScheduleException {
  public ScheduleNotFoundException(int id) {
    super("해당 id가 존재하지 않습니다. id: " + id);
  }
}
