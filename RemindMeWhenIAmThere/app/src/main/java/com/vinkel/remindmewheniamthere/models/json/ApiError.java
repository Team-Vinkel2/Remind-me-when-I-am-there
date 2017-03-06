package com.vinkel.remindmewheniamthere.models.json;

public class ApiError extends Exception {
  private String message;

  public ApiError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
