package com.squareup.apiparser;

public class InvalidSpecException extends RuntimeException {
  public InvalidSpecException(String message) {
    super(message);
  }

  public InvalidSpecException(String message, Exception cause) {
    super(message, cause);
  }
}
