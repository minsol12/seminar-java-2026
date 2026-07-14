package com.example.java_seminar.scheduleProgram.exception;

public class ScheduleStorageException extends ScheduleException {
  public ScheduleStorageException(String message) {
    super(message);
  }

  public ScheduleStorageException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}