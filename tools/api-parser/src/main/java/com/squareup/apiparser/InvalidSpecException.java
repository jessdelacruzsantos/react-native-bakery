package com.squareup.apiparser;

import java.util.Optional;
import com.squareup.wire.schema.internal.parser.RpcElement;
import static com.google.common.base.Preconditions.checkNotNull;

class InvalidSpecException extends RuntimeException {
  private RpcElement element;

  private InvalidSpecException(String message, RpcElement element, Exception cause) {
    super(message, cause);

    this.element = element;
  }

  Optional<RpcElement> getContext() { return Optional.ofNullable(this.element); }

  static class Builder {
    private String message;
    private Exception cause;
    private RpcElement context;

    Builder(String message) {
      this.message = message;
    }

    Builder setCause(Exception cause) {
      checkNotNull(cause);
      this.cause = cause;
      return this;
    }

    Builder setContext(RpcElement context) {
      checkNotNull(context);
      this.context = context;
      return this;
    }

    InvalidSpecException build() {
      return new InvalidSpecException(this.message, this.context, this.cause);
    }
  }
}
