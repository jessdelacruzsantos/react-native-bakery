package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.ServiceElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by barlow on 2/2/16.
 */
public class ConnectService {
  private final ServiceElement rootService;

  public ConnectService(ServiceElement rootService) {
    this.rootService = checkNotNull(rootService);
  }

  public ServiceElement getRootService() {
    return this.rootService;
  }
}
