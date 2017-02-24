package com.squareup.apiparser;

class InvalidSpecException extends RuntimeException {
  InvalidSpecException(String message) {
    super(message);
  }

  InvalidSpecException(String message, Exception cause) {
    super(message, cause);
  }
}
