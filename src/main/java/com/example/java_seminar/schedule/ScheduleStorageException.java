package com.example.java_seminar.schedule;

public class ScheduleStorageException extends ScheduleException {
  public ScheduleStorageException(String message) {
    super(message);
  }

  public ScheduleStorageException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}