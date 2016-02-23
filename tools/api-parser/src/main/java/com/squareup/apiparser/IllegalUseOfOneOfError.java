package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.MessageElement;

/**
 * Created by da3mon on 2/23/16.
 */
public class IllegalUseOfOneOfError extends Error {
  public IllegalUseOfOneOfError(MessageElement element) {
    super(element.name() + element.location().toString());
  }
}
